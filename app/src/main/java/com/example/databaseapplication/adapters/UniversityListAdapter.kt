package com.example.databaseapplication.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.databaseapplication.R
import com.example.databaseapplication.callbacks.UniversalClickListener
import com.example.databaseapplication.callbacks.UniversalViewClickListener
import com.example.databaseapplication.mvp.models.University
import kotlinx.android.synthetic.main.university_item.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

class UniversityListAdapter(val lang: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<University>?

    init {
        list = ArrayList()
    }

    private var mItemSpaceOpenClickListener: UniversalClickListener? = null
    private var mItemMoreClickListener: UniversalClickListener? = null
    private var mItemImageClickListener: UniversalClickListener? = null
    private var mItemClickListener: UniversalClickListener? = null

    fun setOnItemClickListener(click: UniversalClickListener) {
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
        if (list == null)
            return 0
        return list.size
    }

    fun addToList(list: ArrayList<University>) {
        this.list?.addAll(list)
    }

    fun addToUniversityListPagination(
        listUni: List<University>,
        pagination: Boolean
    ) {
        if (!pagination)
            list?.clear()
        list?.addAll(listUni)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: University, position: Int) {
            itemView.apply {
                item.apply {
                    speciality_name.text = speciality.nameSpeciality
                    university_name.text = name
                    profession_name.text = speciality.profession.nameProfession
                    when (lang) {
                        1 ->
                            entScore.text = "Балл ЕНТ ${speciality.entScoreRussian}"
                        else ->
                            entScore.text = "Балл ЕНТ ${speciality.entScoreKazakh}"
                    }
                    if (speciality.chance_score < 40) {
                        chance_entrance.textColor = Color.RED
                        chance_entrance.text = speciality.chance_score.toString()
                    } else if (speciality.chance_score > 40 && speciality.chance_score < 70) {
                        chance_entrance.textColor = Color.YELLOW
                        chance_entrance.text = speciality.chance_score.toString()
                    } else {
                        chance_entrance.textColor = Color.GREEN
                        chance_entrance.text = speciality.chance_score.toString()
                    }
                    Glide.with(context)
                        .load(imageSmall)
                        .apply(RequestOptions().placeholder(R.drawable.ic_univer))
                        .into(universityImage)
                }
                setOnClickListener {
                    if (list != null) {
                        mItemClickListener!!.onListClick(position, list)
                    }
                }

            }

        }

    }


}