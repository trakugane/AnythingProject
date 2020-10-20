package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ezParkDao{
    @Insert
    fun insert(vararg ezParks: ezPark)

    @Query ("SELECT * from ezPark WHERE username IN (:usernameCheck)")
    fun checkUniqueUser(usernameCheck: String) : List<ezPark>
}
