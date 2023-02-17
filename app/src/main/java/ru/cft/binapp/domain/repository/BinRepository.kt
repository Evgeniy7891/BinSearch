package ru.cft.binapp.domain.repository

import ru.cft.binapp.domain.model.NetworkState
import ru.cft.binapp.models.BinModel

interface BinRepository {

    suspend fun getInfo(bin: Int) : NetworkState<BinModel>
}