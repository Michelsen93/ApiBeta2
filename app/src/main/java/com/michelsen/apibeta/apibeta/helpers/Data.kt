package com.michelsen.apibeta.apibeta.helpers

import android.content.Context
import com.google.gson.Gson
import com.michelsen.apibeta.apibeta.models.Account
import com.michelsen.apibeta.apibeta.models.Accounts
import com.michelsen.apibeta.apibeta.models.Token
import com.michelsen.apibeta.apibeta.models.Transactions
import io.reactivex.schedulers.Schedulers

class Data(context: Context){
    var accounts: Accounts? = null
    var token: Token? = null
    var transactions: Transactions? = null
    var endpointHelper: EndpointHelper? = null

    init {
        endpointHelper = EndpointHelper(context)
        endpointHelper?.getTokenObservable()?.subscribeOn(Schedulers.io())?.subscribe( { result ->
            this.token = result!!
        })
    }

    private fun getToken() {
        endpointHelper?.getBearerToken(completion = { accessToken ->
            this.token = Gson().fromJson(accessToken.content, Token::class.java)
            getAccounts()
        })
    }

    fun getAccounts() {
        endpointHelper?.getAccounts(token?.token!!, completion = { accounts ->
            this.accounts = Gson().fromJson(accounts.content, Accounts::class.java)
        })
    }

    //TODO : Better filter when filter is needed, This is just special for my account
    fun getDefaultAccount(): Account? {
        return accounts!!.accounts.firstOrNull { !it.defaultAccount };
    }

    fun getTransactionsFromAccount(account: Account) {
        endpointHelper?.getAccounts(token?.token!!, completion = { transactions ->
            this.transactions = Gson().fromJson(transactions.content, Transactions::class.java)
        })
    }

    fun initAccounts(token: Token): Unit {
        endpointHelper?.getAccountsObservable(token!!.token)?.subscribeOn(Schedulers.io())?.subscribe( { result ->
            this.accounts = result!!
        })
    }



}