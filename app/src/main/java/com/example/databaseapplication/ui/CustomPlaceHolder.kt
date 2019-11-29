package com.example.databaseapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.databaseapplication.R
import com.example.databaseapplication.callbacks.PlaceHolderActionCallback
import android.view.View
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import kotlin.math.absoluteValue


class CustomPlaceholderView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var mActionItemClickListener:PlaceHolderActionCallback ? = null

    fun setOnActionClickListener(click: PlaceHolderActionCallback) {
        mActionItemClickListener = click
    }

    init {
        inflate(context, R.layout.custom_view_placeholder, this)
        val lottie: LottieAnimationView = findViewById(R.id.animation_view)
        val textView: TextView = findViewById(R.id.textViewCustomViewPlaceHolderTitle)
        val button: TextView = findViewById(R.id.buttonCustomViewPlaceHolderAction)
        val textViewDesc: TextView = findViewById(R.id.textViewCustomViewPlaceHolderDesc)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomPlaceholderView)
//        val `is` = resources.openRawResource(attributes.getInt(R.styleable.CustomPlaceholderView_animation_placeholder, -1))
//        val writer = StringWriter()
//        val buffer = CharArray(1024)
//        `is`.use { `is` ->
//            val reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
//            while (reader.read(buffer) != -1) {
//                writer.write(buffer, 0, reader.read(buffer))
//            }
//        }

       // val jsonString = writer.toString()
        val ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomPlaceholderView)
        val rawResId =
            ta.getResourceId(R.styleable.CustomPlaceholderView_animation_placeholder, 0)

        lottie.setAnimation(rawResId)
        if (attributes.getString(R.styleable.CustomPlaceholderView_button_title_placeholder) != "") {
            button.text = attributes.getString(R.styleable.CustomPlaceholderView_button_title_placeholder)
            button.visibility = View.VISIBLE
        } else {
            button.visibility = View.GONE
        }

        if (attributes.getString(R.styleable.CustomPlaceholderView_desc_placeholder) != "") {
            textViewDesc.visibility = View.VISIBLE
            textViewDesc.text = attributes.getString(R.styleable.CustomPlaceholderView_desc_placeholder)
        } else {
            textViewDesc.visibility = View.GONE
        }

        button.setOnClickListener {
            mActionItemClickListener?.placeHolderActionCallback("")
        }

        attributes.recycle()
    }

    fun setTitle(title: String) {
        val textView: TextView = findViewById(R.id.textViewCustomViewPlaceHolderTitle)
        textView.text = title
    }

    fun setDesc(desc:String) {
        val textViewDesc: TextView = findViewById(R.id.textViewCustomViewPlaceHolderDesc)
        textViewDesc.text = desc
    }
    fun setAnimation(animJson:String){
        val animationView : LottieAnimationView = findViewById(R.id.animation_view)
        animationView.setAnimationFromJson(animJson, null)
    }

}