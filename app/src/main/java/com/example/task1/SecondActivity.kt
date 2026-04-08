package com.example.task1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task1.databinding.ActivityMainBinding
import com.example.task1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //წინა Activity-დან გადმოცემული მონაცემების ცვლადებად გამოცხადება
        val carName = intent.getStringExtra("carName") ?: "Unknown"
        var carPrice = intent.getDoubleExtra("carPrice",0.0)
        //ფასდაკლების პირობა
        carPrice = carPrice - (carPrice * 0.05)


        //მანქანის სახელი
        binding.itemView.text = carName

        //რადგან ფასი Double ტიპის უნდა ყოფილიყო, პირდაპირ String-ის გადმოცემა "$38,000"-ად არ იმუშავებდა, ამიტომ
        //საჭირო იყო ამ ფასის ვალუტად კონვერტირება.
        var formattedPrice = java.text.NumberFormat.getCurrencyInstance().format(carPrice)

        //Express მიწოდების პირობა
        binding.expressButtonView.setOnClickListener {
            //როგორც კი ღილაკს ეჭირება, მაშინვე ემატება შესაბამისი ფასი
            carPrice += 1700
            formattedPrice = java.text.NumberFormat.getCurrencyInstance().format(carPrice)
            binding.pricetotalView.text = "$formattedPrice"

            //ამის გარეშე თუ ღილაკს 10-ჯერ დავაჭერდით 10-ჯერვე დააკლდებოდა 1700$
            binding.expressButtonView.isEnabled = false

            //თუ Express მიწოდება მონიშნეს, საჭიროა ლურჯი მოსანიშნი ტიჭკაც ქვევით ჩამოვიდეს
            val checkMarkParameters = binding.checkmarkView.layoutParams as ConstraintLayout.LayoutParams

            //სწავლა მომიწია იმისა თუ XML-ის Constraint-ები როგორ უნდა შემეცვალა ამ კოტლინ ფაილიდან
            checkMarkParameters.bottomToBottom = R.id.circleView2
            checkMarkParameters.startToStart = R.id.circleView2
            checkMarkParameters.topToTop = R.id.circleView2
            binding.checkmarkView.layoutParams = checkMarkParameters
        }
        binding.priceView.text = "$formattedPrice"
        binding.pricetotalView.text = "$formattedPrice"

        //Pay ღილაკზე დაჭერისას გადავდივართ მესამე Activity-ზე
        binding.payButtonView.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}