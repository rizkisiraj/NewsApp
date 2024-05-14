package com.example.newsapp
import android.app.Application
import com.example.newsapp.model.AppContainer
import com.example.newsapp.model.AppDataContainer

class LearningApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}