package com.michelsen.apibeta.apibeta.helpers



import android.content.Context
import android.content.res.Resources
import com.github.kittinunf.fuel.Fuel
import com.michelsen.apibeta.apibeta.R
import java.util.*

/**
 * Created by Ole-Martin Michelsen on 20.01.2018.
 */
class EndpointHelper (context: Context){
    private var clientId : String
    private val baseUrl = "https://api.sbanken.no/"
    private var appSecret : String
    val accountsPrefix = "api/v1/Accounts/"
    private val identityServerPrefix = "IdentityServer/connect/token"
    private var credentials :String
    var headers: MutableMap<String, String> = mutableMapOf()
    var token: String = "asd"

    init {
        headers.put("Accept", "application/json")
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
        clientId = context.getString(R.string.client_id)
        appSecret =  context.getString(R.string.app_secret)
        credentials = clientId + ":" + appSecret
        headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString(credentials.toByteArray()))
    }

   /* fun getAccounts(customerId: String): List<String> {
        Fuel.get(baseUrl).response() {request, response, result ->

        }
        return 0;
    } */
    //TODO: base64 credentials, body contain the missing peace

    fun getBearerToken(completion : (accessToken: String ) -> Unit){

        Fuel.post(baseUrl + identityServerPrefix)
                .header(headers)
                .body("grant_type=client_credentials")
                .response { request, response, result ->

            completion(response.toString())
        }

    }

    fun registerToken(response: String) : Unit{
        token = response;
    }
}
