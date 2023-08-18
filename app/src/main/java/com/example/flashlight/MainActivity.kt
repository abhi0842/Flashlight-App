package com.example.flashlight

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var toggleBtn: ToggleButton

    lateinit var torchStatusTV: TextView


    lateinit var cameraManager: CameraManager

    lateinit var cameraID: String

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleBtn = findViewById(R.id.togglebutton)
        torchStatusTV = findViewById(R.id.textview)


        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {

            cameraID = cameraManager.cameraIdList[0]
        } catch (e: Exception) {

            e.printStackTrace()
        }


        toggleBtn.setOnClickListener {

            if (toggleBtn.isChecked) {
                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                        cameraManager.setTorchMode(cameraID, true)

                        Toast.makeText(this@MainActivity, "Torch turned on..", Toast.LENGTH_LONG)
                            .show()

                        torchStatusTV.setText("Torch On")
                    }
                } catch (e: Exception) {

                    e.printStackTrace()
                }
            } else {

                try {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraID, false)
                        Toast.makeText(this@MainActivity, "Torch turned off..", Toast.LENGTH_SHORT)
                            .show()
                        torchStatusTV.setText("Torch Off")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    override fun finish() {
        super.finish()

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                cameraManager.setTorchMode(cameraID, false)
            }

            Toast.makeText(this@MainActivity, "Torch turned off..", Toast.LENGTH_SHORT)
                .show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}