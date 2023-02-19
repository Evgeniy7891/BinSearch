package ru.cft.binapp.domain.repository

import ru.cft.binapp.models.BinModel

interface SharedPreferencesRepository {

    suspend fun saveRequest(info: BinModel)

    suspend fun getHistory() : List<BinModel>
}