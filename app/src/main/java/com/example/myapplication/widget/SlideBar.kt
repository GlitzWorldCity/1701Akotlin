package com.example.myapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.myapplication.R
import org.jetbrains.anko.sp

class SlideBar(context: Context?,attrs:AttributeSet?=null) :View(context, attrs){

    var seetionHeight = 0f

    var paint = Paint()

    var textBaseline = 0f

    var onSectionChangeListener: OnSectionChangeListener? = null

    companion object{
        private val SECTIONS = arrayListOf("A","B","C","D","E","F","G","H","I","J","K",
            "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
    }

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        seetionHeight = h*1.0f / SECTIONS.size
        val fontMetrics = paint.fontMetrics

        val textHeight = fontMetrics.descent - fontMetrics.ascent

        textBaseline = seetionHeight / 2 + (textHeight/2 - fontMetrics.descent)

    }

    override fun onDraw(canvas: Canvas) {
        //绘制所有字母
        val x = width*1.0f/2;
        var baseline = textBaseline;
        SECTIONS.forEach {
            canvas.drawText(it, x, baseline,paint)
            baseline += seetionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action){
            MotionEvent.ACTION_MOVE -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                var index = getTouchIndex(event)
                var firstLetter = SECTIONS[index]
                println(firstLetter)
                onSectionChangeListener?.onSectionChange(firstLetter)
            }

            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onSectionChangeListener?.onSlideFinish()
            }
        }
        return true
    }

    private fun getTouchIndex(event: MotionEvent) :Int{
        var index = (event.y/seetionHeight).toInt()
        if(index<0){
            index = 0
        }else if (index>= SECTIONS.size){
            index = SECTIONS.size - 1
        }
        return index
    }
    interface OnSectionChangeListener{
        fun onSectionChange(firstLetter :String)
        fun onSlideFinish()
    }

}