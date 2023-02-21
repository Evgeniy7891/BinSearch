package ru.cft.binapp.providers.network

import android.util.Log
import ru.cft.binapp.data.datasources.BinRemoteDataSource
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class BinRemoteDataSourceImpl @Inject constructor(private val apiService: NetworkService) : BinRemoteDataSource {

    override suspend fun getInfo(bin: Int): BinModel {
        Log.d("TAG", "API ${apiService.getInfo(bin)} ")
      return apiService.getInfo(bin)
    }
}