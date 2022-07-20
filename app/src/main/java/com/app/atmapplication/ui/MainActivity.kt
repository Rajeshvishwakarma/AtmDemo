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
        binding.viewmodel = atmViewModel
//        insertData()


        CoroutineScope(Dispatchers.Main).launch {
            atmViewModel.data.observe(this@MainActivity) {
                binding.data = it
            }
        }
        atmViewModel.edittext.observe(this@MainActivity) {
            Log.e("edit", "edit " + it)
        }

    }



}
