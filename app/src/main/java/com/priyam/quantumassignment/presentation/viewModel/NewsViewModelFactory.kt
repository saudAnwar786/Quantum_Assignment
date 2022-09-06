package com.priyam.quantumassignment.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priyam.quantumassignment.domain.usecase.GetNewsHeadlinesUseCase
import com.priyam.quantumassignment.domain.usecase.GetSearchedNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase
        ) as T
    }


}
