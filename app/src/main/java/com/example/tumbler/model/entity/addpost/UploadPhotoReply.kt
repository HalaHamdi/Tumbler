package com.example.tumbler.model.entity.addpost

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class UploadPhotoReply(
    @SerializedName("meta") var meta: Meta,
    @SerializedName("response") var uploadPhotoResponse: UploadPhotoResponse
)
