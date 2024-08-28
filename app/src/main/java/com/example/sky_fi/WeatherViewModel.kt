package com.example.sky_fi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sky_fi.api.Constant
import com.example.sky_fi.api.NetworkResponse
import com.example.sky_fi.api.WeatherModel
import com.example.sky_fi.api.retrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    private val weatherApi = retrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String){

        viewModelScope.launch {
            _weatherResult.value = NetworkResponse.Loading
            try {
                val response = weatherApi.getWeather(Constant().apiKey,city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

        }

    }

}