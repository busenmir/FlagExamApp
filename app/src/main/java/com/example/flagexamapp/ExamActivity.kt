package com.example.flagexamapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flagexamapp.Model.Flags
import com.example.flagexamapp.Model.FlagsDao
import com.example.flagexamapp.databinding.ActivityExamBinding

class ExamActivity : AppCompatActivity() {
    private lateinit var questions : ArrayList<Flags>
    private lateinit var falseOption : ArrayList<Flags>
    private lateinit var trueOption : Flags
    private lateinit var allOption : HashSet<Flags>
    private lateinit var vt : DatabaseAssistant

    private var examCounter = 0
    private var trueCounter = 0
    private var falseCounter = 0

    private lateinit var binding: ActivityExamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vt = DatabaseAssistant(this)

        questions = FlagsDao().randomFlag(vt)

        examLoad()

        binding.buttonA.setOnClickListener{
            trueControl(binding.buttonA)
            qCounterControl()
        }
        binding.buttonB.setOnClickListener{
            trueControl(binding.buttonB)
            qCounterControl()

        }
        binding.buttonC.setOnClickListener{
            trueControl(binding.buttonC)
            qCounterControl()

        }
    }
    @SuppressLint("SetTextI18n")
    fun examLoad(){

        binding.textViewExam.text = "${examCounter+1}. Soru"

        trueOption = questions.get(examCounter)

        binding.imageView.setImageResource(resources.getIdentifier(trueOption.bayrak_resim,"drawable",packageName))

        falseOption = FlagsDao().randomFlagFalse(vt,trueOption.bayrak_id)

        allOption = HashSet()
        allOption.add(trueOption)
        allOption.add(falseOption.get(0))
        allOption.add(falseOption.get(1))

        binding.buttonA.text=allOption.elementAt(0).bayrak_ad
        binding.buttonB.text=allOption.elementAt(1).bayrak_ad
        binding.buttonC.text=allOption.elementAt(2).bayrak_ad

    }

    fun qCounterControl(){
        examCounter++

        if(examCounter != 5){
            examLoad()
        }else{
            val intent = Intent(this@ExamActivity,ResultActivity::class.java)
            intent.putExtra("trueCounter",trueCounter)
            startActivity(intent)
            finish()
        }
    }

    // Buton üzerindeki yazıyı alarak doğru cevap ile eşleştirip doğru/yanlış ayırımı yapabileceğiz.
    fun trueControl(button : Button){
        val buttonText = button.text.toString()

        val trueAnswer = trueOption.bayrak_ad

        if (buttonText == trueAnswer ){
            trueCounter++

        }else{
            falseCounter++
        }

        binding.textViewTrue.text = "Doğru : ${trueCounter}"
        binding.textViewFalse.text = "Yanlış : ${falseCounter}"


    }
}