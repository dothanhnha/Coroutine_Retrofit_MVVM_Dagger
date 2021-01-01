package com.example.coroutine_retrofit_mvvm.main

import androidx.lifecycle.*
import com.example.coroutine_retrofit_mvvm.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val currentWeatherRepos : CurrentWeatherRepos
):BaseViewModelOneLoading(){
    override var countSuccess: MutableLiveData<Int> = MutableLiveData(0)

    override var numberFieldResponse: Int = 2

    val currentWeather1: MutableLiveData<MyResponse<CurrentWeather>> =
        MutableLiveData()

    val currentWeather2: MutableLiveData<MyResponse<CurrentWeather>> =
        MutableLiveData()

    override fun fetchData() {
        super.fetchData()
        this.countSuccess.value = 0;
        viewModelScope.launch {
            currentWeather1.value = currentWeatherRepos
                ?.getCurrentWeather("London", "92ec5a453b7bf29244373a5b79738bd5")
            currentWeather2.value = currentWeatherRepos
                ?.getCurrentWeather("DaNang", "92ec5a453b7bf29244373a5b79738bd5")
        }
    }

    override fun setLoadingField() {
        currentWeather1.value = MyResponse(MyStatus.LOADING,null,null)
        currentWeather2.value = MyResponse(MyStatus.LOADING,null,null)
    }

    override fun observeChangeCountForField(owner: LifecycleOwner) {
        currentWeather1.observe(owner, getObserverCountSucessForField())
        currentWeather2.observe(owner, getObserverCountSucessForField())
    }
}