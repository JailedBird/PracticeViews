package com.hencoder.hencoderpracticedraw1.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice8DrawArcView : View {
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

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        paint.setStyle(Paint.Style.FILL); // 填充模式
        canvas.drawArc(200f, 100f, 800f, 500f, -0f, 100f, true, paint); // 绘制扇形
        canvas.drawArc(200f, 100f, 800f, 500f, 20f, 140f, false, paint); // 绘制弧形
        // paint.setStyle(Paint.Style.STROKE); // 画线模式
        canvas.drawArc(200f, 100f, 800f, 500f, 180f, 60f, false, paint); // 绘制不封口的弧形

    }
}