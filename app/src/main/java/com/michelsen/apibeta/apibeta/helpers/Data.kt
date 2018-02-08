package com.michelsen.apibeta.apibeta.helpers

import android.content.Context
import com.google.gson.Gson
import com.michelsen.apibeta.apibeta.models.Account
import com.michelsen.apibeta.apibeta.models.Accounts
import com.michelsen.apibeta.apibeta.models.Token

class Data(context: Context){
    var accounts: Accounts? = null
    var token: Token? = null
    private var endpointHelper: EndpointHelper? = null

    init {

        endpointHelper = EndpointHelper(context)

        getToken()


    }

    private fun getToken() {
        endpointHelper?.getBearerToken(completion = { accessToken ->
            this.token = Gson().fromJson(accessToken.content, Token::class.java)
        })
        /*
        val accessToken = endpointHelper?.getBearerTokenSync()
        this.token = Gson().fromJson(accessToken.content, Token::class.java)
        */
    }

    fun getAccounts() {
        endpointHelper?.getAccounts(token?.token!!, completion = { accounts ->
            this.accounts = Gson().fromJson(accounts.content, Accounts::class.java)
        })
    }

    fun getDefaultAccount(): Account? {
        return accounts!!.accounts.firstOrNull { it.defaultAccount };
    }

}