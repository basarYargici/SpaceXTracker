package com.basar.spacextracker.domain.detail

import app.cash.turbine.test
import com.basar.spacextracker.base.BaseUseCaseTest
import com.basar.spacextracker.domain.repository.RocketRepository
import com.basar.spacextracker.testdata.RocketDataFixtures.getRocketUIs
import com.basar.spacextracker.testdata.RocketDataFixtures.getRockets
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetRocketByIdUseCaseTest : BaseUseCaseTest<GetRocketByIdUseCase>() {

    @MockK
    private lateinit var repository: RocketRepository

    private val rockets = getRockets()
    private val rocketUIs = getRocketUIs()

    override fun beforeSetup() {
        useCase = GetRocketByIdUseCase(repository)
    }

    @Test
    fun `when valid id is requested then return related rocket`() =
        runBlocking {
            // Given
            val expectedRockets = rockets.first()
            val rocketUIs = rocketUIs.first()
            val requestedId = "1"

            // When
            coEvery {
                repository.getRocketById(requestedId)
            } coAnswers {
                flow { emit(expectedRockets) }
            }

            // Then
            useCase(requestedId).test {
                val response = expectMostRecentItem()
                Truth.assertThat(response?.id).isEqualTo(rocketUIs.id)
            }
        }
}