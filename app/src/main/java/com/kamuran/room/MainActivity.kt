package com.kamuran.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))//geri tuşu


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController= findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}