package id.ac.ubaya.advweek4_160419107.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.advweek4_160419107.R
import id.ac.ubaya.advweek4_160419107.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*


class StudentListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

//        ditampilkan barisDanBaris: Linear || kolomDanBaris: Grid
        recView.layoutManager=LinearLayoutManager(context)

//        assign adapter tersebut ke recycler view
        recView.adapter = studentListAdapter

        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing =false
        }

//        utk assign ke variabel yg dimiliki view model (Method sendiri)
        observeViewModel()

    }



    private fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner) {

            studentListAdapter.updateStudentList(it)
        }
        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            if(it){
                txtError.visibility= View.VISIBLE
            } else{
                txtError.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                progressLoad.visibility = View.VISIBLE
                recView.visibility= View.GONE
            }else{
                progressLoad.visibility= View.GONE
                recView.visibility= View.VISIBLE

            }
        })
    }


}

//private fun StudentListAdapter.updateStudentList(newStudentList: Student?) {
//
//}
