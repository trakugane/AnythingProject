package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ezPark(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "firstName")
    var firstName: String?,
    @ColumnInfo(name = "lastName")
    var lastName: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "phoneNum")
    var phoneNum: String?,
    @ColumnInfo(name = "username")
    var username: String?,
    @ColumnInfo(name = "password")
    var password: String?
)