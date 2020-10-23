package com.neel.desai.workdemo.repo

import com.neel.desai.workdemo.fragment.HomeFragmentNewViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MatchViewModelFactory(
    private val mcontext: Context,
    private val repository: MatchRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentNewViewModel(mcontext, repository) as T
    }

}
