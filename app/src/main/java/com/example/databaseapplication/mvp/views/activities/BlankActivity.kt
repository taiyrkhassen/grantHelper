package com.example.databaseapplication.mvp.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.models.User
import com.example.databaseapplication.mvp.presenters.BlankPresenter
import com.example.databaseapplication.mvp.views.activities.MainActivity.Companion.TAB
import com.example.databaseapplication.mvp.views.interfaces.BlankView
import kotlinx.android.synthetic.main.blank_activity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class BlankActivity : BaseActivity(), BlankView {

    private var presenter: BlankPresenter = BlankPresenter(this)
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blank_activity)
        if (isOnline()) {
            material_button.setOnClickListener {
                if (checkAllFields()) {
                   //presenter.sendInfo(getHashMap())
                    startActivity<MainActivity>()
                } else {
                    toast("Пожалуйста заполните все поля!")
                }
            }
        } else {
            noInternetConnection()
        }
    }

    override fun noInternetConnection() {
        toast("No internet connection")
        startActivity<NoInternetConnectionActivity>()
    }


    override fun onFailed(msg: String) {
        toast(msg)
        d("back_taiyr", msg)
    }

    fun doLogIn(){
        startActivity<MainActivity>()
    }
    override fun onSuccessSend(id:Int) {
        toast("Отправлена!")
        setLogged(true)
        setId(id)
        doLogIn()
    }

    private fun checkAllFields(): Boolean {
        return !lastname_user.text.isNullOrEmpty()
                && !name_user.text.isNullOrEmpty()
                && !entScore_blank.text.isNullOrEmpty()
                && first_elective.selectedItem.toString().isNotEmpty()
                && second_elective.selectedItem.toString().isNotEmpty()
                && radio_group.checkedRadioButtonId != -1

    }

    private fun getHashMap():HashMap<String, Any>{
        val data = HashMap<String, Any>()
        data["first_name_user"] = name_user.text.toString()
        data["last_name_user"] = lastname_user.text.toString()
        data["ent_score_user"] = entScore_blank.text.toString().toInt()
        data["prof_subject_one"] = first_elective.selectedItem.toString()
        data["prof_subject_two"] = second_elective.selectedItem.toString()
        data["city_user"] = city.selectedItem.toString()
        data["language_user"] = when (radio_group.checkedRadioButtonId) {
            R.id.rus_bt -> {
                "Русский"
            }
            R.id.kz_bt -> {
                "Казахский"
            }
            else -> "Русский"
        }
        return data
    }

    /*private fun makeUserObject():User {
        user = User(
            entScore_blank.text.toString().toInt(),
            name_user.text.toString(),
            lastname_user.text.toString(),
            city.selectedItem.toString(),
            when (radio_group.checkedRadioButtonId) {
                R.id.rus_bt -> {
                    "rus"
                }
                R.id.kz_bt -> {
                    "kz"
                }
                else -> "rus"
            },
            first_elective.selectedItem.toString(),
            second_elective.selectedItem.toString()
        )
        return user
    }*/

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposable()
    }

}