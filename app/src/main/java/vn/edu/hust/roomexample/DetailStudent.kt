package vn.edu.hust.roomexample

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import vn.edu.hust.roomexample.databinding.ActivityAddStudentBinding
import vn.edu.hust.roomexample.databinding.ActivityDetailStudentBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailStudent : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStudentBinding.inflate(layoutInflater)
        val studentId = intent.getStringExtra("ID")?.toInt()

        if (studentId != null) {
            var student = StudentDatabase.getInstance(application).studentDao().getById(studentId)
            binding.inputFullname.setText(student.fullName)
            binding.inputMssv.setText(student.mssv)
            binding.inputHometown.setText(student.hometown)
            binding.birthdayInput.setText(student.dob)
        }

        setContentView(binding.root)
        val city = arrayOf(
            "Hà Nội", "Hồ Chí Minh", "Hải Phòng", "Đà Nẵng", "Cần Thơ",
            "Hải Dương", "Hà Nam", "Hà Tây", "Ninh Bình", "Hà Giang",
            "Tuyên Quang", "Lào Cai", "Lạng Sơn", "Bắc Kạn", "Thái Nguyên",
            "Lai Châu", "Điện Biên", "Sơn La", "Lào Cai", "Yên Bái",
            "Quảng Ninh", "Bắc Giang", "Bắc Ninh", "Phú Thọ", "Vĩnh Phúc",
            "Ninh Bình", "Thanh Hóa", "Nghệ An", "Hà Tĩnh", "Quảng Bình",
            "Quảng Trị", "Thừa Thiên Huế", "Quảng Nam", "Quảng Ngãi", "Bình Định",
            "Phú Yên", "Khánh Hòa", "Ninh Thuận", "Bình Thuận", "Kon Tum",
            "Gia Lai", "Đắk Lắk", "Đắk Nông", "Lâm Đồng", "Bình Phước",
            "Tây Ninh", "Bình Dương", "Đồng Nai", "Bà Rịa - Vũng Tàu", "Long An",
            "Tiền Giang", "Bến Tre", "Trà Vinh", "Vĩnh Long", "Đồng Tháp",
            "An Giang", "Kiên Giang", "Cần Thơ", "Hậu Giang", "Sóc Trăng",
            "Bạc Liêu", "Cà Mau", "Đắk Nông"
        )
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.select_dialog_item, city)
        binding.inputHometown.setAdapter(adapter)
        binding.inputHometown.threshold = 1

        val myCalendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar)
        }

        binding.birthdayBtn.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.deleteBtn.setOnClickListener {
            StudentDatabase.getInstance(application).studentDao().deleteById(studentId!!)
            Toast.makeText(applicationContext, "Delete student successfully", Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        binding.updateBtn.setOnClickListener {
            if (binding.inputFullname.text.isEmpty() || binding.inputHometown.text.isEmpty()
                || binding.birthdayInput.text.isEmpty()
                || binding.inputHometown.text.isEmpty() || binding.inputMssv.text.isEmpty()
            ) {
                Toast.makeText(applicationContext, "Please fill in all field", Toast.LENGTH_LONG)
                    .show()

            } else {
                val newStudent = Student(
                    id=studentId!!,
                    mssv = binding.inputMssv.text.toString(),
                    fullName = binding.inputFullname.text.toString(),
                    dob = binding.birthdayInput.text.toString(),
                    hometown = binding.inputHometown.text.toString()
                )
                val res =
                    StudentDatabase.getInstance(application).studentDao().insertStudent(newStudent)
                Toast.makeText(applicationContext, "Update student successfully", Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

        }
    }

    private fun updateLabel(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.birthdayInput.setText(sdf.format(myCalendar.time))
    }
}