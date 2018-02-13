package com.michelsen.apibeta.apibeta.models

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("access_token") var token: String
)