package com.itis.adaptiveplayerapp.bl

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRecommender @Inject constructor() {

    fun getRecommendedPlaylist(): String{
        return "spotify:playlist:37i9dQZF1DWXpOtMyVOt4Q"
    }
}