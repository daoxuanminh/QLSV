package vn.edu.hust.roomexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.Normalizer
import java.util.Locale

class StudentAdapter(
    val items: Array<Student>,
    val listener: ItemClickListener?
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    // Implement Adapter methods

    inner class StudentViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName = itemView.findViewById<TextView>(R.id.text_name)
        val studentMSSV = itemView.findViewById<TextView>(R.id.text_mssv)
        val studentMail = itemView.findViewById<TextView>(R.id.text_email)
        init {
            itemView.setOnClickListener {
                listener?.ItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)

        return StudentViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    // onClickListener Interface
    interface ItemClickListener {
        fun ItemClick(student: Student)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.studentName.text = "Full name:"+items[position].fullName
        holder.studentMSSV.text = "MSSV:"+items[position].mssv
        holder.studentMail.text =
            convertToUsername(items[position].fullName) + items[position].mssv + "@sis.hust.edu.vn"
    }

    fun convertToUsername(input: String): String {
        var words = input.split("\\s+".toRegex())
        val lastName=words[words.lastIndex]
        words=words.dropLast(1)
        val firstLetters = words.mapNotNull { it.firstOrNull()?.toString() }
        return (lastName+"."+firstLetters.joinToString("")).lowercase()
    }
}

