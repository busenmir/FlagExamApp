package com.example.flagexamapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagexamapp.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseCopy()
        binding.button.setOnClickListener{
            startActivity(Intent(this@MainActivity,ExamActivity::class.java))
        }
    }

    fun databaseCopy(){
        val copyHelper = DatabaseCopyHelper(this)
        try{
            copyHelper.createDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }

        try{
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}