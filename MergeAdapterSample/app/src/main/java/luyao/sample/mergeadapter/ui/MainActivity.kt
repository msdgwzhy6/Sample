package luyao.sample.mergeadapter.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import luyao.sample.mergeadapter.adapter.StateAdapter
import luyao.sample.mergeadapter.adapter.StudentAdapter
import luyao.sample.mergeadapter.adapter.TeacherAdapter
import luyao.sample.mergeadapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private val teacherAdapter by lazy { TeacherAdapter() }
    private val studentAdapter by lazy { StudentAdapter() }
    private val stateAdapter by lazy { StateAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecyclerView()
        observe()
        initData()
    }

    private fun initData() {
        mViewModel.loadStudent()
    }

    private fun initRecyclerView() {

        binding.recyclerView.run {
            val manager = LinearLayoutManager(this@MainActivity)
            layoutManager = manager
            adapter = MergeAdapter(teacherAdapter, studentAdapter, stateAdapter)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0 && manager.findLastVisibleItemPosition() == manager.itemCount - 1) {
                        mViewModel.loadStudent()
                        Log.e("xxx", "foot")
                    }
                }
            })
        }
    }

    private fun observe() {

        mViewModel.run {
            teacherData.observe(this@MainActivity, Observer { teacherAdapter.submitList(it) })
            studentData.observe(this@MainActivity, Observer { studentAdapter.submitList(it) })

            loadState.observe(this@MainActivity, Observer {
                stateAdapter.submitList(arrayListOf(it))
            })
        }

    }
}
