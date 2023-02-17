package ru.cft.binapp.data.datasources

import ru.cft.binapp.models.BinModel

interface BinRemoteDataSource {

    suspend fun getInfo(bin: Int) : BinModel
}