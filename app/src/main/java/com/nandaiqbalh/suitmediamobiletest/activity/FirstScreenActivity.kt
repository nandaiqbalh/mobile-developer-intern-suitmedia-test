package com.nandaiqbalh.suitmediamobiletest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.nandaiqbalh.suitmediamobiletest.R
import com.nandaiqbalh.suitmediamobiletest.helper.SharedPrefs

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var edtName: TextInputEditText
    private lateinit var edtPalindrome: TextInputEditText

    private lateinit var btnCheck: MaterialButton
    private lateinit var btnNext: MaterialButton

    private lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        // init variable
        init()

        // btn triggered
        mainButton()

    }

    private fun init(){

        edtName = findViewById(R.id.edt_name)
        edtPalindrome = findViewById(R.id.edt_palindrome)

        btnCheck = findViewById(R.id.btn_check)
        btnNext = findViewById(R.id.btn_next)

        sharedPrefs = SharedPrefs(this)
    }

    private fun mainButton(){

        // palindrome button check
        btnCheck.setOnClickListener{
            // check wether the input is empty or not
            if (edtPalindrome.text.toString().isEmpty()) {
                messageDialog("Invalid", "Please enter the text you want to check whether it is palindrome or not!")
            } else {
                if (checkPalindrome(edtPalindrome.text.toString().replace("\\s".toRegex(), ""))) {
                    messageDialog("Result", "${edtPalindrome.text.toString().trim()} isPalindrome")
                } else {
                    messageDialog("Result", "${edtPalindrome.text.toString().trim()} is not palindrome")
                }
            }
        }

        // btn next
        btnNext.setOnClickListener {
            if (edtName.text.toString().isEmpty()) {
                messageDialog("Invalid", "Please input the name field first!")
            } else {
                sharedPrefs.setValue("name", edtName.text.toString().trim())
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun checkPalindrome(text: String): Boolean {
        val reverseString = text.reversed()
        return text.equals(reverseString, ignoreCase = true)
    }

    private fun messageDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}