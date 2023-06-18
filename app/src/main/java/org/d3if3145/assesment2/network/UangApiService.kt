package org.d3if3145.assesment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3145.assesment2.model.Uang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/rizkimardii/json-uang/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UangApiService {
    @GET("static-api.json")
    suspend fun getUang(): List<Uang>

}

object UangApi {
    val service: UangApiService by lazy {
        retrofit.create(UangApiService::class.java)
    }

    fun getUangUrl(gambar: String): String {
        return "$BASE_URL$gambar.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }

