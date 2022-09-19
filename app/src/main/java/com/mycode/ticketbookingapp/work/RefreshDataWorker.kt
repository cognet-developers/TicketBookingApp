package com.mycode.ticketbookingapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.mycode.ticketbookingapp.localDatabase.MovieDatabase.Companion.getInstance
import com.mycode.ticketbookingapp.localRepository.MovieRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val repository = MovieRepository(database)
        return try {
            repository.getMovie()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}