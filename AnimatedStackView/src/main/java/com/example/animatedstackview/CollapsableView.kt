package com.example.animatedstackview

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

class CollapsableView(context: Context,attributeSet: AttributeSet) : MaterialCardView(context,attributeSet) {

    private var viewState : ViewState = ViewState.COLLAPSED

    fun toggleViewState(): ViewState {
        viewState = if(viewState == ViewState.COLLAPSED){
            ViewState.EXPANDED
        }else{
            ViewState.COLLAPSED
        }
        return viewState
    }

    fun getViewState() = viewState

}