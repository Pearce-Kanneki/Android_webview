package com.kanneki.webview.domain.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class fcmMessageData(
    @SerializedName("body")
    val body: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("key_1")
    val key1: String = "",
    @SerializedName("key_2")
    val key2: String = "",
    @SerializedName("key_3")
    val key3: String = "",
    @SerializedName("title")
    val title: String = ""
)

fun Map<String, Any>.toFcmMessageData(): fcmMessageData {
    val gson = Gson()
    gson.toJson(this).apply {
        return gson.fromJson(this, fcmMessageData::class.java)
    }
}

