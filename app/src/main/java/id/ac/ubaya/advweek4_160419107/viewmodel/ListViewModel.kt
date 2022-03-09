package id.ac.ubaya.advweek4_160419107.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.advweek4_160419107.model.Student
import id.ac.ubaya.advweek4_160419107.view.StudentListAdapter

//Membuat jembatan antara student model fragment list
class ListViewModel(application: Application):AndroidViewModel(application) {

    val studentsLD = MutableLiveData<List<Student>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue?= null

//loading data
    fun refresh(){
        loadingErrorLD.value = false
        loadingLD.value=true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val sType = object  : TypeToken<List<Student>>(){}.type
                val result= Gson().fromJson<List<Student>>(response, sType)
                studentsLD.value= result
                loadingLD.value=false
                Log.d("showvolley", response.toString())

            },
            {
                loadingErrorLD.value=true
                loadingLD.value=false
                Log.d("showVolley", it.toString())
            })
    stringRequest.tag = TAG
    queue?.add(stringRequest)






//        val student1 = Student("67-2039790","Carry","1998/02/08","3544182408","http://dummyimage.com/100x75.png/cc0000/ffffff")
//        val student2 = Student("28-7499255","Monica","2017/12/22","1832107985","http://dummyimage.com/100x75.png/dddddd/000000")
//        val student3 = Student("42-5816171","Mair","2021/09/26","2129017674","http://dummyimage.com/100x75.png/dddddd/000000")
//
//        studentsLD.value = arrayListOf<Student>(student1,student2,student3)
//        loadingErrorLD.value = false
//        loadingDoneLD.value = true


    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)

    }
}