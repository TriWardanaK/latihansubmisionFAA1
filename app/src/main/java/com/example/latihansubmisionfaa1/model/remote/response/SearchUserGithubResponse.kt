package com.example.latihansubmisionfaa1.model.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUserGithubResponse(
    @SerializedName("items")
    val results: ArrayList<SearchUserResponse>?
) : Parcelable