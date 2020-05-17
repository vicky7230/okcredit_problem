package com.vicky7230.okcredit_problem.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky7230.okcredit_problem.di.ViewModelFactory
import com.vicky7230.okcredit_problem.di.ViewModelKey
import com.vicky7230.okcredit_problem.ui.details.DetailsViewModel
import com.vicky7230.okcredit_problem.ui.home.HomeViewModel
import com.vicky7230.okcredit_problem.ui.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun postHomeViewModel(mainViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun postNewsViewModel(newsViewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun postDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel

}