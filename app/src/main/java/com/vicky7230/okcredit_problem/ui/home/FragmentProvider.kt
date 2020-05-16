package com.vicky7230.okcredit_problem.ui.home

import com.vicky7230.okcredit_problem.ui.news.NewsFragment
import com.vicky7230.okcredit_problem.ui.news.NewsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {
    @ContributesAndroidInjector(modules = [(NewsModule::class)])
    internal abstract fun provideNewsFragment(): NewsFragment
}