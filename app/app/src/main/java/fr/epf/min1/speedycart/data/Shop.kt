package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Shop(
    val shopId: Long,
    val name: String,
    val description: String,
    val activeSince: Date?,
    val disableSince: Date?,
    val siret: String,
    val address: String
    ) : Parcelable{

    companion object{
        fun generate1Shop() = Shop(12,
            "Intermarché",
            "Ceci est une description",
            Date(1644677500000),
            null,
            "818 582 496 00027",
            "12 Rue de Dupond, 35210 Montjoie"
        )

        fun generateListShop(): List<Shop>{
            val shopList = ArrayList<Shop>()
            shopList.add(Shop(
                1,
                "Intermarché",
                "Ceci est une description",
                Date(1644677500000),
                null,
                "818 582 496 00027",
                "12 Rue de Dupond, 35210 Montjoie"
            ))
            shopList.add(Shop(
                2,
                "Intermarché2",
                "Ceci est une description",
                Date(1644677500000),
                null,
                "818 582 496 00027",
                "12 Rue de Dupond, 35210 Montjoie"
            ))
            shopList.add(Shop(
                3,
                "Intermarché3",
                "Ceci est une description",
                Date(1644677500000),
                null,
                "818 582 496 00027",
                "12 Rue de Dupond, 35210 Montjoie"
            ))
            shopList.add(Shop(
                4,
                "Intermarché4",
                "Ceci est une description",
                Date(1644677500000),
                null,
                "818 582 496 00027",
                "12 Rue de Dupond, 35210 Montjoie"
            ))
            return shopList.toList()

        }
    }
}