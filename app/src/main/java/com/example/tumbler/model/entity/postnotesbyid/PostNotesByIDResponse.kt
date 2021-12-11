package com.example.tumbler.model.entity.postnotesbyid

import com.google.gson.annotations.SerializedName


/***
 * data class to store data of the response of the back-end to get a specific post notes
 */
data class PostNotesByIDResponse (

    @SerializedName("likes") var likes: List<Likes>,
    @SerializedName("replies") var replies: List<Replies>,
    @SerializedName("reblogs") var reblogs: List<Reblogs>

)
