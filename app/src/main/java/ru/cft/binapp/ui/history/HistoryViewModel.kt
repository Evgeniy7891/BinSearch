package ru.cft.binapp.ui.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.cft.binapp.domain.usecases.GetSharedPrefUseCase
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(val getSharedPrefUseCase: GetSharedPrefUseCase) :
    ViewModel() {

    private var _history = listOf<BinModel>()

    suspend fun getInfo() : List<BinModel> {
            _history = getSharedPrefUseCase.invoke()
        return _history
    }
}