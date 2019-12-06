package com.example.databaseapplication.mvp.views.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.databaseapplication.R
import com.example.databaseapplication.callbacks.PlaceHolderActionCallback
import com.example.databaseapplication.mvp.models.University
import com.example.databaseapplication.mvp.presenters.UniversityPresenter
import com.example.databaseapplication.mvp.views.interfaces.DetailUniversityView
import kotlinx.android.synthetic.main.activity_detail_university.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailUniversityActivity : BaseActivity(), DetailUniversityView {

    private var presenterDetail: UniversityPresenter = UniversityPresenter(this)
    private var id_university: Int = 0
    private lateinit var item_Uni_test: University
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_university)
        id_university = intent.getIntExtra("id_university", 0)
        item_Uni_test = intent.getSerializableExtra("item") as University
        onUniversityLoaded_test(item_Uni_test)
        //presenterDetail.getUniversity(id_university)

        setSupportActionBar(toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbarDetail?.setNavigationOnClickListener {
            finish()
        }

        collapsingToolbarSpaceDetailView.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        collapsingToolbarSpaceDetailView.setCollapsedTitleTextColor(
            Color.rgb(
                255,
                255,
                255
            )
        )


    }

    override fun noInternetConnection() {
        startActivity<NoInternetConnectionActivity>()
    }

    override fun onUniversityLoadFail(msg: String) {
        toast(msg)
    }

    @SuppressLint("SetTextI18n", "MissingPermission")
    private fun onUniversityLoaded_test(university: University) {

        Glide.with(this)
            .load(university.imageSmall)
            .apply(RequestOptions().placeholder(R.drawable.ic_standart))
            .into(detailActivityUniversityImage)
        detailUniversityName.text = university.name
        city.text = university.address + ", Kazakhstan"
        detailReview.text = university.description

        //phone
        detailButtonCall.setOnClickListener {
            if (university.phone!!.isEmpty()) {
                toast("Походу телефона нет :(")
            } else {
                val intent = Intent(Intent.ACTION_DIAL)
                val s = university.phone
                intent.data = Uri.parse("tel:${university.phone}")
                startActivity(intent)
            }
        }

        //web
        detailButtonWeb.setOnClickListener {
            if (university.url!!.isEmpty()) {
                toast("Походу сайта нет :(")
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(university.url)
                startActivity(intent)
            }
        }


    }

    @SuppressLint("SetTextI18n", "MissingPermission")
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
            if (university.phone!!.isEmpty()) {
                toast("Походу телефона нет :(")
            } else {
                val intent = Intent(Intent.ACTION_CALL)
                val s = "tel: " + university.phone
                intent.data = Uri.parse(s)
                startActivity(intent)
            }
        }

        //web
        detailButtonWeb.setOnClickListener {
            if (university.url!!.isEmpty()) {
                toast("Походу сайта нет :(")
            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(university.url)
            }
        }


    }

    override fun onUniverEmpty() {
        toast("Ошибка обьект с этим айди не найден")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDetail.disposable()
    }


}