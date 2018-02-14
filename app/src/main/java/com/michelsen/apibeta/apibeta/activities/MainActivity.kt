package com.michelsen.apibeta.apibeta.activities

import android.accounts.Account
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.helpers.AccountHelper
import com.michelsen.apibeta.apibeta.helpers.Data
import com.michelsen.apibeta.apibeta.helpers.EndpointHelper
import com.michelsen.apibeta.apibeta.models.Accounts
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var endpointHelper: EndpointHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        endpointHelper = EndpointHelper(this)
        endpointHelper.getTokenObservable()?.subscribeOn(Schedulers.io())?.subscribe({ token ->
            endpointHelper.getAccountsObservable(token!!.token)?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())?.subscribe({ accounts ->
                var defaultAccount = AccountHelper().getDefaultAccount(accounts!!)
                text_view.text = defaultAccount.name + ": " + defaultAccount.balance
            })
        })

    }
}
