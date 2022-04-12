package com.example.login_kt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val txt:TextView=findViewById(R.id.txt)
        val btn:Button=findViewById(R.id.button)
        val btn1:Button=findViewById(R.id.button1)

        txt.text=intent.getStringExtra("UserName");

        /*val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)*/

        btn.setOnClickListener{
            finish()
            txt.text=""
        }
        btn1.setOnClickListener{
            /*val intent1 = Intent(this, BluetoothActivity::class.java)
            startActivity(intent1)*/
        }

    }
}