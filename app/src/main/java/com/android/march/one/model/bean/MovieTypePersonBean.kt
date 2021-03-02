package com.android.march.one.model.bean

data class MovieTypePersonBean(val types: List<String>)

data class MovieTypeBean(
        val persons: List<MoviePersonBean>,
        val typeName: String,
        val typeNameEn: String
)

data class MoviePersonBean(
        val id: Int,
        val image: String,
        val name: String,
        val nameEn: String
)