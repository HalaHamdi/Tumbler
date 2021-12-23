package com.example.tumbler.model.entity.search

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class SuggestedTags (
    @SerializedName("meta") val meta : Meta,
    @SerializedName("response") val response :SuggestedTagsResponse
    )


data class SuggestedTagsPagination (
    @SerializedName("total"          ) var total        : Int,
    @SerializedName("count"          ) var count        : Int,
    @SerializedName("per_page"       ) var perPage      : Int,
    @SerializedName("current_page"   ) var currentPage  : Int,
    @SerializedName("total_pages"    ) var totalPages   : Int,
    @SerializedName("first_page_url" ) var firstPageUrl : Boolean,
    @SerializedName("last_page_url"  ) var lastPageUrl  : Int,
    @SerializedName("next_page_url"  ) var nextPageUrl  : String,
    @SerializedName("prev_page_url"  ) var prevPageUrl  : String
)

data class Tags (
    @SerializedName("tag_description" ) var tagDescription : String,
    @SerializedName("tag_image"       ) var tagImage       : String,
    @SerializedName("posts_count"     ) var postsCount     : Int
)

data class SuggestedTagsResponse (
    @SerializedName("pagination" ) var pagination : SuggestedTagsPagination,
    @SerializedName("tags"       ) var tags       : ArrayList<Tags> = arrayListOf()
)