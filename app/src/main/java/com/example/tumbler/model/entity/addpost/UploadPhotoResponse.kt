package com.example.tumbler.model.entity.addpost

import com.google.gson.annotations.SerializedName

data class UploadPhotoResponse(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("orignal_filename") var orignalFilename: String? = null,
    @SerializedName("rotation") var rotation: Boolean? = null,
    @SerializedName("upload_id") var uploadId: Boolean? = null

)
