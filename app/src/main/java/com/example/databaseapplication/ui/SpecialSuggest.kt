package com.example.databaseapplication.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.databaseapplication.R
import com.example.databaseapplication.callbacks.UniversalViewClickListener
import kotlinx.android.synthetic.main.special_suggest_item.view.*

class SpecialSuggest {

    private var mItemClickListener: UniversalViewClickListener? = null
    fun setOnClickListener(click: UniversalViewClickListener) {
        mItemClickListener = click
    }

    fun suggestView(
        context: Context,
        topParent: ViewGroup,
        child: View,
        text: String? = null,
        vararg orientation: String
    ) {

        Handler().postDelayed({
            doWork(context, topParent, child, text, *orientation)
        }, 2000)

    }

    fun suggestMultipleViews(
        context: Context,
        topParent: ViewGroup,
        text: String? = null,
        vararg child: View
    ) {

        Handler().postDelayed({
            doMultipleWork(context, topParent, text, *child)
        }, 2000)

    }

    private fun doWork(
        context: Context,
        topParent: ViewGroup,
        child: View,
        text: String? = null,
        vararg orientation: String
    ) {
        val parent = topParent

        val point = getPointOfView(child)

        val inflater =
            LayoutInflater.from(context)
        val viewMyLayout = inflater.inflate(R.layout.special_suggest_item, null)
        parent.addView(viewMyLayout)

        when (child) {
            is TextView -> copyTextView(parent, viewMyLayout.special_suggest_text, child)
        }

        viewMyLayout.imageView.x =
            point.x.toFloat() - (context.resources.getDimensionPixelOffset(R.dimen.special_suggestion_shape_size)) / 2
        viewMyLayout.imageView.y =
            point.y.toFloat() - (context.resources.getDimensionPixelOffset(R.dimen.special_suggestion_shape_size)) / 2

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 0.95f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 0.95f)

