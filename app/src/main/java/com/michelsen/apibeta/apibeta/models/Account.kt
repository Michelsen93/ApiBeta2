package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName

/**
 * Created by micole on 29.01.2018.
 */
data class Account (
        @SerializedName("accountNumber") val accountNumber: String,
        @SerializedName("customerId") val customerId: String,
        @SerializedName("ownerCustomerId") val ownerCustomerId: String,
        @SerializedName("name") val name: String,
        @SerializedName("accountType") val accountType: String,
        @SerializedName("available") val available: Double,
        @SerializedName("balance") val balance: Double,
        @SerializedName("creditLimit") val creditLimit: Double,
        @SerializedName("defaultAccount") val defaultAccount: Boolean
)
