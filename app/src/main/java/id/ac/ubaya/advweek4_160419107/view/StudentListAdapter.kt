package id.ac.ubaya.advweek4_160419107.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.advweek4_160419107.R
import id.ac.ubaya.advweek4_160419107.model.Student
import id.ac.ubaya.advweek4_160419107.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList: ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(val view: View): RecyclerView.ViewHolder(view)

    fun updateStudentList(newStudentList: List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged() //merender ulang recyler view sehingga terupdate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
//        Memasang layout pada setiap item-item yang ada  pada recycler view
        val inflater =LayoutInflater.from(parent.context)
        val v =inflater.inflate(R.layout.student_list_item, parent, false)

        return StudentViewHolder(v)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//Meload data yang sesuai dari arraylist student, lalu dipasang ke dalam layoutnya
        holder.view.txtId.text = studentList[position].id
        holder.view.txtName.text = studentList[position].name
        holder.view.imageView.loadImage(studentList[position].photoUrl.toString(), holder.view.progressBar)

        holder.view.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionToStudentDetail(studentList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
//        Mereturn jumlah student yang ada dalam arraylist
        return studentList.size
    }


}