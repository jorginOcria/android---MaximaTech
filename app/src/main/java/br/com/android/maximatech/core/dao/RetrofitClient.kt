package br.com.android.maximatech.core.dao

import br.com.android.maximatech.core.cross.Constants
import br.com.android.maximatech.core.cross.listener.CommonsHTTPListener
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(commonsHTTPStatusListener: CommonsHTTPListener?): Retrofit {
            val httpClient = OkHttpClient.Builder()
            adicionandoTimeOutNaChamadaDeTodosEndPoints(httpClient)

            httpClient.eventListener(object : EventListener() {
                override fun callEnd(call: Call) {
                    super.callEnd(call)
                }

                override fun callStart(call: Call) {
                    super.callStart(call)
                }
            })

            httpClient.addInterceptor { chain ->
                var builder = chain.request().newBuilder()
                val request = builder.build()
                val response = chain.proceed(request)
                handleResponse(response, commonsHTTPStatusListener)
                response
            }

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API.REST_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        private fun adicionandoTimeOutNaChamadaDeTodosEndPoints(httpClient: OkHttpClient.Builder) {
            httpClient.connectTimeout(Constants.TIMEOUT.ENDPOINTS_GERAIS, TimeUnit.SECONDS)
            httpClient.readTimeout(Constants.TIMEOUT.ENDPOINTS_GERAIS, TimeUnit.SECONDS)
        }

        fun <T> createService(
            serviceClass: Class<T>,
            commonsHTTPStatusListener: CommonsHTTPListener?
        ): T {
            return getRetrofitInstance(commonsHTTPStatusListener)
                .create(serviceClass)
        }

        private fun handleResponse(
            response: Response,
            commonsHTTPStatusListener: CommonsHTTPListener?
        ) {
            if (response.code() == Constants.HTTP.STATUS_CODE.ERRO_INTERNO_NO_SERVIDOR
            ) {
                commonsHTTPStatusListener?.onReceiveCommonHTTPStatus(response.code())
            }
        }

    }
}