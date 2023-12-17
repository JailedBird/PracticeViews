package com.hencoder.hencoderpracticedraw1.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice5DrawOvalView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint   = Paint()
    init {
        paint.setColor(Color.BLACK)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        paint.setStyle(Paint.Style.FILL)
        canvas.drawOval(50f, 50f, 350f, 200f, paint)

        paint.setStyle(Paint.Style.STROKE)
        canvas.drawOval(400f, 50f, 700f, 200f, paint)
    }
}