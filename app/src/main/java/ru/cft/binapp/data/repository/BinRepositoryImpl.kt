package ru.cft.binapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import ru.cft.binapp.data.datasources.BinRemoteDataSource
import ru.cft.binapp.di.IoDispatcher
import ru.cft.binapp.domain.model.NetworkState
import ru.cft.binapp.domain.repository.BinRepository
import ru.cft.binapp.models.Bank
import ru.cft.binapp.models.BinModel
import ru.cft.binapp.utils.safeApiCall
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: BinRemoteDataSource
) : BinRepository{

    override suspend fun getInfo(bin: Int): NetworkState<BinModel> {
       return safeApiCall(ioDispatcher) {
           remoteDataSource.getInfo(bin)
       }
    }
}