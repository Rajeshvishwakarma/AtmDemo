package com.app.atmapplication.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AtmData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "r_100") val r_100: Int,
    @ColumnInfo(name = "r_200") val r_200: Int,
    @ColumnInfo(name = "r_500") val r_500: Int,
    @ColumnInfo(name = "r_2000") val r_2000: Int,




    )
