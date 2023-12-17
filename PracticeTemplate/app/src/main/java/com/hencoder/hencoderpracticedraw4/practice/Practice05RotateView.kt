package com.hencoder.hencoderpracticedraw4.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Practice05RotateView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var paint1 = Paint().apply {
        color = Color.RED
        strokeWidth = 20f
    }
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPoint(point1.x.toFloat(), point1.y.toFloat(), paint1)
        canvas.drawPoint(point2.x.toFloat(), point2.y.toFloat(), paint1)
        canvas.ok {
            canvas.rotate(180f, point1.x.toFloat() + bitmap.width/2, point1.y.toFloat()+ bitmap.height/2)
            canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        }
        canvas.ok {
            canvas.rotate(45f, point2.x.toFloat() + bitmap.width/2, point2.y.toFloat()+ bitmap.height/2)
            canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        }
    }
}