package com.nomkhonwaan.mb.ui.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
        val title: String?
) : Parcelable
