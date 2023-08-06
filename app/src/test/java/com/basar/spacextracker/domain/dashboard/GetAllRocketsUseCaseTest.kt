package com.basar.spacextracker.domain.dashboard

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseUseCaseTest
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.testdata.RocketDataFixtures.buildFavouriteRocketUIs
import com.basar.spacextracker.testdata.RocketDataFixtures.getRocketUIs
import com.basar.spacextracker.testdata.RocketDataFixtures.getRockets
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllRocketsUseCaseTest : BaseUseCaseTest<GetAllRocketsUseCase>() {

    @MockK
    private lateinit var repository: RocketRepository

    @MockK
    private lateinit var getFavouriteRocketUseCase: GetFavouriteRocketUseCase

    private val rockets = getRockets()
    private val rocketUIs = getRocketUIs()

    override fun beforeSetup() {
        useCase = GetAllRocketsUseCase(repository, getFavouriteRocketUseCase)
    }

    @Test
    fun `when no rocket is favourited then rocket list should not contain any favourited rocket`() =
        runBlocking {
            // Given
            val expectedRockets = rockets
            val rocketUIs = rocketUIs

            // When
            coEvery { repository.getRocketList() } coAnswers {
                flow { emit(expectedRockets) }
            }
            coEvery { getFavouriteRocketUseCase() } coAnswers {
                flow { emit(null) }
            }

            // Then
            useCase().test {
                val response = expectMostRecentItem()
                assertThat(response?.any { !it.isFavourite }).isEqualTo(true)
                assertThat(response?.size).isEqualTo(rocketUIs.size)
            }
        }

    @Test
    fun `when one rocket is favourited then rocket list should contain one favourited rocket`() =
        runBlocking {
            // Given
            val expectedRockets = rockets
            val favouriteRocketIds = intArrayOf(1)
            val favouriteRockets = buildFavouriteRocketUIs(*favouriteRocketIds)

            // When
            coEvery { repository.getRocketList() } coAnswers {
                flow { emit(expectedRockets) }
            }
            coEvery { getFavouriteRocketUseCase() } coAnswers {
                flow { emit(favouriteRockets) }
            }

            // Then
            useCase().test {
                val response = expectMostRecentItem()
                assertThat(response?.count { it.isFavourite }).isEqualTo(favouriteRockets.size)
                assertThat(response?.first { it.isFavourite }?.id?.toInt()).isEqualTo(favouriteRocketIds.first())
            }
        }
}