package com.example.latihansubmisionfaa1.model.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FollowersGithubResponse(
    @SerializedName("id")
    val idUser: Int?,
    @SerializedName("avatar_url")
    val avatar: String?,
    @SerializedName("login")
    val user: String?
) : Parcelable
