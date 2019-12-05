package com.example.databaseapplication.mvp.views.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapplication.R
import com.example.databaseapplication.adapters.FavouriteChecksAdapter
import com.example.databaseapplication.callbacks.UniversalClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_choose_favourite.*
import kotlinx.android.synthetic.main.dialog_choose_favourite.view.*
import kotlinx.android.synthetic.main.fragment_university_list.view.*
import org.jetbrains.anko.support.v4.toast
import java.lang.reflect.Array

class CheckFavoutesSubDialog : AppCompatDialogFragment() {

    private var adapter = FavouriteChecksAdapter()
    private lateinit var recyclerView:RecyclerView
    var listCheckBox = ArrayList<String>()

    override fun getTheme(): Int = R.style.BaseGrayBottomSheetDialogLight
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    private var mListener:UniversalClickListener? = null

    fun setOnSubmitClickLister(click: UniversalClickListener){
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
            if(adapter.getReadyList().size!=0){
                mListener?.onListClick(0, adapter.getReadyList())
                dismiss()
            } else{
                toast("Выберите хотя бы один")
            }
        }
        button_close.setOnClickListener {
            dismiss()
        }
    }

    private fun initAll(view:View){
        test_checkBoxes()
        adapter.addToList(listCheckBox)
        recycler_checkbox.layoutManager =LinearLayoutManager(context)
        recycler_checkbox.adapter = adapter

    }

    fun test_checkBoxes(){
        listCheckBox.add("Физика")
        listCheckBox.add("Математика")
        listCheckBox.add("Физика")
        listCheckBox.add("Математика")
        listCheckBox.add("Физика")
        listCheckBox.add("Математика")
        listCheckBox.add("Физика")
        listCheckBox.add("Математика")
        listCheckBox.add("Физика")
        listCheckBox.add("Математика")

        listCheckBox.add("Физика")
        listCheckBox.add("Математика")



    }
}