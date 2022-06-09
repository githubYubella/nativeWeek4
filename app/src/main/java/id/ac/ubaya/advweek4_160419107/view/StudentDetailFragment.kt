package id.ac.ubaya.advweek4_160419107.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.core.provider.FontsContractCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.advweek4_160419107.R
import id.ac.ubaya.advweek4_160419107.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.advweek4_160419107.util.loadImage
import id.ac.ubaya.advweek4_160419107.viewmodel.DetailViewModel
import id.ac.ubaya.advweek4_160419107.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.txtId
import kotlinx.android.synthetic.main.fragment_student_detail.txtName
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), StudentUpdateCLickListener, StudentCreateNotificationChannelListener {
    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentStudentDetailBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = ""
        arguments?.let{
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentIdId
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)


        observeViewModel()
        dataBinding.updateListener = this
        dataBinding.createNotificationChannelListener = this
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner){
            dataBinding.student = it
        }
//        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            txtId.setText(it.id)
//            txtName.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//            imageView2.loadImage(it.photoUrl.toString(), progressBar2)
//
//            var student= it
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe{
//                        MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
//
//                    }
//            }
//        })
    }

    override fun onStudentUpdateClick(view: View) {
        Toast.makeText(view.context,"Student updated",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
    override fun onStudentCreateNotificationChannel(view: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("mynotif", "Notification delayed after 5 seconds")
                MainActivity.showNotification(view.tag.toString(), "Notification created",
                    R.drawable.ic_baseline_person_24)
            }
    }


}