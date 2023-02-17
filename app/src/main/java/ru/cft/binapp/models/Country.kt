package ru.cft.binapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val alpha2: String,
    val currency: String,
    val emoji: String,
    val latitude: Int,
    val longitude: Int,
    val name: String,
    val numeric: String
): Parcelable