        val scaleX_s = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f)
        val scaleY_s = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)

        val scale =
            ObjectAnimator.ofPropertyValuesHolder(viewMyLayout.imageView, scaleX, scaleY)
        scale.duration = 2000
        scale.repeatMode = ObjectAnimator.REVERSE
        scale.repeatCount = ObjectAnimator.INFINITE

        val scaleS =
            ObjectAnimator.ofPropertyValuesHolder(viewMyLayout.imageView, scaleX_s, scaleY_s)
        scaleS.duration = 500

        val animatorSet = AnimatorSet()
        animatorSet.play(scale).before(scaleS)
        animatorSet.start()

        child.visibility = View.INVISIBLE

        viewMyLayout.setOnClickListener {
            parent.removeView(it)
        }

        text.let {
            viewMyLayout.special_suggest_description.text = it
            child.visibility = View.VISIBLE

            orientation.let {
                var marginR = 0f
                var marginL = 0f
                var marginB = 0f
                var marginT = 0f

                orientation.forEach {
                    when (it) {
                        "top" -> {
                            marginB =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_y)
                        }
                        "left" -> {
                            marginR =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_x)
                        }
                        "right" -> {
                            marginL =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_x)
                        }
                        "bottom" -> {
                            marginT =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_y)
                        }
                    }

                    var parameter =
                        viewMyLayout.special_suggest_description.layoutParams as FrameLayout.LayoutParams
                    parameter.setMargins(
                        marginL.toInt(), marginT.toInt(),
                        marginR.toInt(),
                        marginB.toInt()
                    ) // left, top, right, bottom
                    viewMyLayout.special_suggest_description.layoutParams = parameter

                    //viewMyLayout.special_suggest_description.x = point.x.toFloat()
                    //viewMyLayout.special_suggest_description.y = point.y.toFloat()

                    val alpfa = PropertyValuesHolder.ofFloat(View.ALPHA, 0F, 1F)

                    val scale =
                        ObjectAnimator.ofPropertyValuesHolder(
                            viewMyLayout.special_suggest_description,
                            alpfa
                        )
                    scale.duration = 1500
                    scale.start()

                }
            }
        }
    }

    private fun doMultipleWork(
        context: Context,
        topParent: ViewGroup,
        text: String? = null,
        vararg child: View
    ) {
        /*val parent = topParent

        val point = getPointOfView(child)

        val inflater =
            LayoutInflater.from(context)
        val viewMyLayout = inflater.inflate(R.layout.special_suggest_item, null)
        parent.addView(viewMyLayout)

        when (child) {
            is TextView -> copyTextView(parent,viewMyLayout.special_suggest_text, child)
        }

        viewMyLayout.imageView.x =
            point.x.toFloat() - (context.resources.getDimensionPixelOffset(R.dimen.special_suggestion_shape_size)) / 2
        viewMyLayout.imageView.y =
            point.y.toFloat() - (context.resources.getDimensionPixelOffset(R.dimen.special_suggestion_shape_size)) / 2

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 0.95f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 0.95f)

        val scaleX_s = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f)
        val scaleY_s = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)

        val scale =
            ObjectAnimator.ofPropertyValuesHolder(viewMyLayout.imageView, scaleX, scaleY)
        scale.duration = 2000
        scale.repeatMode = ObjectAnimator.REVERSE
        scale.repeatCount = ObjectAnimator.INFINITE

        val scaleS =
            ObjectAnimator.ofPropertyValuesHolder(viewMyLayout.imageView, scaleX_s, scaleY_s)
        scaleS.duration = 500

        val animatorSet = AnimatorSet()
        animatorSet.play(scale).before(scaleS)
        animatorSet.start()

        child.visibility = View.INVISIBLE

        viewMyLayout.setOnClickListener {
            parent.removeView(it)
        }

        text.let {
            viewMyLayout.special_suggest_description.text = it
            child.visibility = View.VISIBLE

            orientation.let {
                var marginR = 0f
                var marginL = 0f
                var marginB = 0f
                var marginT = 0f

                orientation.forEach {
                    when (it) {
                        "top" -> {
                            marginB =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_y)
                        }
                        "left" -> {
                            marginR =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_x)
                        }
                        "right" -> {
                            marginL =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_x)
                        }
                        "bottom" -> {
                            marginT =
                                context.resources.getDimension(R.dimen.special_suggestion_description_offset_y)
                        }
                    }

                    var parameter =
                        viewMyLayout.special_suggest_description.layoutParams as FrameLayout.LayoutParams
                    parameter.setMargins(
                        marginL.toInt(), marginT.toInt(),
                        marginR.toInt(),
                        marginB.toInt()
                    ) // left, top, right, bottom
                    viewMyLayout.special_suggest_description.layoutParams = parameter

                    //viewMyLayout.special_suggest_description.x = point.x.toFloat()
                    //viewMyLayout.special_suggest_description.y = point.y.toFloat()

                    val alpfa = PropertyValuesHolder.ofFloat(View.ALPHA, 0F, 1F)

                    val scale =
                        ObjectAnimator.ofPropertyValuesHolder(viewMyLayout.special_suggest_description, alpfa)
                    scale.duration = 1500
                    scale.start()

                }
            }
        }*/
    }

    private fun copyTextView(parent: ViewGroup, dubView: TextView, view: TextView) {

        val point = getPointOfView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dubView.focusable = view.focusable
            dubView.foreground = view.foreground
        }

        dubView.background = view.background
        dubView.isClickable = view.isClickable


        dubView.text = view.text
        dubView.setTextColor(view.currentTextColor)
        dubView.gravity = view.gravity
        dubView.width = view.width
        dubView.height = view.height
        //dubView.layoutParams = view.layoutParams
        dubView.setPaddingRelative(
            view.paddingStart,
            view.paddingTop,
            view.paddingEnd,
            view.paddingBottom
        )

        dubView.x = point.x.toFloat()
        dubView.y = point.y.toFloat()

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.2f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.2f)
        val scale = ObjectAnimator.ofPropertyValuesHolder(dubView, scaleX, scaleY)
        scale.duration = 1200
        scale.repeatMode = ObjectAnimator.REVERSE
        scale.repeatCount = ObjectAnimator.INFINITE
        scale.start()

        dubView.setOnClickListener {
            mItemClickListener!!.onListClick(0, null, it)
            parent.removeView(dubView.parent as ViewGroup)
        }

    }

    private fun getPointOfView(view: View): Point {
        val location = IntArray(2)
        view.getLocationInWindow(location)
        return Point(location[0], location[1])
    }

    private fun getLocalPointOfView(view: View): Point {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Point(location[0], location[1])
    }

    private fun dpToPx(context: Context, dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
    }
}