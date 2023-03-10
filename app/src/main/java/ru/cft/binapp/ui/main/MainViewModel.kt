package ru.cft.binapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.cft.binapp.domain.model.NetworkState
import ru.cft.binapp.domain.usecases.GetInfoBinUseCase
import ru.cft.binapp.domain.usecases.SaveSharedPrefUseCase
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getInfoBinUseCase: GetInfoBinUseCase,
    private val saveSharedPrefUseCase: SaveSharedPrefUseCase
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
            is NetworkState.Loading -> isLoading.value
            is NetworkState.Success -> _result.emit(response.success)

        }
    }
    suspend fun saveInfo(info: BinModel) = saveSharedPrefUseCase.invoke(info)

    fun deleteInfo() {
        _result.value = null
    }
}