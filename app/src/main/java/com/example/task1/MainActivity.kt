package com.example.task1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task1.databinding.ActivityMainBinding

/*XML ფაილებში არამგონია საჭირო იყოს რაიმე კომენტარების წერა, რადგან mark-up language არის წესით და რიგით ყველაფერი რაც გაკეთებული მაქვს თვითახსნადი უნდა იყოს.
* ერთადერთი რაც აღსანიშნავია არის ის, რომ მანქანების ღილაკებს პირველ გვერდზე რომ მომრგვალებული მართხკუთხედები ჰქონოდათ Background-ად საჭირო იყო res/drawable
* დირექტორიაში round_button მარტივი custom background-ის გაკეთება.*/

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ღილაკზე დაჭერის Listeners, რომლებიც ღილაკზე დაჭერისას Activity-ს ცვლიან და გარდა ამისა
        //მეორე Activity-ს აწვდიან მანქანის სახელისა და ფასის მონაცემებს. ფასი, რადგან Double ტიპად
        //იყო მოთხოვნილი შესაბამისად .0-ები უწერია ყველას ბოლოში

        binding.car1Button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("carName","BMW M3 (F80 generation)")
            intent.putExtra("carPrice",38000.0)
            startActivity(intent)
        }


        binding.car2Button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("carName","Mercedes-Benz CLA-Class\n (Second Generation)")
            intent.putExtra("carPrice",46400.0)
            startActivity(intent)
        }


        binding.car3Button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("carName","Porsche 911 GT3 RS\n (991.1 Generation)")
            intent.putExtra("carPrice",189000.0)
            startActivity(intent)
        }


        binding.car4Button.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("carName","Ferrari 488 Spider")
            intent.putExtra("carPrice",260000.0)
            startActivity(intent)
        }
    }
}