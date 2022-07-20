package com.app.atmapplication.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.atmapplication.db.AtmData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtmViewModel(private val repository: AtmRepository) : ViewModel() {

    var edittext = MutableLiveData<String?>()
    var data = repository.getAllAtmData()


    init {
        if (data.value == null)
            insertData()
    }

    suspend fun insertNote(note: AtmData) = repository.insertNote(note)

    private suspend fun updateNote(note: AtmData) = repository.updateNote(note)

    fun getAllAtmData() = repository.getAllAtmData()


    fun insertData() {
        viewModelScope.launch {
            insertNote(AtmData(1, 5000, 75, 50, 25, 10))
        }
    }


    fun update() {
        CoroutineScope(Dispatchers.Main).launch {
            calculateMoney()
        }
    }

    suspend fun calculateMoney() {
        var amount: Int = Integer.parseInt(edittext.value.toString())
        var note100 = 0
        var note200 = 0
        var note500 = 0
        var note2000 = 0

        /* Initialize all notes to 0 */
        when {
            amount >= 2000 -> {
                note2000 = amount / 2000;
                amount -= note2000 * 2000;
            }
            amount >= 500 -> {
                note500 = amount / 500;
                amount -= note500 * 500;
            }
            amount >= 200 -> {

                note200 = amount / 200;
                amount -= note200 * 200;
            }
            amount >= 100 -> {
                note100 = amount / 100;
                amount -= note100 * 100;
            }
            else -> {
//                Toast.makeText(this, "not match", Toast.LENGTH_LONG).show()
            }
        }

        Log.e(
            "note ",
            "note " + note100 + "   " + note200 + "   " + note2000 + "   " + note500 + "  " + amount
        )

        if (Integer.parseInt(edittext.value.toString()) <= data.value?.amount!!)
            updateNote(
                AtmData(
                    data.value?.id!!,
                    data.value?.amount!! - Integer.parseInt(edittext.value.toString()),
                    data.value!!.r_100 - note100,
                    data.value!!.r_200 - note200,
                    data.value!!.r_500 - note500,
                    data.value!!.r_2000 - note2000
                )
            )


    }

}