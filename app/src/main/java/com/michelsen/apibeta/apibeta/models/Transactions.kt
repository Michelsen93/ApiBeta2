package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName

data class Transactions (
        @SerializedName("items") val transactions: List<Transaction>
)