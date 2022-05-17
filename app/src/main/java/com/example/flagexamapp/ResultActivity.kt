package com.example.flagexamapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagexamapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trueCounter = intent.getIntExtra("trueCounter",0)
        binding.textViewSonuc.text="$trueCounter DOĞRU  ${5-trueCounter} YANLIŞ "

        binding.textViewYuzdeSonuc.text = " % ${(trueCounter*100) / 5} Başarı"

        binding.buttonTekrar.setOnClickListener{
            startActivity(Intent(this@ResultActivity,ExamActivity::class.java))
            finish()

        }
    }
}