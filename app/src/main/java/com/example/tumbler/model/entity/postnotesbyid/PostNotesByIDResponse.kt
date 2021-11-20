package com.example.tumbler.model.entity.postnotesbyid

import com.example.tumbler.model.entity.postnotesbyid.Likes
import com.example.tumbler.model.entity.postnotesbyid.Reblogs
import com.example.tumbler.model.entity.postnotesbyid.Replies
import com.google.gson.annotations.SerializedName

   
data class PostNotesByIDResponse (

   @SerializedName("likes") var likes : List<Likes>,
   @SerializedName("replies") var replies : List<Replies>,
   @SerializedName("reblogs") var reblogs : List<Reblogs>

)