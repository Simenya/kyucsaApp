package com.application.kyucsa

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.view.WindowManager
import android.widget.Button

class InternetConnectionCheck {
    
    fun checkInternetConnection() {

        val manager = applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val networkIfo = manager.activeNetworkInfo

        if (null == networkIfo) {

            val dialog = Dialog(co)

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