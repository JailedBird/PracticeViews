package com.hencoder.hencoderpracticedraw2.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DashPathEffect
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathDashPathEffect
import android.graphics.PathEffect
import android.graphics.SumPathEffect
import android.util.AttributeSet
import android.view.View


class Practice12PathEffectView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path = Path()

    var pathEffect1: PathEffect = CornerPathEffect(20f)
    var pathEffect2: PathEffect = DiscretePathEffect(20f, 5f)
    val pathEffect3: PathEffect = DashPathEffect(floatArrayOf(10f, 5f, 30f, 5f), 10f)
    // val pathEffect4: PathEffect = DashPathEffect(floatArrayOf(10f, 5f, 30f, 5f), 10f)
    var dashPath = Path().apply {
        lineTo(20f, -30f)
        lineTo(40f, 0f)
        close()
    }

    val pathEffect4 = PathDashPathEffect(dashPath, 50f, 0f, PathDashPathEffect.Style.MORPH)
    val pathEffect5: PathEffect = SumPathEffect(pathEffect1, pathEffect3)
    val pathEffect6: PathEffect = ComposePathEffect(pathEffect1, pathEffect3)


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        paint.style = Paint.Style.STROKE
        path.moveTo(50f, 100f)
        path.rLineTo(50f, 100f)
        path.rLineTo(80f, -150f)
        path.rLineTo(100f, 100f)
        path.rLineTo(70f, -120f)
        path.rLineTo(150f, 80f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(pathEffect1)
        canvas.drawPath(path, paint)
        canvas.save()
        canvas.translate(500f, 0f)
        // 第二处：DiscretePathEffect
        paint.setPathEffect(pathEffect2)
        canvas.drawPath(path, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(0f, 200f)
        // 第三处：DashPathEffect
        paint.setPathEffect(pathEffect3)
        canvas.drawPath(path, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(500f, 200f)
        // 第四处：PathDashPathEffect
        paint.setPathEffect(pathEffect4)
        canvas.drawPath(path, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(0f, 400f)
        // 第五处：SumPathEffect
        paint.setPathEffect(pathEffect5)
        canvas.drawPath(path, paint)
        canvas.restore()
        canvas.save()
        canvas.translate(500f, 400f)
        // 第六处：ComposePathEffect

        paint.setPathEffect(pathEffect6)
        canvas.drawPath(path, paint)
        canvas.restore()
    }
}