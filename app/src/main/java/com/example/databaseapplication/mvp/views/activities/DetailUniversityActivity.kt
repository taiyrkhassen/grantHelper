package com.example.databaseapplication.mvp.views.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.databaseapplication.R
import com.example.databaseapplication.callbacks.PlaceHolderActionCallback
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.presenters.UniversityPresenter
import com.example.databaseapplication.mvp.views.interfaces.DetailUniversityView
import kotlinx.android.synthetic.main.activity_detail_university.*
import org.jetbrains.anko.toast

class DetailUniversityActivity : AppCompatActivity(), DetailUniversityView {

    private lateinit var presenterDetail: UniversityPresenter
    private lateinit var mContext: Context
    private var id:Int = 0
    private var uni_name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)
        id = intent.getIntExtra("id", 0)
        uni_name = intent.getStringExtra("name")
        presenterDetail.getUniversity(id)

    }

    init {
        presenterDetail = UniversityPresenter(this)
        mContext = baseContext
    }

    override fun noInternetConnection() {
        noDataFound_detail.visibility = View.VISIBLE
        noDataFound_detail.setOnActionClickListener(object: PlaceHolderActionCallback{
            override fun placeHolderActionCallback(o: Any) {
               presenterDetail.getUniversity(id)
            }
        })
    }

    override fun onUniversityLoadFail(msg: String) {
        toast(msg)
    }

    @SuppressLint("SetTextI18n")
    override fun onUniversityLoaded(university: University) {

        Glide.with(this)
            .load(university.imageSmall)
            .apply(RequestOptions().placeholder(R.drawable.ic_standart))
            .into(detailActivityUniversityImage)
        detailUniversityName.text = university.name
        city.text = university.address + ", Kazakhstan"
        detailReview.text = university.description

        //phone
        detailButtonCall.setOnClickListener {

        }

        //web
        detailButtonWeb.setOnClickListener {

        }


    }

    override fun onUniverEmpty() {
        toast("Ошибка обьект с этим айди не найден")
    }

    private fun onBackPressed(view:View){
        onBackPressed(view)
    }

}