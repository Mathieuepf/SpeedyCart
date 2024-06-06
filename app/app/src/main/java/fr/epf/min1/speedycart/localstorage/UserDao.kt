package fr.epf.min1.speedycart.localstorage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.min1.speedycart.data.UserDTO

@Dao
interface UserDao {
    @Insert
    fun insertUser(userDTO: UserDTO)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserDTO>

    @Delete
    fun delete(userDto: UserDTO)
}