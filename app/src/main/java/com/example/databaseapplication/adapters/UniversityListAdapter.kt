package com.example.databaseapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import com.example.databaseapplication.interfaces.UniversalClickListener
import com.example.databaseapplication.interfaces.UniversalViewClickListener
import com.example.databaseapplication.mvp.models.University

class UniversityListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list:MutableList<University>?

    init {
        list = ArrayList()
    }
    private var mItemSpaceOpenClickListener: UniversalClickListener? = null
    private var mItemMoreClickListener: UniversalClickListener? = null
    private var mItemImageClickListener: UniversalClickListener? = null
    private var mItemClickListener: UniversalViewClickListener? = null

    fun setOnItemClickListener(click :UniversalViewClickListener){
        mItemClickListener = click
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            (holder as ViewHolder).bindData(list!![position], position)
        } catch (e: Exception) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.university_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (list == null )
            return 0
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(item: University, position: Int){



        }


    }


}