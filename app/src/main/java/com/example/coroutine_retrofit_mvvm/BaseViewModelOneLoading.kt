package com.example.coroutine_retrofit_mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModelOneLoading : ViewModel() {

    open var numberFieldResponse: Int = 0


    open var countSuccess: MutableLiveData<Int> = MutableLiveData(0)
    var eventCallBackStatusResponse: OnStatusResponseChange? = null


    interface OnStatusResponseChange {
        fun onSuccessAll()
        fun onLoading()
        fun onError()
    }

    open fun fetchData() {
        setLoadingField()
        this.countSuccess.value = 0
    }

    open fun setup(owner: LifecycleOwner, eventCallBackStatusResponse: OnStatusResponseChange) {
        observeChangeCountForField(owner)
        this.eventCallBackStatusResponse = eventCallBackStatusResponse
        if (numberFieldResponse > 0)
            this.countSuccess.observe(owner, Observer { count ->
                if (count == this@BaseViewModelOneLoading.numberFieldResponse)
                    this@BaseViewModelOneLoading.eventCallBackStatusResponse?.onSuccessAll()
                else
                    this@BaseViewModelOneLoading.eventCallBackStatusResponse?.onLoading()
            })
    }

    fun <S> getObserverCountSucessForField(): Observer<MyResponse<S>> {
        return object : Observer<MyResponse<S>> {
            override fun onChanged(response: MyResponse<S>?) {
                if (response?.status == MyStatus.SUCCESS)
                    countSuccess.value = countSuccess.value?.inc()
            }
        }
    }

    abstract fun setLoadingField()
    abstract fun observeChangeCountForField(owner: LifecycleOwner)
}
