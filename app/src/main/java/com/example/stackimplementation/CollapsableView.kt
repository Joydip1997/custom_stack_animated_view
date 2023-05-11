package com.example.stackimplementation

import android.content.Context
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView
import java.nio.file.Path

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