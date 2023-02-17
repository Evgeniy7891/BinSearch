package ru.cft.binapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Number(
    val length: Int,
    val luhn: Boolean
): Parcelable