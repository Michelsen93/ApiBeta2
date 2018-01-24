package com.michelsen.apibeta.apibeta.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.EndpointHelper
import com.michelsen.apibeta.apibeta.models.Token
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var token: Token? = null
    private var list: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var endpointHelper = EndpointHelper(this)
        endpointHelper.getBearerToken(completion = { accessToken ->
            token = Gson().fromJson(accessToken.content, Token::class.java)
            endpointHelper.token = token?.token!!
        })


    }


    fun buttonClick(view: View) {
        text_view.text = token?.token
    }

    fun buttonClick2(view: View) {
        text_view.text = list
    }


}
