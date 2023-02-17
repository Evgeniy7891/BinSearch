package ru.cft.binapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val city: String,
    val name: String,
    val phone: String,
    val url: String
) : Parcelable