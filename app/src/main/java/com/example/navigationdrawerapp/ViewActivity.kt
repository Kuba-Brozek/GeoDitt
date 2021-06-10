package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        var modalItems: Modal = intent.getSerializableExtra("data") as Modal;

        Log.e("name", modalItems.name.toString());

        var viewName: TextView = findViewById(R.id.viewName)
        var viewName2: TextView = findViewById(R.id.viewName2)
        var viewImage: ImageView = findViewById(R.id.viewImage)

        viewName.text = modalItems.name;
        viewName2.text = modalItems.describe;
        viewImage.setImageResource(modalItems.image!!);
    }
}