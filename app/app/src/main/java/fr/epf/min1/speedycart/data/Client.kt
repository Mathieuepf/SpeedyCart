package fr.epf.min1.speedycart.data

import java.util.Date

data class Client(
    val clientId : Long,
    val firstname : String,
    val lastname : String,
    val activefrom : Date ?,
    val desactivefrom : Date ?,
    val clientDOB : Date ?
){
    companion object{
        fun generate1Client() = Client(45,
            "John2",
            "Doe2",
            null,
            null,
            null)
    }
}
