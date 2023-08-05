package com.basar.spacextracker.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule

abstract class BaseVMTest<T : ViewModel> {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    protected lateinit var viewModel: T

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
        beforeSetup()
    }

    abstract fun beforeSetup()
}