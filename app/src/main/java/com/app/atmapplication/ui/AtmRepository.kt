package com.app.atmapplication.ui

import androidx.lifecycle.LiveData
import com.app.atmapplication.db.AtmData
import com.app.atmapplication.db.AtmDatabase

class AtmRepository(
    private val noteDatabase: AtmDatabase
) {

    suspend fun insertNote(note: AtmData) = noteDatabase.atmData().insertAll(note)

    suspend fun updateNote(note: AtmData) = noteDatabase.atmData().updateNote(note)

    fun getAllAtmData(): LiveData<AtmData> = noteDatabase.atmData().getAllAtmData()
}