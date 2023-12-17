package com.hencoder.hencoderpracticedraw1.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Practice9DrawPathView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val paint   = Paint()
    private val path = Path()
    init {
        paint.setColor(Color.BLACK)
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200f, 200f, 400f, 400f, -225f, 225f);
        path.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false);
        path.lineTo(400f, 542f);


    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        练习内容：使用 canvas.drawPath() 方法画心形
        canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形），大功告成
        path.reset()
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 6f

        path.lineTo(100f, 100f)
        path.lineTo(100f, 200f)
        canvas.drawPath(path, paint)

    }
}