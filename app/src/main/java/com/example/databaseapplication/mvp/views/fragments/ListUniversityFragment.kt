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
import com.example.databaseapplication.callbacks.UniversalClickListener
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.presenters.ListSpecialityPresenter
import com.example.databaseapplication.mvp.views.activities.DetailUniversityActivity
import com.example.databaseapplication.mvp.views.interfaces.ListSpecialityView
import kotlinx.android.synthetic.main.fragment_university_list.*
import kotlinx.android.synthetic.main.fragment_university_list.view.*
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class ListUniversityFragment : Fragment(), ListSpecialityView {

    lateinit var recyclerView: RecyclerView

    //Languages budet peredovatsa s BLANK fragmenta
    private var adapter: UniversityListAdapter = UniversityListAdapter(1)

    private var presenterList: ListSpecialityPresenter = ListSpecialityPresenter(this)
    private var listUniversity: MutableList<University> = ArrayList()
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_university_list, container, false)
        init(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenterList.getListSpeciality(true)
        fragmentUniversityListSwipeRefreshLayout.setOnRefreshListener {
            toast("Обновляю...")
            presenterList.getListSpeciality(true)
        }

    }

    fun init(view: View) {
        recyclerView = view.fragmentUniversityListRecyclerView
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        //Pagination
       /* recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = mLayoutManager.childCount
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItemPos = mLayoutManager.findFirstVisibleItemPosition()
                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount
                        && firstVisibleItemPos >= 0
                        && totalItemCount >= 10
                    ) {
                        isLoading = true
                        presenterList.getListSpeciality(isLoading)
                    } else {
                        isLoading = false
                    }
                }
            }
        })*/

        adapter.setOnItemClickListener(object : UniversalClickListener {
            override fun onListClick(position: Int, _list: Any) {
                toUniversityDeatails((_list as ArrayList<University>)[position])
            }
        })
    }

    override fun noInternetConnection() {
        filter_container.visibility = View.INVISIBLE
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
        noInternetConnectionPlaceHolder.visibility = View.VISIBLE
    }

    override fun onListLoading(isDone: Boolean) {
        isLoading = !isDone
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = !isDone
    }

    override fun onListLoadedFail(msg: String) {
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
        toast(msg)
    }

    override fun onListLoadded(arrayList: ArrayList<University>) {
        noInternetConnectionPlaceHolder.visibility = View.GONE
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
        //adapter.addToUniversityListPagination(arrayList, isLoading)
        adapter.addToList(arrayList)
    }

    override fun ListEmpty() {
        noDataFound.visibility = View.VISIBLE
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
    }

    private fun toUniversityDeatails(item:University) {
        toast("Yea")
        startActivity(
            intentFor<DetailUniversityActivity>(
                "id" to item.id,
                "name" to item.name
            )
        )
    }


}