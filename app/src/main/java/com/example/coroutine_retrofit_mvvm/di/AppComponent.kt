package com.example.coroutine_retrofit_mvvm.di

import android.content.Context
import com.example.coroutine_retrofit_mvvm.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SubcomponentsModule::class,
        RetrofitNetworkModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory


}

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
object SubcomponentsModule  /// temple object to link subcomponent to appcomponent