package com.vicky7230.okcredit_problem.di.module


import com.vicky7230.okcredit_problem.ui.home.FragmentProvider
import com.vicky7230.okcredit_problem.ui.home.HomeActivity
import com.vicky7230.okcredit_problem.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class, FragmentProvider::class])
    abstract fun bindHomeActivity(): HomeActivity

}