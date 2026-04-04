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

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
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

        //ფასი ეკრანზე უნდა ჩანდეს 2 სხვადასხვა TextView-ში
        val priceView : TextView = findViewById(R.id.priceView)
        val pricetotalView : TextView = findViewById(R.id.pricetotalView)

        //რადგან ფასი Double ტიპის უნდა ყოფილიყო, პირდაპირ String-ის გადმოცემა "$38,000"-ად არ იმუშავებდა, ამიტომ
        //საჭირო იყო ამ ფასის ვალუტად კონვერტირება.
        var formattedPrice = java.text.NumberFormat.getCurrencyInstance().format(carPrice)

        //Express მიწოდების პირობა
        val expressButton: Button = findViewById(R.id.expressButtonView)
        expressButton.setOnClickListener {
            //როგორც კი ღილაკს ეჭირება, მაშინვე ემატება შესაბამისი ფასი
            carPrice += 1700
            formattedPrice = java.text.NumberFormat.getCurrencyInstance().format(carPrice)
            pricetotalView.text = "$formattedPrice"

            //ამის გარეშე თუ ღილაკს 10-ჯერ დავაჭერდით 10-ჯერვე დააკლდებოდა 1700$
            expressButton.isEnabled = false

            //თუ Express მიწოდება მონიშნეს, საჭიროა ლურჯი მოსანიშნი ტიჭკაც ქვევით ჩამოვიდეს
            val checkMark : ImageView = findViewById(R.id.checkmarkView)
            val checkMarkParameters = checkMark.layoutParams as ConstraintLayout.LayoutParams

            //სწავლა მომიწია იმისა თუ XML-ის Constraint-ები როგორ უნდა შემეცვალა ამ კოტლინ ფაილიდან
            checkMarkParameters.bottomToBottom = R.id.circleView2
            checkMarkParameters.startToStart = R.id.circleView2
            checkMarkParameters.topToTop = R.id.circleView2
            checkMark.layoutParams = checkMarkParameters
        }
        priceView.text = "$formattedPrice"
        pricetotalView.text = "$formattedPrice"

        //Pay ღილაკზე დაჭერისას გადავდივართ მესამე Activity-ზე
        val payButton: Button = findViewById(R.id.payButtonView)
        payButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}