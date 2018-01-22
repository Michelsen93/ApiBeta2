package com.michelsen.apibeta.apibeta.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.EndpointHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var endpointHelper: EndpointHelper = EndpointHelper()
        endpointHelper.getBearerToken()
        text_view.setText(endpointHelper.getBearerToken());
    }
}
