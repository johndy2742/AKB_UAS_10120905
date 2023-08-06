package com.example.uas_10120905_johndypanca.ui.tiers

data class TiersResponse(
    val data: TiersData
)

data class TiersData(
    val uuid: String,
    val tiers: List<Tier>
)

data class Tier(
    val tier: Int,
    val tierName: String,
    val largeIcon : String
)
