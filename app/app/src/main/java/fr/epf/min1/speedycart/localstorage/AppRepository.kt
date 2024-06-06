package fr.epf.min1.speedycart.localstorage

import android.app.Application
import androidx.room.Room
import fr.epf.min1.speedycart.data.UserDTO

class AppRepository(application: Application) {
    private val userDao: UserDao

    init {
        val db = Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            "speedycart-db"
        ).build()
        userDao = db.userDao()
    }

    fun getUser() = userDao.getAll()

    fun setUser(userDTO: UserDTO) {
        userDao.insertUser(userDTO)
    }

    fun deleteUser(userDTO: UserDTO) {
        userDao.delete(userDTO)
    }
}