package com.app.atmapplication.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.atmapplication.R
import com.app.atmapplication.databinding.ActivityMainBinding
import com.app.atmapplication.db.AtmData
import com.app.atmapplication.db.AtmDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var atmDatabase: AtmDatabase
    private lateinit var atmViewModelFactory: AtmViewModelFactory

    private lateinit var atmViewModel: AtmViewModel
    private lateinit var repository: AtmRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        atmDatabase = AtmDatabase.invoke(this)
        repository = AtmRepository(atmDatabase)
        atmViewModelFactory = AtmViewModelFactory(repository)
        atmViewModel = ViewModelProvider(this, atmViewModelFactory)[AtmViewModel::class.java]

        insertData()


        CoroutineScope(Dispatchers.Main).launch {
            atmViewModel.getAllAtmData().observe(this@MainActivity) {
                binding.data = it
            }
        }
        binding.btnWithdrow.setOnClickListener {
            update()
        }
    }

    private fun update() {
        CoroutineScope(Dispatchers.Main).launch {
            calculateMoney()
        }
    }

    private fun calculateMoney() {
        var amount: Int = Integer.parseInt(binding.etWithDrow.text.toString())
//        var note100 = Integer.parseInt(binding.tv100.text.toString())
//        var note200 =  Integer.parseInt(binding.tv200.text.toString())
//        var note500 =  Integer.parseInt(binding.tv500.text.toString())
//        var note2000 =  Integer.parseInt(binding.tv2000.text.toString())

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
                Toast.makeText(this, "not match", Toast.LENGTH_LONG).show()
            }
        }

        Log.e(
            "note ",
            "note " + note100 + "   " + note200 + "   " + note2000 + "   " + note500 + "  " + amount
        )
//        atmViewModel.updateNote(AtmData())


    }


    private fun insertData() {
//        insert
        CoroutineScope(Dispatchers.Main).launch {
            atmViewModel.insertNote(AtmData(1, 5000, 75, 50, 25, 10))
        }
    }
}
