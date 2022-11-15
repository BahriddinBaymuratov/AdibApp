package com.example.adabiyotapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Adib(
    val name: String = "",
    val wasBorn: String = "",
    val dateDead: String = "",
    val type: String = "",
    val aboutAbd: String = "",
    val image: String = ""
) : Parcelable