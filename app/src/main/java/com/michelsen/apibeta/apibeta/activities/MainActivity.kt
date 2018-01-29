package com.michelsen.apibeta.apibeta.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.Data

class MainActivity : AppCompatActivity() {
    private var mData: Data? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mData = Data(this)




    }


    fun buttonClick(view: View) {
        mData?.getAccounts();
    }

    fun buttonClick2(view: View) {
    }


}
