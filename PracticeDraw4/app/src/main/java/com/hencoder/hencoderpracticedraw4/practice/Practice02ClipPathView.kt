package com.hencoder.hencoderpracticedraw4.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R


class Practice02ClipPathView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    var point1 = Point(200, 200)
    var point2 = Point(600, 200)

    private var path1 = Path()
    private var path2 = Path()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private inline fun Canvas.saveBlock(block: () -> Unit) {
        save()
        block.invoke()
        restore()

    }

    init {
        path1.addCircle((point1.x + 200).toFloat(), (point1.y + 200).toFloat(), 150f, android.graphics.Path.Direction.CW)

        path2.setFillType(android.graphics.Path.FillType.INVERSE_WINDING)
        path2.addCircle((point2.x + 200).toFloat(), (point2.y + 200).toFloat(), 150f, android.graphics.Path.Direction.CW)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.saveBlock {
            // path1.reset()
            //
            // // 构造一个4分之一的扇形
            //
            // // 构造一个4分之一的扇形
            // val radius = Math.min(bitmap.width, bitmap.height) / 2.0f
            //
            // val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
            // path1.moveTo(point1.x.toFloat() + bitmap.width, point1.y.toFloat() + bitmap.height)
            // path1.lineTo(point1.x.toFloat() + bitmap.width, point1.y.toFloat() )
            // path1.lineTo(point1.x.toFloat(), point1.y.toFloat() + bitmap.height)
            //
            // // path1.arcTo(rectF, -90f, -180f)
            // path1.close()

            canvas.clipPath(path1)
            canvas.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        }

        canvas.saveBlock {
            // path2.reset()
            // path2.moveTo(point2.x.toFloat() + bitmap.width, point2.y.toFloat() + bitmap.height)
            // path2.lineTo(point2.x.toFloat() + bitmap.width, point2.y.toFloat() )
            // path2.lineTo(point2.x.toFloat(), point2.y.toFloat() + bitmap.height)
            //
            // // path1.arcTo(rectF, -90f, -180f)
            // path2.close()
            canvas.clipPath(path2)
            canvas.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        }

    }
}