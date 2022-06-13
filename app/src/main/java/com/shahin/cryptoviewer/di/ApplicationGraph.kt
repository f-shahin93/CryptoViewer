package com.shahin.cryptoviewer.di

import android.app.Application
import com.shahin.cryptoviewer.di.datasource.DataSourceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        SubComponentModule::class,
        ViewModelModule::class,
        DataSourceModule::class,
    ]
)
interface ApplicationGraph {

    fun mainComponent(): MainActivitySubComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationGraph
    }

}