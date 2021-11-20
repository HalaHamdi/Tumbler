package com.example.tumbler.model.entity

import com.google.gson.annotations.SerializedName

data class Meta(

    @SerializedName("status") var status: String,
    @SerializedName("msg") var msg: String

)
