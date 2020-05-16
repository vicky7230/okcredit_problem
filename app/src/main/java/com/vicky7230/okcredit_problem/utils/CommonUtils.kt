package com.vicky7230.okcredit_problem.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.vicky7230.okcredit_problem.R
import java.net.URL


object CommonUtils {

    fun showLoadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun isValidUrl(url: String?): Boolean {
        return try {
            URL(url).toURI()
            true
        } // If there was an Exception
        // while creating URL object
        catch (e: Exception) {
            false
        }
    }
}