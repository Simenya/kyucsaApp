package com.application.kyucsa


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        Handler().postDelayed({
            startActivity(Intent(this,WebActivity::class.java))
            finish()
        },4000)

    }
}