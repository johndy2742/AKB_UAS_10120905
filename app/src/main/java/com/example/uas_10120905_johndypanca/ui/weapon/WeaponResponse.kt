package com.example.uas_10120905_johndypanca.ui.weapon


data class WeaponResponse(
    val data: List<Weapon>
)

data class Weapon(
    val shopData: ShopData?,
    val displayName: String,
    val weaponStats: WeaponStats?,
    val displayIcon : String
)


data class WeaponStats(
    val fireRate: Double,
    val magazineSize: Int,
    val damageRanges: List<DamageRange>
)

data class DamageRange(
    val rangeStartMeters: Int,
    val rangeEndMeters: Int,
    val headDamage: Double,
    val bodyDamage: Double,
    val legDamage: Double
)

data class ShopData(
    val cost: String,
    val categoryText: String,
)


