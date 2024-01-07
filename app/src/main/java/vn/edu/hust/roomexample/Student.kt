package vn.edu.hust.roomexample

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    val mssv: String,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "dob")
    val dob: String,

    @ColumnInfo(name = "hometown")
    val hometown: String
)
