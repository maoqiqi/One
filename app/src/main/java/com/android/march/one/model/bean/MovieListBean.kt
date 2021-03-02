package com.android.march.one.model.bean

import com.google.gson.annotations.SerializedName

data class MovieListBean(
        val date: String,
        val lid: Int,
        val totalComingMovie: Int,
        @SerializedName("ms")
        val movieBeanList: List<MovieBean>? = null
)

class MovieBean(
        val id: Int,
        val movieId: Int,
        val tCn: String, // 中文名
        val tEn: String, // 英文名
        val d: String,  // 导演id
        var dN: String, // 导演
        val actors: String, // 主演
        val movieType: String, // 类型
        val img: String, // 图片
        val r: Float,// 评分
        val wantedCount: Int, // 想看
        val is3D: Boolean, // 3D
        val isIMAX: Boolean, // IMAX
        val isDMAX: Boolean,// 中国巨幕
        val year: String
) {
    fun getTitle(): String = "$tCn(${if (is3D) "3D、" else ""}${if (isIMAX) "IMAX、" else ""}${if (isDMAX) "中国巨幕" else ""})"
}

class RatingBean {
    @SerializedName("max")
    var max = 0

    @SerializedName("average")
    var average: Float? = null

    @SerializedName("stars")
    var stars: String? = null

    @SerializedName("min")
    var min = 0
}

class AvatarBean {
    @SerializedName("small")
    var small: String? = null

    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null
}

class CastBean {
    @SerializedName("alt")
    var alt: String? = null

    @SerializedName("avatars")
    var avatarBean: AvatarBean? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("id")
    var id: String? = null
}

class ImageBean {
    @SerializedName("small")
    var small: String? = null

    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null
}