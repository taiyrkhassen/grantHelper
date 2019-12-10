package com.example.databaseapplication.mvp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import com.example.databaseapplication.adapters.UniversityListAdapter
import com.example.databaseapplication.callbacks.UniversalClickListener
import com.example.databaseapplication.mvp.models.ItemAdapter
import com.example.databaseapplication.mvp.models.Profession
import com.example.databaseapplication.mvp.models.Speciality
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.presenters.ListSpecialityPresenter
import com.example.databaseapplication.mvp.views.activities.BaseActivity
import com.example.databaseapplication.mvp.views.activities.DetailUniversityActivity
import com.example.databaseapplication.mvp.views.activities.NoInternetConnectionActivity
import com.example.databaseapplication.mvp.views.dialogs.CheckFavoutesSubDialog
import com.example.databaseapplication.mvp.views.interfaces.ListSpecialityView
import com.example.databaseapplication.ui.SpecialSuggest
import kotlinx.android.synthetic.main.fragment_university_list.*
import kotlinx.android.synthetic.main.fragment_university_list.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class ListUniversityFragment : Fragment(), ListSpecialityView {

    lateinit var recyclerView: RecyclerView

    var testImproveList = ArrayList<ItemAdapter>()
    private var adapter: UniversityListAdapter = UniversityListAdapter(1)

    private var presenterList: ListSpecialityPresenter = ListSpecialityPresenter(this)
    private var listUniversity: ArrayList<ItemAdapter> = ArrayList()
    private var isLoading = false
    var specSuggested = SpecialSuggest()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_university_list, container, false)
        init(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        addToList()

        specSuggested.suggestView(context!!, list_content, list_choose_favourite,
            "Вы можете подобрать професси исходя из ваших предпочтений в предметах!")
        //после налаживания бэка легко подключить презентеры
        //presenterList.getListSpeciality((activity as BaseActivity).getId())

        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false

        list_choose_favourite.setOnClickListener {
            val dialog = CheckFavoutesSubDialog()
            dialog.setOnSubmitClickLister(object : UniversalClickListener {
                override fun onListClick(position: Int, _list: Any) {
                   // запрос на презентер отправляем стринги с листа лист join to string до этого еще

                    //d("test_check", (_list as ArrayList<String>).toString())
                    testImproveAdapter()
                    adapter.addToList(testImproveList)
                }

            })
            dialog.show(childFragmentManager, dialog::class.java.name)
        }
    }

    override fun onStart() {
        super.onStart()
        //presenter here
//        presenterList.getListSpeciality()
//        fragmentUniversityListSwipeRefreshLayout.setOnRefreshListener {
//            toast("Обновляю... ")
//            presenterList.getListSpeciality()
//        }
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
                toUniversityDetails((_list as ArrayList<University> )[position])
            }
        })
    }
    fun addToList(){
        val prof = Profession(
            1,"Working travel", "Ssdsasad"
        )
        val speciality = Speciality(
            0, "VTIPO",24,36,prof, 0.26
        )
        val university = University(
            1, "Nazarbaev University", "87089642881","Astana",speciality,
            "http://testapi.pillowz.kz/media/photos/%2B77012490135/7vzt9fz4k3z6gg9g8t2f1b4bu.JPEG",
            resources.getString(R.string.test_string),
            "https://pillowz.kz"
        )
        val university2 = University(
            1, "Nazarbaev University", "87089642881","Astana",speciality,
            "http://testapi.pillowz.kz/media/photos/%2B77012490135/7vzt9fz4k3z6gg9g8t2f1b4bu.JPEG",
            resources.getString(R.string.test_string),
            "https://pillowz.kz"
        )
        val university3 = University(
            1, "Nazarbaev University", "87089642881","Astana",speciality,
            "http://testapi.pillowz.kz/media/photos/%2B77012490135/7vzt9fz4k3z6gg9g8t2f1b4bu.JPEG",
            resources.getString(R.string.test_string),
            "https://pillowz.kz"
        )
        val university4 = University(
            1, "Nazarbaev University", "87089642881","Astana",speciality,
            "http://testapi.pillowz.kz/media/photos/%2B77012490135/7vzt9fz4k3z6gg9g8t2f1b4bu.JPEG",
            resources.getString(R.string.test_string),
            "https://pillowz.kz"
        )
        listUniversity.add(university)

        listUniversity.add(university2)

        listUniversity.add(university3)
        listUniversity.add(university4)
        adapter.addToList(listUniversity)
    }

    override fun noInternetConnection() {
        startActivity<NoInternetConnectionActivity>()
    }

    override fun onListLoading(isDone: Boolean) {
        isLoading = !isDone
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = !isDone
    }

    override fun onListLoadedFail(msg: String) {
       // fragmentUniversityListSwipeRefreshLayout.isRefreshing = true
        toast(msg)
    }

    override fun onListLoadded(arrayList: ArrayList<ItemAdapter>) {
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
        //adapter.addToUniversityListPagination(arrayList, isLoading)
        adapter.addToList(arrayList)
    }

    override fun ListEmpty() {
        noDataFound.visibility = View.VISIBLE
        fragmentUniversityListSwipeRefreshLayout.isRefreshing = false
    }

    private fun toUniversityDetails(item:University) {
        val intent = Intent(context, DetailUniversityActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
//        startActivity(
//            intentFor<DetailUniversityActivity>(
//                "id_university" to item.id,
//                "name" to item.name
//            )
//        )
    }

    private fun testImproveAdapter(){
        testImproveList.add(
            Profession(
                0,
                "Programmer",
                "aakfdslkfslkfflsdf dafmdskfm askfnm akf kmfsa"
            )
        )
        testImproveList.add(
            Profession(
                2,
                "Programmer",
                "aakfdslkfslkfflsdf dafmdskfm askfnm akf kmfsa"
            )
        )
        testImproveList.add(
            Profession(
                3,
                "Programmer",
                "aakfdslkfslkfflsdf dafmdskfm askfnm akf kmfsa"
            )
        )
    }


}