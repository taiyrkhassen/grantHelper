package com.example.databaseapplication.mvp.views.fragments

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.databaseapplication.R
import kotlinx.android.synthetic.main.fragment_information.*
import org.jetbrains.anko.imageResource

class InformationAboutUsFragment : Fragment() {

    private lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.image_trans)
        val imageToShow: Array<Int> = arrayOf(R.drawable.photo_one, R.drawable.team)
        animate(imageView, imageToShow, 0, true)

    }

    private fun animate(
        imageView: ImageView, imagesShow: Array<Int>,
        imageIndex: Int, forever: Boolean
    ) {
        val fadeInDuration = 500
        val timeBetween = 3000
        val fadeOutDuration = 1000
        imageView.visibility = View.INVISIBLE
        imageView.imageResource = imagesShow[imageIndex]


        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() // add this
        fadeIn.duration = fadeInDuration.toLong()

        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.startOffset = (fadeInDuration + timeBetween).toLong()
        fadeOut.duration = fadeOutDuration.toLong()

        val animationSet = AnimationSet(false)
        animationSet.addAnimation(fadeIn)
        animationSet.addAnimation(fadeOut)
        animationSet.repeatCount = 1
        imageView.animation = animationSet

        animationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                if (imagesShow.size - 1 > imageIndex) {
                    animate(imageView, imagesShow, imageIndex+1, forever)
                } else {
                    if (forever) {
                        animate(imageView, imagesShow,0, forever)
                    }
                }
            }
        })


    }

}