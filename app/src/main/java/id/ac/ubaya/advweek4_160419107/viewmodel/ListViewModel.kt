package id.ac.ubaya.advweek4_160419107.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ubaya.advweek4_160419107.model.Student
import id.ac.ubaya.advweek4_160419107.view.StudentListAdapter

//Membuat jembatan antara student model fragment list
class ListViewModel:ViewModel() {

    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

//loading data
    fun refresh(){
        val student1 = Student("67-2039790","Carry","1998/02/08","3544182408","http://dummyimage.com/100x75.png/cc0000/ffffff")
        val student2 = Student("28-7499255","Monica","2017/12/22","1832107985","http://dummyimage.com/100x75.png/dddddd/000000")
        val student3 = Student("42-5816171","Mair","2021/09/26","2129017674","http://dummyimage.com/100x75.png/dddddd/000000")

        studentsLD.value = arrayListOf<Student>(student1,student2,student3)
        loadingErrorLD.value = false
        loadingDoneLD.value = true

    }
}