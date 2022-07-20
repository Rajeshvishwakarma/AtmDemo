package com.app.atmapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AtmViewModelFactory(
    private val repository: AtmRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(AtmRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("TAG", "create: " + e.message.toString())
        }
        return super.create(modelClass)
    }
}