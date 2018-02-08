package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Transaction (
        @SerializedName("transactionId") val transactionId: String,
        @SerializedName("customerId") val customerId: String,
        @SerializedName("accountNumber") val accountNumber: String,
        @SerializedName("otherAccountNumber") val otherAccountNumber: String,
        @SerializedName("amount") val amount: Double,
        @SerializedName("text") val text: String,
        @SerializedName("transactionType") val transactionType: String,
        @SerializedName("registrationDate") val registrationDate: Date,
        @SerializedName("accountingDate") val accountingDate: Date,
        @SerializedName("interestDate") val interestDate: Date
)