package luyao.sample.mergeadapter.ui

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luyao.sample.mergeadapter.R
import luyao.sample.mergeadapter.data.LoadState
import luyao.sample.mergeadapter.data.Student
import luyao.sample.mergeadapter.data.Teacher

/**
 * Created by luyao
 * on 2020/4/3 13:47
 */
class MainViewModel : ViewModel() {

    private val _loadState: MutableLiveData<LoadState> = MutableLiveData()
    val loadState: LiveData<LoadState>
        get() = _loadState

    private val _studentData: MutableLiveData<List<Student>> = MutableLiveData()
    val studentData: LiveData<List<Student>>
        get() = _studentData

    val teacherData: LiveData<ArrayList<Teacher>> = liveData {
        emit(arrayListOf<Teacher>().apply {
            for (i in 0..3)
                add(Teacher(i))
        })
    }

    fun loadStudent() {

        viewModelScope.launch {
            if (loadState.value is LoadState.Loading) return@launch

            _loadState.value = LoadState.Loading
            val data = studentData.value ?: emptyList<Student>()
            val currentSize = data.size
            if (data.size < 20) {
                val append = arrayListOf<Student>().apply {
                    for (i in 0..10)
                        add(Student(currentSize+i, iconId = R.mipmap.ic_launcher_round))
                }
                delay(2000)
                _studentData.value = data + append
                _loadState.value = LoadState.Success
            }else{
                _loadState.value = LoadState.End
            }
        }

    }


}