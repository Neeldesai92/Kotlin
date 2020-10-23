package com.neel.desai.workdemo.fragment


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neel.desai.workdemo.model.Results
import com.neel.desai.workdemo.repo.MatchRepository

public class HomeFragmentNewViewModel(context: Context, private val repository: MatchRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel


    public val _Result = MutableLiveData<List<Results>>();
    val mContext: Context = context
    val Result: LiveData<List<Results>>
        get() = _Result

    fun fetchRepoList() {

        val Resultes = repository.getMovies()
        Resultes.observeForever { result ->
            _Result.value = result
        }
    }

    fun UpdateList(Pos: Int, movie: Results) {


        repository.updateMatch(Pos, movie)


    }

}