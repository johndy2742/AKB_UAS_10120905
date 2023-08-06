package com.example.uas_10120905_johndypanca.ui.api

import com.example.uas_10120905_johndypanca.ui.tiers.TiersResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchTiers {
        @GET("/v1/competitivetiers/03621f52-342b-cf4e-4f86-9350a49c6d04")
        suspend fun getTiers(): Response<TiersResponse>
}
