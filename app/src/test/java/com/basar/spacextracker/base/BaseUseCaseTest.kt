package com.basar.spacextracker.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
abstract class BaseUseCaseTest<U : Any> {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    protected lateinit var useCase: U

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
        beforeSetup()
    }

    abstract fun beforeSetup()
}