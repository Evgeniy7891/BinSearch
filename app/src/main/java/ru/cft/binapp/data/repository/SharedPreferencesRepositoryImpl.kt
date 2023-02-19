package ru.cft.binapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.cft.binapp.domain.repository.SharedPreferencesRepository
import ru.cft.binapp.models.BinModel
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(@ApplicationContext context: Context) : SharedPreferencesRepository {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, BinModel::class.java).type
    private val key = "key"
    var pref = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private var result = listOf<BinModel>()
    private val history = mutableListOf<BinModel>()

    override suspend fun saveRequest(info: BinModel) {
        history.add(info)
        val editor = pref?.edit()
        val text = gson.toJson(history)
        editor?.putString(key, text)
        editor?.apply()
    }

    override suspend fun getHistory(): List<BinModel> {
        pref?.getString(key, "History empty")?.let {
            result = gson.fromJson(it, type)
        }
        return result
    }
}