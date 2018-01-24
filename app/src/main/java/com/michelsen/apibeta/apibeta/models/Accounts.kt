package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName

data class Accounts (
        @SerializedName("items") val accounts: List<Account>
)
