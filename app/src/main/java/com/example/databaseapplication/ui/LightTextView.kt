package com.example.databaseapplication.ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class LightTextView : AppCompatTextView {

    private var face: Typeface

    constructor(context: Context) : super(context) {

        face = Typeface.createFromAsset(context.assets, "fonts/OpenSans.ttf")
        this.typeface = face
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        face = Typeface.createFromAsset(context.assets, "fonts/OpenSans.ttf")
        this.typeface = face
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        face = Typeface.createFromAsset(context.assets, "fonts/OpenSans.ttf")
        this.typeface = face
    }
}
