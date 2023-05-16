package com.example.stackimplementation




import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.stackimplementation.databinding.ActivityMainBinding
import com.joydip1997.animatedstackview.CollapsableView
import com.joydip1997.animatedstackview.CustomStackFramework
import com.joydip1997.animatedstackview.CustomStackView


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var customStackFramework: CustomStackFramework
    private var ratio : Float = 1.0f
    private var linearLayout: LinearLayout? = null
    private var containerHeight = 500
    private var screenHeight = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val parentLayout = findViewById<FrameLayout>(R.id.container)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels

        containerHeight = (screenHeight/1.5).toInt()

        val firstView = LayoutInflater.from(this).inflate(R.layout.fragment_first, parentLayout, false) as CollapsableView
        val secondView = LayoutInflater.from(this).inflate(R.layout.fragment_second, parentLayout, false) as CollapsableView
        val thirdView = LayoutInflater.from(this).inflate(R.layout.fragment_third, parentLayout, false) as CollapsableView
        val fourthView = LayoutInflater.from(this).inflate(R.layout.fragment_fourth, parentLayout, false) as CollapsableView

        val listOfViews  = mutableListOf<Triple<CollapsableView,Float,Float>>().apply {
            add(Triple(firstView,0.9f,0.15f))
            add(Triple(secondView,0.85f,0.12f))
            add(Triple(thirdView,0.80f,0.09f))
            add(Triple(fourthView,0.75f,0.06f))
        }

        customStackFramework = CustomStackView(
            parentView = parentLayout,
            highestViewHeight = screenHeight,
            listOfViews = listOfViews
        )


        firstView.setOnClickListener {
            customStackFramework.toggleViewState(firstView)
        }
        secondView.setOnClickListener {
            customStackFramework.toggleViewState(secondView)
        }

        thirdView.setOnClickListener {
            customStackFramework.toggleViewState(thirdView)
        }

        fourthView.setOnClickListener {
            customStackFramework.toggleViewState(fourthView)
        }


    }



}