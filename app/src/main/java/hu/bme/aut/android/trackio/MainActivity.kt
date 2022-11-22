package hu.bme.aut.android.trackio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.trackio.model.SharedPrefConfig

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPrefConfig.init(applicationContext)
    }
}