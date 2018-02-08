package com.michelsen.apibeta.apibeta.activities

import android.accounts.Account
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.Data
import com.michelsen.apibeta.apibeta.models.Accounts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mData: Data? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mData = Data(this)

    }

    fun buttonClick(view: View) {
        var defaultAccount = mData?.getDefaultAccount()
        text_view.text = defaultAccount?.name + " : " + defaultAccount?.balance
    }

}
