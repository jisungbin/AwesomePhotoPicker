package me.sungbin.awesomephotopicker

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import me.sungbin.awesomephotopicker.library.view.ui.AwesomePhotoPicker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1000
        )

        btn_test.setOnClickListener {
            AwesomePhotoPicker().show(supportFragmentManager, "")
        }
    }
}