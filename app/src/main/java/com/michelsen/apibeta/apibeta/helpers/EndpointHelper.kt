package com.michelsen.apibeta.apibeta.helpers


import android.content.Context
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import com.michelsen.apibeta.apibeta.R
import com.michelsen.apibeta.apibeta.models.Token
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by Ole-Martin Michelsen on 20.01.2018.
 */
class EndpointHelper(context: Context) {
    private var clientId: String
    private val baseUrl = "https://api.sbanken.no/"
    private var userId: String
    private var appSecret: String
    val accountsPrefix = "Bank/api/v1/Accounts/"
    val transactionsPrefix = "Bank/api/v1/Transactions/"
    private val identityServerPrefix = "IdentityServer/connect/token"
    private var credentials: String
    var headers: MutableMap<String, String> = mutableMapOf()


    init {
        headers.put("Accept", "application/json")
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        clientId = context.getString(R.string.client_id)
        appSecret = context.getString(R.string.app_secret)
        userId = context.getString(R.string.user_id)
        credentials = clientId + ":" + appSecret
        headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString(credentials.toByteArray()))
    }


    fun getAccounts(token: String, completion: (accounts: Json) -> Unit) {
        Fuel.get(baseUrl + accountsPrefix + userId)
                .header(mapOf("Authorization" to "Bearer  " + token, "Accept" to "application/json", "Content-Type" to "application/x-www-form-urlencoded; charset=utf-8"))
                .responseJson { request, response, result ->
                    completion(result.component1()!!)
                }
    }

    fun getBearerToken(completion: (accessToken: Json) -> Unit) {
        Fuel.post(baseUrl + identityServerPrefix)
                .header(headers)
                .body("grant_type=client_credentials")
                .responseJson { request, response, result ->
                    completion(result.component1()!!)
                }
    }

    fun getTransactions(token: String, accountNumber: String, completion: (transactions: Json) -> Unit) {
        Fuel.get(baseUrl + transactionsPrefix + userId + "/" + accountNumber)
                .header(mapOf("Authorization" to "Bearer  " + token, "Accept" to "application/json", "Content-Type" to "application/x-www-form-urlencoded; charset=utf-8"))
                .responseJson {request, response, result ->
                    completion(result.component1()!!)
                }

    }

    fun getBearerTokenBlockMode(): Token {
        val (request, response, result) = (baseUrl + identityServerPrefix).httpPost().header(headers).body("grant_type=client_credentials").responseJson();
        return Gson().fromJson(result.component1()?.content, Token::class.java)
    }

    fun observableTokenRequest(): Observable<Token?>? {
        return Observable.defer<Token> {
            return@defer Observable.just(getBearerTokenBlockMode())
        }

    }

}




























