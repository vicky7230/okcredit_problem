package com.vicky7230.okcredit_problem.ui.news

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicky7230.okcredit_problem.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter(arrayListOf())
    }

    @Provides
    fun provideLayoutManager(
        @ApplicationContext context: Context
    ): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}