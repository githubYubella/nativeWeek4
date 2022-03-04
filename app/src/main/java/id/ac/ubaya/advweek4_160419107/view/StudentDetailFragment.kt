package id.ac.ubaya.advweek4_160419107.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.core.provider.FontsContractCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.advweek4_160419107.R
import id.ac.ubaya.advweek4_160419107.viewmodel.DetailViewModel
import id.ac.ubaya.advweek4_160419107.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()


        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)
        })
    }


}