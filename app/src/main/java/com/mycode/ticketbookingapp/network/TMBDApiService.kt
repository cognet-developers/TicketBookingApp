package com.mycode.ticketbookingapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.themoviedb.org/3/list/35?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4
//https://api.themoviedb.org/3/movie/11385?api_key=af7198a57a1e538eee1c1b0d13c352c4&append_to_response=videos,images
//Api_Key="af7198a57a1e538eee1c1b0d13c352c4"

private const val BASE_URL = "https://api.themoviedb.org"

private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()



interface TMBDApiService{
    @GET("/3/list/{id}")
    fun getGenresList(@Path("id") id : String, @Query("api_key") api_key : String) : Call<GenresListProperty>

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<MovieDetailsProperty>

}

object TMBDApi{
    val retrofitService:TMBDApiService by lazy{
        retrofit.create(TMBDApiService::class.java)
    }
}
