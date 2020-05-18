package com.itis.adaptiveplayerapp.ui.viewmodel.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel() : ViewModel() {
    val compositeDisposable = CompositeDisposable()
}