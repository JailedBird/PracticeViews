package com.hencoder.hencoderpracticedraw4.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Practice11CameraRotateView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)
    private val camera:Camera  =Camera()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.ok {
            camera.save()
            camera.rotateX(30f)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        }
        canvas.ok {
            camera.save()
            camera.rotateX(30f)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        }


    }
}