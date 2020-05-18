package com.itis.adaptiveplayerapp

import android.content.Context
import com.itis.adaptiveplayerapp.ui.view.StarterActivity
import com.itis.adaptiveplayerapp.ui.view.StarterActivityModule
import com.itis.adaptiveplayerapp.ui.view.StarterActivityVM
import com.itis.adaptiveplayerapp.ui.viewmodel.di.modules.ActivityInjectorsModule
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


var instance: App? = null

class App : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        fun getInstance() = instance
    }
//
//    @Inject
//    lateinit var androidInjector: DispatchingAndroidInjector<Activity>
//
//    override fun activityInjector(): AndroidInjector<Activity>  = androidInjector

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}

@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}

@Module
abstract class AppModule {
    @ContributesAndroidInjector(modules = [StarterActivityModule::class])
    abstract fun starterActivity(): StarterActivity

}