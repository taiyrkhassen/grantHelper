package com.example.databaseapplication.mvp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import com.example.databaseapplication.adapters.UniversityListAdapter
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_university_list.*
import kotlinx.android.synthetic.main.fragment_university_list.view.*

class ListUniversityFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:UniversityListAdapter
    init {
        adapter = UniversityListAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_university_list, container, false)
        init(view)
        return view
    }

    fun init(view:View){
        recyclerView = view.fragmentUniversityListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}