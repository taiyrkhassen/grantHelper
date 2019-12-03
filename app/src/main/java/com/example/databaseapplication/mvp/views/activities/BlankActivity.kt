package com.example.databaseapplication.mvp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.databaseapplication.R
import com.example.databaseapplication.mvp.presenters.BlankPresenter
import com.example.databaseapplication.mvp.views.activities.MainActivity.Companion.TAB
import com.example.databaseapplication.mvp.views.interfaces.BlankView
import kotlinx.android.synthetic.main.blank_activity.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class BlankActivity : AppCompatActivity(), BlankView {



    private lateinit var presenter: BlankPresenter

    init {
        presenter = BlankPresenter(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blank_activity)
        material_button.setOnClickListener {
            if(checkAllFields()) {
                val data = HashMap<String, Any>()
                data["first_name_user"] = name_user.text.toString()
                data["last_name_user"] = lastname_user.text.toString()
                data["city_user"] = city.selectedItem.toString()
                data["ent_score"] = entScore_blank.text.toString().toInt()
                data["prof_sub_one"] = first_elective.selectedItem.toString()
                data["prof_sub_two"] = second_elective.selectedItem.toString()
                data["city"] = city.selectedItem.toString()
                data["language"] = when (radio_group.checkedRadioButtonId) {
                    R.id.rus_bt -> {
                        "rus"
                    }
                    R.id.kz_bt -> {
                        "kz"
                    }
                    else -> "rus"
                }

                presenter.sendInfo(data)
            } else {
                toast("Пожалуйста заполните все поля!")
            }
        }
    }

    override fun noInternetConnection() {
        toast("No internet connection")
    }


    override fun onFailed(msg: String) {
        toast(msg)
    }

    override fun onSuccessSend() {
        toast("Отправлена!")
        startActivity<MainActivity>(
            TAB to 2
        )

    }

    private fun checkAllFields(): Boolean {
        /*return !lastname_user.text.isNullOrEmpty()
                && !name_user.text.isNullOrEmpty()
                && !entScore_blank.text.isNullOrEmpty()
                && first_elective.selectedItem.toString().isNotEmpty()
                && second_elective.selectedItem.toString().isNotEmpty()
                && radio_group.checkedRadioButtonId != -1*/
        return true
    }

}