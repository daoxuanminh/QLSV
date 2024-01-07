package vn.edu.hust.roomexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.edu.hust.roomexample.databinding.ActivityMainBinding
import java.nio.file.Files.createFile

class MainActivity : AppCompatActivity(), StudentAdapter.ItemClickListener {
    private var data=emptyArray<Student>();
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentDao=StudentDatabase.getInstance(application).studentDao()
//        val newStudent= Student(1,"20205119","Quang","25-02-2002","Ha Noi");
//        lifecycleScope.launch(Dispatchers.IO) {
//
//
//            val result = studentDao.insertStudent(newStudent)
//            Log.v("TAG", "${result}")
//            withContext(Dispatchers.Main) {
//                Toast.makeText(this@MainActivity,
//                    "New record inserted: ${result}",
//                    Toast.LENGTH_LONG).show()
//            }
//        }
        val students = studentDao.getAllStudents()
        Log.v("TAG", "${students[0].fullName}")

        val adapter = StudentAdapter(students,this)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this,
            RecyclerView.VERTICAL,false)

        binding.recyclerView.layoutManager=linearLayoutManager
        binding.recyclerView.adapter=adapter

//
    }
    override fun ItemClick(student: Student) {
        val intent = Intent(this, DetailStudent::class.java)
        Log.v("my app",student.id.toString())
        intent.putExtra("ID",student.id.toString())
        startActivity(intent)
        Log.v("TAG", "Selected position: ${student.mssv}")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.add_student){
            val intent = Intent(this, AddStudent::class.java)
            startActivity(intent)
            return true;
        }
        else{
            return super.onOptionsItemSelected(item)
        }

    }

}