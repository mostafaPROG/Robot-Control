package com.example.sampleesp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleesp.api.ApiClientESP
import com.example.sampleesp.api.ESPApib
import me.zhanghai.android.materialprogressbar.MaterialProgressBar
import retrofit2.Call
import retrofit2.Callback
import java.util.logging.Logger


class ButtonPage : AppCompatActivity(), View.OnTouchListener {

    lateinit var editBtm: ImageView
    lateinit var saveBtm: ImageView
    var editstatus: Boolean = false
    lateinit var editButton1: EditText
    lateinit var editButton2: EditText
    lateinit var editButton3: EditText
    lateinit var editButton4: EditText
    lateinit var editButton5: EditText
    lateinit var editButton6: EditText
    lateinit var editButton7: EditText
    lateinit var editButton8: EditText
    lateinit var editButton9: EditText
    lateinit var editButton10: EditText

    lateinit var Button1: ImageButton
    lateinit var Button2: ImageButton
    lateinit var Button3: ImageButton
    lateinit var Button4: ImageButton
    lateinit var Button5: ImageButton
    lateinit var Button6: ImageButton
    lateinit var Button7: ImageButton
    lateinit var Button8: ImageButton
    lateinit var Button9: ImageButton
    lateinit var Button10: ImageButton

    lateinit var textLoading: TextView
    lateinit var progressLoading: MaterialProgressBar

    var ipAddress: String = "http://192.168.43.147"  // ur ip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button_activity)

        initUI()
        wifiHotspotConf()
        onTouchClicked()

        ipAddress = intent.getStringExtra("ip")


    }

    override fun onResume() {
        Handler().postDelayed({
            if (ap.isApOn) {
                textLoading.visibility = View.GONE
                progressLoading.visibility = View.GONE
            }
        }, 8000)
        super.onResume()
    }

    private fun wifiHotspotConf() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this.applicationContext)) {

            } else {
                val intent = Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + this.packageName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent);
            }
        }
        CreateNewWifiApNetwork()
    }

    private lateinit var ap: ApManager

    fun CreateNewWifiApNetwork() {

        ap = ApManager(this.applicationContext)
        ap.createNewNetwork("mostafa", "pro192016")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchClicked() {
        Button1.setOnTouchListener(this)
        Button2.setOnTouchListener(this)
        Button3.setOnTouchListener(this)
        Button4.setOnTouchListener(this)
        Button5.setOnTouchListener(this)
        Button6.setOnTouchListener(this)
        Button7.setOnTouchListener(this)
        Button8.setOnTouchListener(this)
        Button9.setOnTouchListener(this)
        Button10.setOnTouchListener(this)
    }

    private fun initUI() {
        editBtm = findViewById(R.id.editName)
        saveBtm = findViewById(R.id.saveName)
        editButton1 = findViewById(R.id.editButton1)
        editButton2 = findViewById(R.id.editButton2)
        editButton3 = findViewById(R.id.editButton3)
        editButton4 = findViewById(R.id.editButton4)
        editButton5 = findViewById(R.id.editButton5)
        editButton6 = findViewById(R.id.editButton6)
        editButton7 = findViewById(R.id.editButton7)
        editButton8 = findViewById(R.id.editButton8)
        editButton9 = findViewById(R.id.editButton9)
        editButton10 = findViewById(R.id.editButton10)

        Button1 = findViewById(R.id.button1)
        Button2 = findViewById(R.id.button2)
        Button3 = findViewById(R.id.button3)
        Button4 = findViewById(R.id.button4)
        Button5 = findViewById(R.id.button5)
        Button6 = findViewById(R.id.button6)
        Button7 = findViewById(R.id.button7)
        Button8 = findViewById(R.id.button8)
        Button9 = findViewById(R.id.button9)
        Button10 = findViewById(R.id.button10)

        progressLoading = findViewById(R.id.loadingWifi)
        textLoading = findViewById(R.id.textLoadingWifi)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun editClick(view: View) {
        if (!editstatus && view.id == R.id.editName) {

            editButton1.isEnabled = true
            editButton2.isEnabled = true
            editButton3.isEnabled = true
            editButton3.isEnabled = true
            editButton4.isEnabled = true
            editButton5.isEnabled = true
            editButton6.isEnabled = true
            editButton7.isEnabled = true
            editButton8.isEnabled = true
            editButton9.isEnabled = true
            editButton10.isEnabled = true
            editstatus = true
            saveBtm.visibility = View.VISIBLE

        } else if (editstatus && view.id == R.id.saveName) {

            editButton1.isEnabled = false
            editButton2.isEnabled = false
            editButton3.isEnabled = false
            editButton3.isEnabled = false
            editButton4.isEnabled = false
            editButton5.isEnabled = false
            editButton6.isEnabled = false
            editButton7.isEnabled = false
            editButton8.isEnabled = false
            editButton9.isEnabled = false
            editButton10.isEnabled = false
            editstatus = false
            saveBtm.visibility = View.INVISIBLE
            hideKeyboard(this@ButtonPage)
        }
    }

    private var apiInterface: ESPApi? = null
    lateinit var call: Call<MyCalback>
    private fun sendByApi(mes: String) {
        if (apiInterface == null)
            apiInterface = ApiClientESP().createApi(ipAddress)!!

        call = apiInterface!!.setLED(mes)!!
        call.enqueue(object : Callback<MyCalback> {
            override fun onFailure(call: Call<MyCalback>, t: Throwable) {

            }

            override fun onResponse(call: Call<MyCalback>, response: retrofit2.Response<MyCalback>) {

            }

        })

    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val message: String
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Logger.getLogger("button Press").warning("${v?.tag} Pressed !")
                message = v!!.tag.toString()
                sendByApi(message)
            }
            MotionEvent.ACTION_UP -> {
                Logger.getLogger("button Press").warning("${v?.tag} Released !")
                message = "0"
                sendByApi(message)
            }

        }

        return v?.onTouchEvent(event) ?: true
    }

    override fun onDestroy() {
        ap.turnWifiApOff()
        super.onDestroy()
    }


}