package com.application.kyucsa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent= Intent(this,WebActivity::class.java)
            startActivity(intent)
            finish()
        },4000)

    }
}