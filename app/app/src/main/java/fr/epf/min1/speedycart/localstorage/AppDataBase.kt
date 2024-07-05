package fr.epf.min1.speedycart.localstorage

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.min1.speedycart.data.ProductDTO
import fr.epf.min1.speedycart.data.UserDTO

//@Database(entities = [UserDTO::class], version = 1, exportSchema = true)
//abstract class AppDataBase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//}

@Database(entities = [UserDTO::class, ProductDTO::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)], exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao
}