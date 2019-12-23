package com.example.databaseapplication.mvp.views.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import com.example.databaseapplication.adapters.FavouriteChecksAdapter
import com.example.databaseapplication.callbacks.UniversalClickListener
import com.example.databaseapplication.mvp.models.CheckBoxModel
import com.example.databaseapplication.mvp.models.Profession
import com.example.databaseapplication.mvp.presenters.DialogPresenterFavourite
import com.example.databaseapplication.mvp.views.interfaces.FavoiritesSubDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_choose_favourite.*
import org.jetbrains.anko.support.v4.toast

class CheckFavoutesSubDialog : AppCompatDialogFragment(), FavoiritesSubDialog {


    private var adapter = FavouriteChecksAdapter()
    private lateinit var recyclerView: RecyclerView
    private var listCheckBox = ArrayList<CheckBoxModel>()
    private var presenter = DialogPresenterFavourite(this)

    override fun getTheme(): Int = R.style.BaseGrayBottomSheetDialogLight
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    private var mListener: UniversalClickListener? = null

    fun setOnSubmitClickLister(click: UniversalClickListener) {
        mListener = click
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_choose_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAll(view)
        dialog_submit.setOnClickListener {
            d("list", adapter.mReadyList.toString())
            if (adapter.mReadyList.size != 0) {
                presenter.sendFavourites(adapter.mReadyList)
            } else {
                toast("Выберите хотя бы один")
            }
        }
        button_close.setOnClickListener {
            dismiss()
        }
    }

    private fun initAll(view: View) {
        test_checkBoxes()
        recycler_checkbox.layoutManager = LinearLayoutManager(context)
        recycler_checkbox.adapter = adapter

    }

    fun test_checkBoxes() {

        val test = listOf(
            "рисование",
            " создавать руками",
            " химические эксперименты",
            " исследовать растение",
            " исследовать животных",
            " работать с детьми",
            " лечить",
            " археологические раскопки",
            " изучать историю",
            " изучать новые технологии",
            " программирования",
            " исследовать мировую карту",
            " изучать международные отношение",
            " общаться с людьми",
            " писать",
            " учить",
            " помогать окружающим",
            " считать",
            " анализировать",
            " изучать религию",
            " думать о бытие",
            " изучать культуру",
            " изучать этнологию",
            " защищать права  людей",
            " исследовать преступление",
            " шить",
            " заниматься спортом",
            " играть на музыкальном инструменте",
            " петь",
            " исследовать физические законы",
            " экспериментировать",
            " управлять",
            " изучать бизнес",
            " работать в гос.органе",
            " горные раскопки",
            " нефтедобывание",
            " работать с металлом",
            " исследовать  землетрясение",
            " изучать машиностроение",
            " Строить",
            " изучать  землеустройство",
            " изучать логистику",
            " путешествовать",
            " работать с животными",
            " изучать фармацию",
            " читать книги",
            " оценивать",
            " изучать землеустройство",
            " организовывать",
            " изучать энергообеспечение",
            " иследовать растение",
            " выращивать",
            " изучать мелиорацию",
            " изучать почвоведение",
            " охранять окружающую среду",
            " выращивать растение",
            " работать с аграрной техникой",
            " изучать водные ресурсы",
            " охотничество",
            " изучать животных",
            " снимать видео",
            " танцевать",
            " изучать музейное дело"
        )
        for (i in test) {
            listCheckBox.add(CheckBoxModel(i))
        }

        adapter.addToList(listCheckBox)
    }

    override fun successfullySend(listProfession:ArrayList<Profession>) {
        mListener?.onListClick(0, listProfession)
        dismiss()
    }

    override fun failedSend(msg:String) {
        toast(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposable()
    }
}