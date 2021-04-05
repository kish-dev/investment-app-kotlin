package com.invest.tickerapp.model.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.invest.tickerapp.model.data.*
import com.invest.tickerapp.viewmodel.ConfigureViewModel
import com.invest.tickerapp.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): CompanyDatabase {
        var result: CompanyDatabase? = null
        result = Room.databaseBuilder(
                appContext,
                CompanyDatabase::class.java,
                "company.db"
        ).addCreateCallback {
            for (company in Company.ListOfCompaniesLoader.listOfCompanies) {
                DataStoreScope.launch(Dispatchers.IO) {
                    result!!.companyDao().insert(company)
                }
            }
        }.build()
        return result
    }

    @Provides
    fun provideDao(database: CompanyDatabase): CompanyDao {
        return database.companyDao()
    }

    @Provides
    @IntoMap
    @ViewModelKey(ConfigureViewModel::class)
    fun provideConfigureViewModel(adapter: ViewModelAdapter): ViewModel =
            ConfigureViewModel(adapter)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(adapter: ViewModelAdapter): ViewModel =
        SearchViewModel(adapter)
}
