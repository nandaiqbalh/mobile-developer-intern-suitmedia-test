package com.nandaiqbalh.suitmediamobiletest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.nandaiqbalh.suitmediamobiletest.R
import com.nandaiqbalh.suitmediamobiletest.helper.SharedPrefs

class SecondScreenActivity : AppCompatActivity() {
    
    private lateinit var tvName: MaterialTextView
    private lateinit var tvSelectedUser: MaterialTextView

    private lateinit var secondToolbar: MaterialToolbar
    private lateinit var btnChooseUser: MaterialButton

    private lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        // init
        init()

        // btn triggered
        mainButton()

        // set data
        setData()

    }

    private fun init(){

        tvName = findViewById(R.id.tv_name)
        tvSelectedUser = findViewById(R.id.tv_selected)

        secondToolbar = findViewById(R.id.second_toolbar)
        btnChooseUser = findViewById(R.id.btn_choose_user)

        sharedPrefs = SharedPrefs(this)
    }

    private fun mainButton(){

        // back -> toolbar
        secondToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // to third screen
        btnChooseUser.setOnClickListener {
            val intent = Intent(this@SecondScreenActivity, ThirdScreenActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setData(){
        tvName.text = sharedPrefs.getValue("name")

        val selectedUser = sharedPrefs.getValue("selected_user")

        if (selectedUser == "" || selectedUser == null){
            tvSelectedUser.text = "Selected User Name"
        } else {
            tvSelectedUser.text = sharedPrefs.getValue("selected_user")

            sharedPrefs.setValue("selected_user", "")
        }

    }
}