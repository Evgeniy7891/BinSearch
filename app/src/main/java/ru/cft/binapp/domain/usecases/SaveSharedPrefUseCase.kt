package ru.cft.binapp.domain.usecases

import android.util.Log
import ru.cft.binapp.domain.repository.SharedPreferencesRepository
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class SaveSharedPrefUseCase @Inject constructor(private val repository: SharedPreferencesRepository){

    suspend operator fun invoke(info: BinModel)  {
        return repository.saveRequest(info)
    }
}