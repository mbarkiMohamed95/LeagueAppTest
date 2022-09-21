package com.example.leagueapptest.domaine.Network.service


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class NetworkHelper {

    companion object {
        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }

        private fun getClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .build()


        fun <T> getApi(service: Class<T>, baseURL: String): T {

            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service)
        }


    }

}