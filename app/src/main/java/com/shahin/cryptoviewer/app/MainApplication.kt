package com.shahin.cryptoviewer.app

import android.app.Application
import com.shahin.cryptoviewer.di.ApplicationGraph
import com.shahin.cryptoviewer.di.DaggerApplicationGraph

class MainApplication : Application() {
    lateinit var applicationGraph: ApplicationGraph
        private set

    override fun onCreate() {
        super.onCreate()
        applicationGraph = DaggerApplicationGraph.builder()
            .application(this)
            .build()
    }

}