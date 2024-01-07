package vn.edu.hust.roomexample

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
     fun getAllStudents(): Array<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student): Long

    @Update
    fun updateStudent(student: Student): Int

    @Delete
    fun delete(student: Student): Int

    @Query("SELECT * FROM students WHERE id=:id")
    fun getById(id: Int): Student

    @Query("delete from students where id = :id")
    fun deleteById(id: Int):Int

}
