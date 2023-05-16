package com.joydip1997.animatedstackview

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.contains

class CustomStackView(
    private val parentView: FrameLayout,
    private val highestViewHeight : Int,
    private val listOfViews : MutableList<Triple<CollapsableView,Float,Float>>
) : CustomStackFramework {

    private var currentViewIndex = 0
    private var nextViewIndex = 0


    init {
        val firstView = listOfViews.first().first
        handleViewStateAnimations(firstView, viewState = firstView.getViewState())
    }

    override fun toggleViewState(view: CollapsableView) {
        currentViewIndex = listOfViews.indexOf(listOfViews.find { it.first == view })
        if(currentViewIndex  == listOfViews.size - 1){
            val currentViewState = view.toggleViewState()
            handleViewStateAnimations(view,currentViewState)
        }else{
            nextViewIndex = currentViewIndex + 1

            val currentViewState = view.toggleViewState()
            handleViewStateAnimations(view,currentViewState)

            val nextViewToAnimate = listOfViews[nextViewIndex].first
            val nextViewState = nextViewToAnimate.getViewState()
            handleViewStateAnimations(nextViewToAnimate,nextViewState)
        }

    }

    private fun handleViewStateAnimations(view : CollapsableView, viewState: ViewState){
        when(viewState){
            ViewState.EXPANDED -> expand(view)
            ViewState.COLLAPSED -> collapse(view)
        }
    }

    private fun expand(view: CollapsableView) {
        val expantionRatio = listOfViews.find { it.first == view }?.second ?: 0.8f
        animateAndAddView(
            view = view,
            height = (highestViewHeight * expantionRatio).toInt()
        )
        addViewToParent(view)
    }



    private fun collapse(view: CollapsableView) {
        val collapseRatio = listOfViews.find { it.first == view }?.third ?: 0.2f
        animateAndAddView(view = view, height =  (highestViewHeight * collapseRatio).toInt())
        addViewToParent(view)
    }



    private fun addViewToParent(childView: View){
        if(!parentView.contains(childView)){
            parentView.addView(childView)
        }
    }


    private fun animateAndAddView(view: View, height: Int) {
        valueAnimateForView(view,height,view.height)

    }

    private fun valueAnimateForView(view: View,newHeight : Int,startHeight : Int,onAnimationComplete: () -> Unit  = {}){
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        view.layoutParams = layoutParams
        (view.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.BOTTOM


        val valueAnimator = ValueAnimator.ofInt(startHeight,newHeight)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            layoutParams.height = value
            view.layoutParams = layoutParams
        }
        valueAnimator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                onAnimationComplete()
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }

        })
        valueAnimator.start()
    }


}