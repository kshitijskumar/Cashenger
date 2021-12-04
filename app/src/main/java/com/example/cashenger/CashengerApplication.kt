package com.example.cashenger

import android.app.Application
import android.util.Log
import com.example.cashenger.utils.Injector

class CashengerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.setupInjector(getContextInNeed = {
            Log.d("CashengerAppContext", "contextNeed: $it")
            return@setupInjector this
        })
    }
}