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
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess

class WebActivity : AppCompatActivity()
{
    private lateinit var   webView:WebView
    private var URL="https://simenya.github.io/kyucsa/"
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        checkInternetConnection(this)

        webViewContent()
        //Invocation of the bottom Navigation config fun
        bottomNavigation()

    }

    private fun webViewContent() {
        //Checking for Internet connection
        checkInternetConnection(this)
        //Setting up the URL link
        webView = findViewById(R.id.web)
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(URL)
            settings.javaScriptEnabled=true
        }
    }

    //Bottom Navigation Config
    private fun bottomNavigation() {
        bottomNav = findViewById(R.id.bottomNavId)
        bottomNav.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.homeBtnId -> {
                    webViewContent()
                }
                R.id.logoutBtnId -> {
                    logoutConfirmation(this)
                    //Toast.makeText(this,"this is Home", Toast.LENGTH_LONG).show()
                }
                R.id.aboutBtnId -> {
                    aboutPage(this)
                }

            }
            true
        }
    }

    //Logout Dialogue Alert
    private fun logoutConfirmation(context: Context) {
        val dialog = Dialog(context)

        dialog.setContentView(R.layout.logout_alert_dialog)

        dialog.setCanceledOnTouchOutside(false)

        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )

        dialog.findViewById<Button>(R.id.confirmLogoutBtnId)
            .setOnClickListener {
                Firebase.auth.signOut()
                finish()
                Toast.makeText(this,"Signed Out Successfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LandingActivity::class.java))
            }
        dialog.findViewById<Button>(R.id.cancelLogoutBtnId)
            .setOnClickListener {
                dialog.dismiss()
            }

        dialog.show()
    }

    //About Page Dialog
    private fun aboutPage(context: Context) {
        val dialog = Dialog(context)

        dialog.setContentView(R.layout.about_dialog)

        dialog.setCanceledOnTouchOutside(false)

        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
//        dialog.window!!.setBackgroundDrawable(
//            ColorDrawable(
//                Color.GREEN
//            )
//        )

        dialog.findViewById<Button>(R.id.cancelAboutBtnId)
            .setOnClickListener {
                dialog.dismiss()
            }

        dialog.show()
    }

    override fun onBackPressed() {
        checkInternetConnection(this)
        if (webView.canGoBack()){
            checkInternetConnection(this)
            webView.goBack()
        } else{
            super.onBackPressed()
            checkInternetConnection(this)
        }

    }


    private fun checkInternetConnection(context:Context) {

        val manager = applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val networkIfo = manager.activeNetworkInfo

        if (networkIfo==null) {

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
                    checkInternetConnection(context)
                }

            dialog.show()
        }
    }


}

