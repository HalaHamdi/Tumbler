package com.example.tumbler.model.entity

import com.google.gson.annotations.SerializedName

data class Pagination (

    @SerializedName("total"          ) var total        : Int?     = null,
    @SerializedName("count"          ) var count        : Int?     = null,
    @SerializedName("per_page"       ) var perPage      : Int?     = null,
    @SerializedName("current_page"   ) var currentPage  : Int?     = null,
    @SerializedName("total_pages"    ) var totalPages   : Int?     = null,
    @SerializedName("first_page_url" ) var firstPageUrl : Boolean? = null,
    @SerializedName("next_page_url"  ) var nextPageUrl  : String?  = null,
    @SerializedName("prev_page_url"  ) var prevPageUrl  : String?  = null

)