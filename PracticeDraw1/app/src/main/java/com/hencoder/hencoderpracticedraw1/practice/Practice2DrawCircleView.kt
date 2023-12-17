package com.hencoder.hencoderpracticedraw1.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice2DrawCircleView : View {
    private val p1 = Paint()
    private val p2 = Paint()
    private val p3 = Paint()
    private val p4 = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        p1.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
        }
        p2.apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth =6f
        }

        p3.apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#4991e3")
        }

        p4.apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 20f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
//         黑色实心圆
        canvas.drawCircle(350f, 250f, 200f, p1);
        canvas.drawCircle(900f, 250f, 200f, p2);
        canvas.drawCircle(350f, 700f, 200f, p3);
        canvas.drawCircle(900f, 700f, 200f, p4);
        // canvas.drawCircle(350f, 250f, 200f, p1);
        // canvas.drawCircle(350f, 250f, 200f, p1);

    }
}