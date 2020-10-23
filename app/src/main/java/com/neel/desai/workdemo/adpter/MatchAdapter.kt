package com.neel.desai.workdemo.adpter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neel.desai.workdemo.databinding.RecyclerviewMovieBinding
import com.neel.desai.workdemo.model.Results


lateinit var context: Context;

public class MatchAdapter(private val paymentList: List<Results>, private val mcontext: Context,
                          private val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<MatchAdapter.MatchViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewholder {
        context = mcontext;
        val itemBinding =
            RecyclerviewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewholder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchViewholder, position: Int) {

        holder.itemBinding.data = paymentList[position]
        holder.bind(paymentList[position])

        holder.itemBinding.ivAccept.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.itemBinding.ivAccept, paymentList[position],position)
        }
        holder.itemBinding.ivReject.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.itemBinding.ivReject, paymentList[position],position)
        }
    }

    override fun getItemCount(): Int = paymentList.size

    class MatchViewholder(val itemBinding: RecyclerviewMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(paymentBean: Results) {

        }
    }
}