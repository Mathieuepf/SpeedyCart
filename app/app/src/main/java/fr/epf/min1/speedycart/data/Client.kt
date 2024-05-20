package fr.epf.min1.speedycart.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.Date

@Parcelize
data class Client(
    val clientId : Long,
    val firstname : String,
    val lastname : String,
    val activefrom : Date ?,
    val desactivefrom : Date ?,
    val clientDOB : Date ?,
    val address: Address
) : Parcelable{
    companion object{
        fun generate1Client() = Client(45,
            "John2",
            "Doe2",
            null,
            null,
            null,
            Address.generate1Address())
    }
}
