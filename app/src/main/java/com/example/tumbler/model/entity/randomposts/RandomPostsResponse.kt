package com.example.tumbler.model.entity.randomposts

import com.google.gson.annotations.SerializedName

data class RandomPostsResponse (
    @SerializedName("posts") val posts : List<Posts>
)