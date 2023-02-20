package ru.cft.binapp.ui.history

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

    private val _history = MutableLiveData<List<BinModel>>()
    val history = _history

    init {
        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch {
            _history.value = getSharedPrefUseCase.invoke()
        }
    }
}