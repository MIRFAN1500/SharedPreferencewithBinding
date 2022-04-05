package com.irfan.sharedpreferencesample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.irfan.sharedpreferencesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var  btnSave: Button
//    private lateinit var  btnClear: Button
//    private lateinit var  btnView: Button
//    private lateinit var  inputUsername: EditText
//    private lateinit var  inputPassword: EditText
//    private lateinit var  viewUsername: TextView
//    private lateinit var  viewPassword: TextView
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
//        btnSave = findViewById(R.id.btnSave)
//        btnView = findViewById(R.id.btnView)
//        btnClear = findViewById(R.id.btnClear)
//        inputUsername = findViewById(R.id.username)
//        inputPassword = findViewById(R.id.password)
//        viewUsername = findViewById(R.id.viewUsername)
//        viewPassword = findViewById(R.id.viewPassword)
       val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        binding.btnSave.setOnClickListener {
            val id: Int = Integer.parseInt(binding.password.text.toString())
            val name: String = binding.username.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Snackbar.make(binding.root, "Data Saved", Snackbar.LENGTH_LONG).show()
            //Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }
        binding.btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
              binding.viewUsername.setText("default name: ${sharedNameValue}").toString()
              binding.viewPassword.setText("default id: ${sharedIdValue.toString()}")
                Snackbar.make(binding.root, "Data View is Empty", Snackbar.LENGTH_LONG).show()
              //Toast.makeText(this, "Data View is Empty", Toast.LENGTH_SHORT).show()
            } else {
                binding.viewUsername.setText(sharedNameValue).toString()
                binding.viewPassword.setText(sharedIdValue.toString())
                Snackbar.make(binding.root, "Data View is Showed", Snackbar.LENGTH_LONG).show()
                //Toast.makeText(this, "Data View is Showed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnClear.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.viewUsername.setText("")
            binding.viewPassword.setText("")
            Snackbar.make(binding.root, "Data Clear", Snackbar.LENGTH_LONG).show()
            //Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }
    }
}