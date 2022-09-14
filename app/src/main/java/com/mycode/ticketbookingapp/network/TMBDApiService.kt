package com.mycode.ticketbookingapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    fun getMovieDetails(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<MovieDetails>

    @GET("/3/movie/{id}/videos")
    fun getMovieTrailer(@Path("id") id : String, @Query ("api_key") api_key: String) : Call<videos>

    @GET("/3/genre/movie/list")
    fun getGenres(@Query ("api_key") api_key: String) : Call<GenresList>

    @GET("/3/trending/movie/{time_window}")
    fun getTrending(@Path("time_window") time_window:String , @Query ("api_key") api_key: String) : Call<GenresListProperty1>

    @GET("/3/movie/{id}")
    fun getLatestMovies( @Path("id") id:String, @Query ("api_key") api_key: String) : Call<GenresListProperty1>

    @GET("/3/discover/movie")
    fun getLatestMoviesByLanguage(@Query ("api_key") api_key: String,@Query("with_original_language") with_original_language:String,@Query ("year") year:String) : Call<GenresListProperty1>

}

object TMBDApi{
    val retrofitService:TMBDApiService by lazy{
        retrofit.create(TMBDApiService::class.java)
    }
}
