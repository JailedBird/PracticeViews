package com.hencoder.hencoderpracticedraw1.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice7DrawRoundRectView : View {
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
        paint.style  = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
        canvas.drawRoundRect(100f,100f,400f,300f,50f,50f,paint)

    }
}