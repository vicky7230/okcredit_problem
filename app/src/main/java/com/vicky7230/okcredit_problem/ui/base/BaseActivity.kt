package com.vicky7230.okcredit_problem.ui.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.vicky7230.okcredit_problem.R
import com.vicky7230.okcredit_problem.utils.CommonUtils

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var progressDialog: Dialog? = null

    fun hasPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true
        return permissions.none {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    private fun displayMessage(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun displayError(message: String) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundResource(R.drawable.curved_bg_error)
        snackBar.show()
    }

    fun showLoading() {
        hideLoading()
        progressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoading() {
        if (progressDialog != null) {
            if (progressDialog?.isShowing == true)
                progressDialog?.cancel()
        }
    }

    fun showMessage(message: String?) {
        if (message != null)
            displayMessage(message)
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showError(message: String?) {
        if (message != null)
            displayError(message)
    }

}