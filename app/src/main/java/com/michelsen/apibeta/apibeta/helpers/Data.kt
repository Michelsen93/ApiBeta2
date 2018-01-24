package com.michelsen.apibeta.apibeta.helpers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.michelsen.apibeta.apibeta.models.Account
import com.michelsen.apibeta.apibeta.models.Accounts
import com.michelsen.apibeta.apibeta.models.Token

class Data (context: Context) {
    var accounts: List<Account>
    var token: Token? = null
    private var endpointHelper: EndpointHelper? = null

    init {
        accounts = ArrayList<Account>()
        endpointHelper = EndpointHelper(context)
        getToken();
    }

    private fun getToken(){
        endpointHelper?.getBearerToken(completion = { accessToken ->
            this.token = Gson().fromJson(accessToken.content, Token::class.java)
        })
    }

    fun getAccounts(){
        endpointHelper?.getAccounts(token?.token!!, completion = { accounts ->
            this.accounts = Gson().fromJson<List<Account>>(accounts.content, Accounts::class.java)
        })
    }

}