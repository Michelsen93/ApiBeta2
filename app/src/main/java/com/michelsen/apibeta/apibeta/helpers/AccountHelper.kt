package com.michelsen.apibeta.apibeta.helpers

import com.michelsen.apibeta.apibeta.models.Account
import com.michelsen.apibeta.apibeta.models.Accounts

class AccountHelper {
    fun getDefaultAccount(accounts: Accounts): Account {
        return accounts!!.accounts.firstOrNull { !it.defaultAccount }!!
    }
}