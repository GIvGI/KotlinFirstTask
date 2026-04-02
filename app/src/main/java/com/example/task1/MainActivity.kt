package com.example.task1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: ImageButton = findViewById(R.id.car1Button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("car1name","BMW M3 (F80 generation)")
            intent.putExtra("car1price","$38,000")
            startActivity(intent)
        }


        val button2: ImageButton = findViewById(R.id.car2Button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("car2name","Mercedes-Benz CLA-Class (Second Generation)")
            intent.putExtra("car2price","$46,400")
            startActivity(intent)
        }


        val button3: ImageButton = findViewById(R.id.car3Button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("car3name","Porsche 911 GT3 RS (991.1 Generation)")
            intent.putExtra("car3price","$189,000")
            startActivity(intent)
        }


        val button4: ImageButton = findViewById(R.id.car4Button)
        button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("car4name","Ferrari 488 Spider")
            intent.putExtra("car4price","$260,000")
            startActivity(intent)
        }
    }
}