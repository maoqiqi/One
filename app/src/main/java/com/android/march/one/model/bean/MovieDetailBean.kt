package com.android.march.one.model.bean

data class MovieDetailBean(
        val code: String,
        val msg: String,
        val showMsg: String,
        val data: Data
)

data class Data(
        val basic: Basic,
)

data class Basic(
        val story: String, // 简介
        val director: DirectorBean,// 导演
        val actors: List<ActorBean>, // 演员
)

data class DirectorBean(
        val directorId: Int,
        val img: String,
        val name: String,
        val nameEn: String
)

data class ActorBean(
        val actorId: Int,
        val img: String,
        val name: String,
        val nameEn: String
)

data class Award(
        val awardList: List<Any>,
        val totalNominateAward: Int,
        val totalWinAward: Int
)

class Community(
)

class QuizGame(
)

data class StageImg(
        val count: Int,
//        val list: List<>
)

data class Style(
        val isLeadPage: Int,
        val leadImg: String,
        val leadUrl: String
)

data class Video(
        val count: Int,
        val hightUrl: String,
        val img: String,
        val title: String,
        val url: String,
        val videoId: Int,
        val videoSourceType: Int
)

//data class(
//        val imgId: Int,
//        val imgUrl: String
//)