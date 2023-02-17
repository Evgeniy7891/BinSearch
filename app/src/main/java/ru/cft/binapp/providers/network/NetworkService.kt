package ru.cft.binapp.providers.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.cft.binapp.models.BinModel

interface NetworkService {
    @GET("{bin}")
    suspend fun getInfo (@Path("bin") id: Int) : BinModel
}