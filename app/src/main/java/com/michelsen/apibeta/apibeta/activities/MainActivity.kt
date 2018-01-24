package com.michelsen.apibeta.apibeta.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.EndpointHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var token: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var endpointHelper = EndpointHelper(this)
        endpointHelper.getBearerToken(completion = {accessToken ->
            token = accessToken
        })

    }
    fun buttonClick(view: View) {
        text_view.text = token
    }


}
