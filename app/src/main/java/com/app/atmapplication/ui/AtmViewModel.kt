package com.app.atmapplication.ui

import androidx.lifecycle.ViewModel
import com.app.atmapplication.db.AtmData

class AtmViewModel(private val repository: AtmRepository) : ViewModel() {

    suspend fun insertNote(note: AtmData) = repository.insertNote(note)

    suspend fun updateNote(note: AtmData) = repository.updateNote(note)

    fun getAllAtmData() = repository.getAllAtmData()
}