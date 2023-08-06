package com.basar.spacextracker.ui.dashboard.favourites

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseVMTest
import com.basar.spacextracker.domain.favourites.DeleteFavouriteRocketUseCase
import com.basar.spacextracker.domain.favourites.GetFavouriteRocketUseCase
import com.basar.spacextracker.domain.uimodel.RocketUIItem
import com.basar.spacextracker.testdata.RocketDataFixtures
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FavouritesViewModelTest : BaseVMTest<FavouritesViewModel>() {

    @MockK
    private lateinit var getFavouriteRocketUseCase: GetFavouriteRocketUseCase

    @MockK
    private lateinit var deleteFavouriteRocketUseCase: DeleteFavouriteRocketUseCase

    override fun beforeSetup() {
        viewModel = FavouritesViewModel(getFavouriteRocketUseCase, deleteFavouriteRocketUseCase)
    }

    @Test
    fun `when favourited rockets are successfully fetched then set state to not loading and update the result item list`() =
        runBlocking {
            // Given
            val expectedResponse: List<RocketUIItem> = RocketDataFixtures.buildFavouriteRocketUIs(1, 2)

            // When
            coEvery { getFavouriteRocketUseCase() } coAnswers { flow { emit(expectedResponse) } }
            viewModel.getFavouriteRocketList().join()

            // Then
            viewModel.uiState.test {
                val item = expectMostRecentItem()
                Truth.assertThat(item.isLoading).isFalse()
                Truth.assertThat(item.items.size).isEqualTo(
                    expectedResponse.size
                )
            }
        }
}