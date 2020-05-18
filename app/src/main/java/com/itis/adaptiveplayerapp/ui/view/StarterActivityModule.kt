package com.itis.adaptiveplayerapp.ui.view

import dagger.Module
import dagger.Provides
import com.itis.adaptiveplayerapp.ui.viewmodel.di.qualifiers.ViewModelInjection
import com.itis.adaptiveplayerapp.ui.viewmodel.di.InjectionViewModelProvider

@Module
class StarterActivityModule {

    @Provides
    @ViewModelInjection
    fun provideStarterActivityVM(
        activity: StarterActivity,
        viewModelProvider: InjectionViewModelProvider<StarterActivityVM>
    ): StarterActivityVM = viewModelProvider.get(activity)
}