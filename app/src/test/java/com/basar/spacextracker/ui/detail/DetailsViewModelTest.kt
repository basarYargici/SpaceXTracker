package com.basar.spacextracker.ui.detail

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseVMTest
import com.basar.spacextracker.domain.detail.GetRocketByIdUseCase
import com.basar.spacextracker.domain.uimodel.RocketDetailUIItem
import com.basar.spacextracker.testdata.RocketDataFixtures.getRocketDetailUIs
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DetailsViewModelTest : BaseVMTest<DetailsViewModel>() {

    @MockK
    private lateinit var rocketDetailUseCase: GetRocketByIdUseCase

    override fun beforeSetup() {
        viewModel = DetailsViewModel(rocketDetailUseCase)
    }

    @Test
    fun `when rocket is successfully fetched then set state to not loading and update the result item`() =
        runBlocking {
            // Given
            val expectedResponse: RocketDetailUIItem = getRocketDetailUIs().first()
            val requestedId = "1"

            // When
            coEvery { rocketDetailUseCase(requestedId) } coAnswers { flow { emit(expectedResponse) } }
            viewModel.getRocketDetailById(requestedId).join()

            // Then
            viewModel.uiState.test {
                val item = expectMostRecentItem()
                Truth.assertThat(item.isLoading).isFalse()
                Truth.assertThat(item.item?.id).isEqualTo(expectedResponse.id)
            }
        }
}