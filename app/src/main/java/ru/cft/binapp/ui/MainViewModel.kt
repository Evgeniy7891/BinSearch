package ru.cft.binapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cft.binapp.domain.model.NetworkState
import ru.cft.binapp.domain.usecases.GetInfoBinUseCase
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getInfoBinUseCase: GetInfoBinUseCase
) : ViewModel(){

    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _result = MutableStateFlow<BinModel?>(null)
    val result = _result.asStateFlow()

    fun getInfo(bin: Int) = viewModelScope.launch{
        when (val response = getInfoBinUseCase.invoke(bin)) {
            is NetworkState.Error -> _errorMessage.emit(response.throwable)
            is NetworkState.Loading -> TODO("not implemented yet")
            is NetworkState.Success -> _result.emit(response.success)
        }
    }
}