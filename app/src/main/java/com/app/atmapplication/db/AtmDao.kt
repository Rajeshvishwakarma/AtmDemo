package com.app.atmapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AtmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(atm: AtmData)

    @Query("SELECT * FROM 'AtmData'")
    @OnConflictStrategy
    fun getAllAtmData(): LiveData<AtmData>

    @Update
    suspend fun updateNote(note: AtmData)

}