package com.example.uas_10120905_johndypanca.ui.api

import com.example.uas_10120905_johndypanca.ui.weapon.WeaponResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchWeapon {
        @GET("/v1/weapons")
        suspend fun getWeapon(): Response<WeaponResponse>
}
