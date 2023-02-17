package ru.cft.binapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BinModel(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
): Parcelable