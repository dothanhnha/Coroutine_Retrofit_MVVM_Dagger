package com.example.coroutine_retrofit_mvvm.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coroutine_retrofit_mvvm.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),
    BaseViewModelOneLoading.OnStatusResponseChange {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).appComponent.mainComponent().create()
            .inject(this)

        viewModel.setup(this, this);
        viewModel.fetchData()

        viewModel.currentWeather1.observe(this, Observer<MyResponse<CurrentWeather>> { response ->
            if (response.status == MyStatus.SUCCESS)
                textView1.text = response.data.toString()
            else
                textView1.text = ""
        })

        viewModel.currentWeather2.observe(this, Observer<MyResponse<CurrentWeather>> { response ->
            if (response.status == MyStatus.SUCCESS)
                textView2.text = response.data.toString()
            else
                textView2.text = ""
        })

        layout.setOnClickListener({ view ->
            viewModel.fetchData()
        })
    }

    override fun onSuccessAll() {
        spinKit.visibility = View.GONE
    }

    override fun onLoading() {
        spinKit.visibility = View.VISIBLE;
    }

    override fun onError() {

    }
}