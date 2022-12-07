package com.application.kyucsa

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import com.application.kyucsa.databinding.ActivityLandingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        checkInternetConnection(this)

        // removing the screen's tools bar and enabling fullscreen mode
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //Define firebase variable auth
        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    //check if the current user is already logged in and take them to the MainActivity
    override fun onStart() {
        if (auth.currentUser != null){
            startActivity(Intent(this, WebActivity::class.java))
            finish()
        }
        super.onStart()
    }

    fun checkInternetConnection(context: Context) {

        val manager = applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val networkIfo = manager.activeNetworkInfo

        if (null == networkIfo) {

            val dialog = Dialog(context)

            dialog.setContentView(R.layout.alert_internet_dialog)

            dialog.setCanceledOnTouchOutside(false)

            dialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(
                    Color.GREEN
                )
            )

            dialog.findViewById<Button>(R.id.btn_try_again)
                .setOnClickListener {
                    recreate()
                }

            dialog.show()
        }
    }
}