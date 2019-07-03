package com.example.sampleesp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    lateinit var ip: String
    lateinit var wifiName: String
    lateinit var wifiPass: String
    lateinit var editWName: EditText
    lateinit var editWPass: EditText
    lateinit var checkShowPass: CheckBox
    lateinit var editIP: EditText
    lateinit var saveBtn: Button
    lateinit var connectBtn: Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editIP = findViewById(R.id.editIP)
        saveBtn = findViewById(R.id.saveIp)
        connectBtn = findViewById(R.id.connectBtn)
        editWName = findViewById(R.id.wifiName)
        editWPass = findViewById(R.id.wifiPass)
        checkShowPass = findViewById(R.id.checkShowPass)

        toolbar = findViewById(R.id.toolbar2)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        var saved = false

        editIP.isFocusable = true
        editIP.isFocusableInTouchMode = true

        connectBtn.isEnabled=false

//        ip = editIP.text.toString()


        checkShowPass.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                editWPass.transformationMethod = null
            } else {
                editWPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }


        saveBtn.setOnClickListener {
            if (!saved) {
                if (!editIP.text.isNullOrEmpty() && editWName.text.isNotEmpty() && editWPass.text.isNotEmpty()) {
                    editIP.isFocusable = false
                    editIP.isFocusableInTouchMode = false
                    editIP.isClickable = false
                    editIP.isEnabled = false

                    editWPass.isFocusable = false
                    editWPass.isFocusableInTouchMode = false
                    editWPass.isClickable = false
                    editWPass.isEnabled = false

                    editWName.isFocusable = false
                    editWName.isFocusableInTouchMode = false
                    editWName.isEnabled = false
                    editWName.isClickable = false

                    checkShowPass.isEnabled = false
                    connectBtn.isEnabled=true

                    ip = editIP.text.toString()
                    wifiName = editWName.text.toString()
                    wifiPass = editWPass.text.toString()

                    saved = true
                    saveBtn.text = "ویرایش"
                    saveBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
                    hideKeyboard(this)
                } else {
                    Toast.makeText(this, "آی پی وارد نشده است!", Toast.LENGTH_SHORT).show()
                }
            } else {
                editIP.isFocusable = true
                editIP.isFocusableInTouchMode = true
                editIP.isClickable = true
                editIP.isEnabled = true

                editWPass.isFocusable = true
                editWPass.isFocusableInTouchMode = true
                editWPass.isClickable = true
                editWPass.isEnabled = true

                editWName.isFocusable = true
                editWName.isFocusableInTouchMode = true
                editWName.isClickable = true
                editWName.isEnabled = true

                checkShowPass.isEnabled = true
                connectBtn.isEnabled=false

                saved = false
                saveBtn.text = "ذخیره"
                saveBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            }
        }

        connectBtn.setOnClickListener {
            if (saved && editIP.text.isNotEmpty()) {
                startActivity(
                    Intent(this@MainActivity, ButtonPage::class.java).putExtra("ip", ip)
                        .putExtra("wifiName", wifiName)
                        .putExtra("wifiPass", wifiPass)
                )
            } else if (editIP.text.isEmpty()){
                Toast.makeText(this, "آدرس ip نمی تواند خالی باشد!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}
