package com.example.shop.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object ExchangeRateService {
    suspend fun fetchExchangeRate(): Double? {
        return withContext(Dispatchers.IO) {
            val urlString = "https://v6.exchangerate-api.com/v6/b8f5a7e3719986e47a35c315/latest/USD"
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                val jsonResponse = JSONObject(response)
                jsonResponse.getJSONObject("conversion_rates").getDouble("RUB")
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
