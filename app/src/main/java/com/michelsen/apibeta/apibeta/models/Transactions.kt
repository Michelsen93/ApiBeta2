package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName

/**
 * Created by micole on 08.02.2018.
 */
data class Transactions (
        @SerializedName("items") val transactions: List<Transaction>
)