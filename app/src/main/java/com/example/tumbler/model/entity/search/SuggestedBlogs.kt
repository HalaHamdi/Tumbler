package com.example.tumbler.model.entity.search

import com.example.tumbler.model.entity.Meta
import com.google.gson.annotations.SerializedName

data class SuggestedBlogs (
    @SerializedName("meta") val meta : Meta,
    @SerializedName("response") val response :SuggestedBlogsResponse
    )
data class SuggestedBlogsResponse (

    @SerializedName("pagination" ) var pagination : SuggestedBlogsPagination ,
    @SerializedName("blogs"      ) var blogs      : List<Blogs>

)
data class Blogs (

    @SerializedName("id"           ) var id          : Int,
    @SerializedName("username"     ) var username    : String,
    @SerializedName("avatar"       ) var avatar      : String,
    @SerializedName("avatar_shape" ) var avatarShape : String,
    @SerializedName("header_image" ) var headerImage : String,
    @SerializedName("title"        ) var title       : String,
    @SerializedName("description"  ) var description : String,
    @SerializedName("followed") var is_followed:Boolean

)

data class SuggestedBlogsPagination (

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

