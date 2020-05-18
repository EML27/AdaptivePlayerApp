package com.itis.adaptiveplayerapp.ui.viewmodel.di.modules

import com.itis.adaptiveplayerapp.ui.view.StarterActivity
import com.itis.adaptiveplayerapp.ui.view.StarterActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [StarterActivityModule::class])
    abstract fun starterActivityActivityInjector(): StarterActivity
}