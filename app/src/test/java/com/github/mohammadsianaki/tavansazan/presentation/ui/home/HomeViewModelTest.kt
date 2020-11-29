package com.github.mohammadsianaki.tavansazan.presentation.ui.home

import com.github.mohammadsianaki.tavansazan.domain.repository.AppRepository
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val repository: AppRepository = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(repository)

    }

    @Test
    fun loadData() {

    }
}