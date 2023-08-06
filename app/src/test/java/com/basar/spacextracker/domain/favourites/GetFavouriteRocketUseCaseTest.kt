package com.basar.spacextracker.domain.favourites

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseUseCaseTest
import com.basar.spacextracker.domain.repository.LocalRocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.testdata.RocketDataFixtures.buildFavouriteRockets
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetFavouriteRocketUseCaseTest : BaseUseCaseTest<GetFavouriteRocketUseCase>() {

    @MockK
    private lateinit var repository: LocalRocketRepository

    override fun beforeSetup() {
        useCase = GetFavouriteRocketUseCase(repository)
    }

    @Test
    fun `when favourited rockets requested then return RocketUIItem`() = runBlocking {
        // Given
        val favouriteRocketIds = intArrayOf(1, 2)
        val favouriteRockets = buildFavouriteRockets(*favouriteRocketIds)

        // When
        coEvery {
            repository.getFavouriteRockets()
        } coAnswers {
            flow { emit(favouriteRockets) }
        }

        // Then
        useCase().test {
            val response = expectMostRecentItem()
            Truth.assertThat(response?.size).isEqualTo(favouriteRockets.size)
            Truth.assertThat(response?.firstOrNull()?.id).isEqualTo(favouriteRockets.firstOrNull()?.id)
            Truth.assertThat(response?.firstOrNull()).isInstanceOf(RocketUIItem::class.java)
        }
    }
}