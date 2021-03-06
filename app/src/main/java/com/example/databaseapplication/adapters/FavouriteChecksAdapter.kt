package com.example.databaseapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import kotlinx.android.synthetic.main.item_checkbox.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import com.example.databaseapplication.mvp.models.CheckBoxModel


class FavouriteChecksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val favList: ArrayList<CheckBoxModel> = ArrayList()

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

    fun addToList(list: ArrayList<CheckBoxModel>) {
        favList.clear()
        favList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(item: CheckBoxModel, position: Int) {
            itemView.checkbox_fav.text = item.textBox

            itemView.checkbox_fav.onCheckedChange { _, isChecked ->
                item.isCheckedModel = isChecked
            }

            if (item.isCheckedModel){
                mReadyList.add(item.textBox)
            } else {
                if (mReadyList.contains(item.textBox)) {
                    mReadyList.remove(item.textBox)
                }
            }

            itemView.checkbox_fav.isChecked = item.isCheckedModel
        }
    }
}

