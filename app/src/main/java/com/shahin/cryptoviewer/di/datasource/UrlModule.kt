package com.shahin.cryptoviewer.di.datasource

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UrlModule {

    companion object {
        const val WEBSITE_ENDPOINT = "https://www.megaweb.ir/"
        const val BASE_PATH = "api/"
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = WEBSITE_ENDPOINT + BASE_PATH

}