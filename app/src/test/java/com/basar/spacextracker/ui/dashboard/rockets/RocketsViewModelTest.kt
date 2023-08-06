package com.basar.spacextracker.ui.dashboard.rockets

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseVMTest
import com.basar.spacextracker.domain.dashboard.GetAllRocketsUseCase
import com.basar.spacextracker.domain.favourites.AddFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.testdata.RocketDataFixtures.buildFavouriteRocketUIs
import com.basar.spacextracker.testdata.RocketDataFixtures.getRocketUIs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RocketsViewModelTest : BaseVMTest<RocketsViewModel>() {

    @MockK
    private lateinit var rocketUseCase: GetAllRocketsUseCase

    @MockK
    private lateinit var addFavouriteRocketUseCase: AddFavouriteRocketUseCase

    @MockK
    private lateinit var deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase

    override fun beforeSetup() {
        viewModel = RocketsViewModel(rocketUseCase, addFavouriteRocketUseCase, deleteFavouriteRocketUseCase)
    }

    @Test
    fun `when favourited rockets are successfully fetched then set state to not loading and update the result item list`() =
        runBlocking {
            // Given
            val expectedResponse: List<RocketUIItem> = getRocketUIs()

            // When
            coEvery { rocketUseCase() } coAnswers { flow { emit(expectedResponse) } }
            viewModel.getRocketList().join()

            // Then
            viewModel.uiState.test {
                val item = expectMostRecentItem()
                Truth.assertThat(item.isLoading).isFalse()
                Truth.assertThat(item.items.size).isEqualTo(expectedResponse.size)
            }
        }

    @Test
    fun `when favourited rockets are successfully fetched but null then set state to emptyList`() =
        runBlocking {
            // Given
            val expectedResponse: List<RocketUIItem>? = null

            // When
            coEvery { rocketUseCase() } coAnswers { flow { emit(expectedResponse) } }
            viewModel.getRocketList().join()

            // Then
            viewModel.uiState.test {
                val item = expectMostRecentItem()
                Truth.assertThat(item.isLoading).isFalse()
                Truth.assertThat(item.items.size).isEqualTo(0)
            }
        }

    @Test
    fun `when valid id passed to unfavourite then set it to unfavourited`() = runBlocking {
        // Given
        val currentList = buildFavouriteRocketUIs(1, 2)
        val removeItemId = "1"
        val expectedResponse = buildFavouriteRocketUIs(2)

        // When
        viewModel.uiState.value.items = currentList
        viewModel.unfavouredRocket(removeItemId)

        // Then
        Truth.assertThat(viewModel.uiState.value.items).isEqualTo(expectedResponse)
    }
}