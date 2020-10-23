package com.neel.desai.workdemo.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.neel.desai.workdemo.R
import com.neel.desai.workdemo.adpter.MatchAdapter
import com.neel.desai.workdemo.adpter.RecyclerViewClickListener
import com.neel.desai.workdemo.model.Results
import com.neel.desai.workdemo.repo.MatchRepository
import com.neel.desai.workdemo.repo.MatchViewModelFactory


class HomeFragmentNew : Fragment(), RecyclerViewClickListener {


    private lateinit var viewModel: HomeFragmentNewViewModel
    private lateinit var factory: MatchViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var viewHome: View
    lateinit var adapter: MatchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewHome = inflater.inflate(R.layout.home_fragment_new_fragment, container, false)
        recyclerView = viewHome.findViewById(R.id.rvDocketList) as RecyclerView
        return viewHome
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val repository = MatchRepository(activity!!.applicationContext)

        factory = MatchViewModelFactory(activity!!.applicationContext, repository)
        viewModel = ViewModelProviders.of(this, factory).get(HomeFragmentNewViewModel::class.java)

        viewModel.fetchRepoList()

        viewModel._Result.observe(viewLifecycleOwner, Observer { result ->

            if (result != null) {
                recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                recyclerView.setHasFixedSize(true)
                if (this::adapter.isInitialized) {
                    adapter.notifyDataSetChanged()
                } else {
                    adapter = MatchAdapter(result, activity!!.applicationContext, this)
                    recyclerView.adapter = adapter
                }

            }
        })
    }


    override fun onRecyclerViewItemClick(view: View, movie: Results, pos: Int) {
        when (view.id) {
            R.id.iv_accept -> {

                movie.RequestFlg="accepted"
                viewModel.UpdateList(pos, movie)
            }
            R.id.iv_reject -> {
                movie.RequestFlg="Decline"
                viewModel.UpdateList(pos, movie)
            }
        }
    }

}