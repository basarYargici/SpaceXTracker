package com.basar.spacextracker.domain.dashboard

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseUseCaseTest
import com.basar.spacextracker.data.remote.response.Engines
import com.basar.spacextracker.data.remote.response.Height
import com.basar.spacextracker.data.remote.response.Mass
import com.basar.spacextracker.data.remote.response.Rocket
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.google.common.truth.Truth
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

    private val rockets = listOf(
        Rocket(
            id = "1",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon 1",
            country = "Republic of the Marshall Islands",
            company = "Intel",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(22.25),
            mass = Mass(30146),
            engines = Engines(number = 1),
        ), Rocket(
            id = "2",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon 9",
            country = "United States",
            company = "SpaceX",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(70.0),
            mass = Mass(549054),
            engines = Engines(number = 9),
        ), Rocket(
            id = "3",
            flickrImages = listOf("https:1", "https:2"),
            name = "Falcon Heavy",
            country = "United States",
            company = "Tesla",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(70.0),
            mass = Mass(70),
            engines = Engines(number = 27),
        ), Rocket(
            id = "4",
            flickrImages = listOf("https:1", "https:2"),
            name = "Starship",
            country = "United States",
            company = "Yargici",
            wikipedia = "Wiki",
            description = "Description",
            height = Height(118.0),
            mass = Mass(1335000),
            engines = Engines(number = 37)
        )
    )
    private val rocketUIs = listOf(
        RocketUIItem(
            id = "1",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon 1",
            country = "Republic of the Marshall Islands",
            company = "Intel",
            wikipedia = "Wiki",
            description = "Description",
            height = "22.25",
            weight = "30146",
            engineCount = "1",
            isFavourite = false
        ), RocketUIItem(
            id = "2",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon 9",
            country = "United States",
            company = "SpaceX",
            wikipedia = "Wiki",
            description = "Description",
            height = "70",
            weight = "549054",
            engineCount = "9",
            isFavourite = false
        ), RocketUIItem(
            id = "3",
            imageUrl = listOf("https:1", "https:2"),
            name = "Falcon Heavy",
            country = "United States",
            company = "Tesla",
            wikipedia = "Wiki",
            description = "Description",
            height = "70",
            weight = "1420788",
            engineCount = "27",
            isFavourite = false
        ), RocketUIItem(
            id = "4",
            imageUrl = listOf("https:1", "https:2"),
            name = "Starship",
            country = "United States",
            company = "Yargici",
            wikipedia = "Wiki",
            description = "Description",
            height = "118",
            weight = "1335000",
            engineCount = "37",
            isFavourite = false
        )
    )
    private val favouriteRockets = listOf(
        rocketUIs.first().copy(isFavourite = true)
    )

    override fun beforeSetup() {
        useCase = GetAllRocketsUseCase(repository, getFavouriteRocketUseCase)
    }

    @Test
    fun `when no rocket is favourited then all rockets should not contain any favourited rocket`() =
        runBlocking {
            // Given
            val expectedRockets = rockets
            val rocketUIs = rockets

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
                Truth.assertThat(response?.any { !it.isFavourite }).isEqualTo(true)
                Truth.assertThat(response?.size).isEqualTo(rocketUIs.size)
            }
        }

    @Test
    fun `when one rocket is favourited then all rockets should contain one favourited rocket`() =
        runBlocking {
            // Given
            val expectedRockets = rockets
            val expectedFavs = favouriteRockets

            // When
            coEvery { repository.getRocketList() } coAnswers {
                flow { emit(expectedRockets) }
            }
            coEvery { getFavouriteRocketUseCase() } coAnswers {
                flow { emit(expectedFavs) }
            }

            // Then
            useCase().test {
                val response = expectMostRecentItem()
                Truth.assertThat(response?.count { it.isFavourite }).isEqualTo(expectedFavs.size)
                Truth.assertThat(response?.first { it.isFavourite }?.id).isEqualTo(expectedRockets.first().id)
            }
        }
}