package com.vicky7230.okcredit_problem.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.vicky7230.okcredit_problem.data.AppDataManager
import com.vicky7230.okcredit_problem.data.Config
import com.vicky7230.okcredit_problem.data.DataManager
import com.vicky7230.okcredit_problem.data.network.ApiHelper
import com.vicky7230.okcredit_problem.data.network.AppApiHelper
import com.vicky7230.okcredit_problem.di.ApplicationContext
import com.vicky7230.okcredit_problem.di.BaseUrl
import com.vicky7230.okcredit_problem.MyApplication
import com.vicky7230.okcredit_problem.data.db.AppDatabase
import com.vicky7230.okcredit_problem.data.db.AppDbHelper
import com.vicky7230.okcredit_problem.data.db.DbHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(myApplication: MyApplication): Context {
        return myApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(myApplication: MyApplication): Application {
        return myApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Config.DB_NAME).build()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    internal fun provDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }
}