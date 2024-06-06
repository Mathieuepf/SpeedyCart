package fr.epf.min1.speedycart.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.min1.speedycart.data.UserDTO

@Database(entities = [UserDTO::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}