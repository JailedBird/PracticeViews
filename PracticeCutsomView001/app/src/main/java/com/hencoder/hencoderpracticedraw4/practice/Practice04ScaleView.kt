package com.hencoder.hencoderpracticedraw4.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Practice04ScaleView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paintRed = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }
    var paintBlack = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)
    val rect = RectF(0f, 0f, 100f, 100f)
    var pathTest = Path().apply {
        paint.setColor(Color.RED)
        moveTo(0f, 0f)
        addArc(rect, 45f, 90f)
    }


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // canvas.ok {
        //     canvas.drawRect(rect, paintRed)
        //     // canvas.drawPath(pathTest, paintBlack)
        // }
        canvas.ok {
            // canvas.rotate(180f)
            val x = point1.x.toFloat()
            val y = point1.y.toFloat()
            canvas.scale(1.3f, 1.3f, x + bitmap.width / 2, y + bitmap.height / 2)
            // canvas.translate(-150f, 0f)
            canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        }

        canvas.ok {
            val x = point2.x.toFloat()
            val y = point2.y.toFloat()
            canvas.scale(0.5f, 1.3f, x + bitmap.width / 2, y + bitmap.height / 2)

            // canvas.translate(150f, 0f)
            canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        }
    }
}