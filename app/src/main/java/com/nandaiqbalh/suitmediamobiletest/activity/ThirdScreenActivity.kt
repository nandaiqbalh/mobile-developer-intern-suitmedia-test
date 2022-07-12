package com.nandaiqbalh.suitmediamobiletest.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.MaterialToolbar
import com.nandaiqbalh.suitmediamobiletest.R
import com.nandaiqbalh.suitmediamobiletest.adapter.UserAdapter
import com.nandaiqbalh.suitmediamobiletest.data.network.ApiConfig
import com.nandaiqbalh.suitmediamobiletest.data.response.UserResponse
import com.nandaiqbalh.suitmediamobiletest.helper.SharedPrefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var thirdToolbar: MaterialToolbar

    private lateinit var rvUser: RecyclerView

    private lateinit var sharedPrefs: SharedPrefs

    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_screen)

        // init
        init()

        // main button
        mainButton()

        // get data from api
        getData()

        // refresh layout
        refreshScreen()

    }

    private fun init(){

        rvUser = findViewById(R.id.rv_users)

        thirdToolbar = findViewById(R.id.third_toolbar)

        sharedPrefs = SharedPrefs(this)

        refreshLayout = findViewById(R.id.swipe_layout)
    }

    private fun mainButton(){

        thirdToolbar.setOnClickListener {
            onBackPressed()
            finish()
        }

    }

    private fun getData(){

        ApiConfig.getService().getUserList().enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful){
                    val responseUser = response.body()
                    val dataUser = responseUser?.data

                    rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
                    rvUser.adapter = dataUser?.let { it ->
                        UserAdapter(it){

                            sharedPrefs.setValue("selected_user", it.firstName + " " + it.lastName)

                            val intent = Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java)
                            startActivity(intent)
                            finishAffinity()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                Toast.makeText(this@ThirdScreenActivity, "User not found!", Toast.LENGTH_LONG).show()

            }

        })
    }

    private fun refreshScreen(){
        refreshLayout.setOnRefreshListener {
            getData()

            refreshLayout.isRefreshing = false
        }
    }

}

