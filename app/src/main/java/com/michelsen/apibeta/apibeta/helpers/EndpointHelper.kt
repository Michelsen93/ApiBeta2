package com.michelsen.apibeta.apibeta.helpers

import com.github.kittinunf.fuel.Fuel

/**
 * Created by Ole-Martin Michelsen on 20.01.2018.
 */
class EndpointHelper {
    val clientId = "asdasd"
    val baseUrl = "https://api.sbanken.no/"
    val appSecret = "asdasd"
    val accountsPrefix = "api/v1/Accounts/"
    val identityServerPrefix = "IdentityServer/connect/token"
    val credentials = clientId + ":" + appSecret
    var headers: MutableMap<String, String> = mutableMapOf()

    init {
        headers.put("Accept", "application/json")
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        headers.put("Authorization", "Basic " + credentials)

    }

   /* fun getAccounts(customerId: String): List<String> {
        Fuel.get(baseUrl).response() {request, response, result ->

        }
        return 0;
    } */
    //TODO: base64 credentials, body contain the missing peace

    fun getBearerToken(): String {
        var answer: String = "asd"
        Fuel.post(baseUrl + identityServerPrefix)
                .header(headers)
                .body(credentials)
                .response { request, response, result ->
            answer = response.toString()
        }

        return answer
    }
}
