package com.neel.desai.workdemo.adpter

import com.neel.desai.workdemo.model.Results
import android.view.View


interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, movie: Results,pos :Int)
}