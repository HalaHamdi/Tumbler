package com.example.tumbler.model.entity.postnotesbyid

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

   
data class PostNotesByID (

   @SerializedName("meta") var meta : Meta,
   @SerializedName("response") var postNotesByIDResponse : PostNotesByIDResponse

)