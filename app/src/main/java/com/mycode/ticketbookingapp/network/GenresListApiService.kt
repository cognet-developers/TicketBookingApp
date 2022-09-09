package com.mycode.ticketbookingapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.themoviedb.org/3/genre/movie/list?api_key=af7198a57a1e538eee1c1b0d13c352c4&language=en-US
//https://api.themoviedb.org/3/list/35?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4
//https://api.themoviedb.org/3/movie/11385?api_key=af7198a57a1e538eee1c1b0d13c352c4&append_to_response=videos,images


enum class MarsApiFilter(val value:String){
    Family("10751?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4"),Action("28?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4"),
    Animation("16?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4"),Comedy("35?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4"),
    Crime("80?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4"),History("?page=1&api_key=af7198a57a1e538eee1c1b0d13c352c4")
}
private const val BASE_URL = "https://api.themoviedb.org/4/list/"

private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()



interface GenresListApiService{
    @GET("realestate")
    fun getProperties(@Query("filter") type:String):
            Deferred<List<GenresListProperty>>
}

object GenresListApi{
    val retrofitService:GenresListApiService by lazy{
        retrofit.create(GenresListApiService::class.java)
    }
}