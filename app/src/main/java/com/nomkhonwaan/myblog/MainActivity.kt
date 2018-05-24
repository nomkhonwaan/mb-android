package com.nomkhonwaan.myblog

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoToSecondActivity.setOnClickListener {
            Log.d(this.javaClass.name, "Hey, I got clicked!")
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

}