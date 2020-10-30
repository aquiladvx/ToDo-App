package com.debcomp.aql.debcentral

import android.app.Application

class DEBCentralApplication: Application(){


    companion object {
        lateinit var instance: DEBCentralApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}