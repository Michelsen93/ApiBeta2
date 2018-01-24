package com.michelsen.apibeta.apibeta.helpers


import android.content.res.Resources
import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.michelsen.apibeta.apibeta.R
import java.util.*

/**
 * Created by Ole-Martin Michelsen on 20.01.2018.
 */
class EndpointHelper(context: Context) {
    private var clientId: String
    private val baseUrl = "https://api.sbanken.no/"
    private var appSecret: String
    val accountsPrefix = "api/v1/Accounts/"
    private val identityServerPrefix = "IdentityServer/connect/token"
    private var credentials: String
    var headers: MutableMap<String, String> = mutableMapOf()
    var token: String = "asd"

    init {
        headers.put("Accept", "application/json")
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        clientId = context.getString(R.string.client_id)
        appSecret = context.getString(R.string.app_secret)
        credentials = clientId + ":" + appSecret
        headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString(credentials.toByteArray()))
    }

   /* fun getAccounts(completion: (accounts: Json) -> Unit) {
        headers.put("Authorization", "Basic " + token)
        Fuel.get(baseUrl + accountsPrefix)
                .header(headers)
                .responseJson() { request, response, result ->
                    completion(result?.component1()!!)
                }
    }*/
    //TODO: base64 credentials, body contain the missing peace

    fun getBearerToken(completion: (accessToken: Json) -> Unit) {

        Fuel.post(baseUrl + identityServerPrefix)
                .header(headers)
                .body("grant_type=client_credentials")
                .responseJson { request, response, result ->
                    completion(result.component1()!!)
                }
    }

}
