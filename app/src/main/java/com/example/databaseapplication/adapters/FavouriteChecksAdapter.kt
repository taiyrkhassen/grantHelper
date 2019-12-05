package com.example.databaseapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import kotlinx.android.synthetic.main.item_checkbox.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

class FavouriteChecksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val favList: ArrayList<String> = ArrayList()

    var mReadyList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checkbox, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindData(favList[position], position)
    }

    fun addToList(list: ArrayList<String>) {
        favList.clear()
        favList.addAll(list)
        notifyDataSetChanged()
    }

    fun getList(): ArrayList<String> {
        return mReadyList
    }

    fun getReadyList(): ArrayList<String> {
        return mReadyList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: String, position: Int) {
            itemView.checkbox_fav.text = item
            itemView.checkbox_fav.onCheckedChange { buttonView, isChecked ->
                if (isChecked) {
                    mReadyList.add(buttonView?.text.toString())
                } else {
                    if (mReadyList.contains(buttonView?.text.toString())) {
                        mReadyList.remove(buttonView?.text.toString())
                    }
                }
            }

        }
    }
}