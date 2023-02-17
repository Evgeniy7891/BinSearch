package ru.cft.binapp.providers.network

import ru.cft.binapp.data.datasources.BinRemoteDataSource
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class BinRemoteDataSourceImpl @Inject constructor(private val apiService: NetworkService) : BinRemoteDataSource {

    override suspend fun getInfo(bin: Int): BinModel {
      return apiService.getInfo(bin)
    }
}