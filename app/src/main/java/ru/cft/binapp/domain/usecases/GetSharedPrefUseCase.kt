package ru.cft.binapp.domain.usecases

import ru.cft.binapp.domain.repository.SharedPreferencesRepository
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class GetSharedPrefUseCase @Inject constructor(private val repository: SharedPreferencesRepository){

    suspend operator fun invoke() : List<BinModel>  {
        return repository.getHistory()
    }
}