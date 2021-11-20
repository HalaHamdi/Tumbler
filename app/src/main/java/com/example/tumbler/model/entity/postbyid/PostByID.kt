package com.example.tumbler.model.entity.postbyid

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

   
data class PostByID (

   @SerializedName("meta") var meta : Meta,
   @SerializedName("response") var postByIDResponse : PostByIDResponse

)