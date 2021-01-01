package com.example.coroutine_retrofit_mvvm.main.di

import androidx.lifecycle.ViewModel
import com.example.coroutine_retrofit_mvvm.di.ViewModelKey
import com.example.coroutine_retrofit_mvvm.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewmodel: MainViewModel): ViewModel
}