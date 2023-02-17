package ru.cft.binapp.domain.usecases

import ru.cft.binapp.domain.model.NetworkState
import ru.cft.binapp.domain.repository.BinRepository
import ru.cft.binapp.models.Bank
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class GetInfoBinUseCase @Inject constructor(private val repository: BinRepository){

    suspend operator fun invoke(bin: Int): NetworkState<BinModel> {
        return repository.getInfo(bin)
    }
}