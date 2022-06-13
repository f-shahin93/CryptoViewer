package com.shahin.cryptoviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shahin.cryptoviewer.app.MainApplication
import com.shahin.cryptoviewer.databinding.ActivityMainBinding
import com.shahin.cryptoviewer.di.MainActivitySubComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainActivitySubComponent: MainActivitySubComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        mainActivitySubComponent =
            (applicationContext as MainApplication).applicationGraph.mainComponent().create()

        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

}