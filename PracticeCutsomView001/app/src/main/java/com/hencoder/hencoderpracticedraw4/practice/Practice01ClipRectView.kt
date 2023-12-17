package com.hencoder.hencoderpracticedraw4.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw4.R

class Practice01ClipRectView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)
    private var path: Path = Path()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val specModeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val specSizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        //
        val w = when (specModeWidth) {
            MeasureSpec.UNSPECIFIED -> suggestedMinimumWidth
            MeasureSpec.AT_MOST -> specSizeWidth // TODO AT_MOST
            MeasureSpec.EXACTLY -> specSizeWidth
            else -> 0
        }

        val specModeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val specSizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        //
        val h = when (specModeHeight) {
            MeasureSpec.UNSPECIFIED -> suggestedMinimumWidth
            MeasureSpec.AT_MOST -> specSizeHeight // TODO AT_MOST
            MeasureSpec.EXACTLY -> specSizeHeight
            else -> 0
        }

        setMeasuredDimension(w, h)
    }

    fun getDefaultSize(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = size
            MeasureSpec.AT_MOST -> result = specSize
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val left = (width - bitmap.width) / 2
        val top = (height - bitmap.height) / 2
        canvas.save()
        //        canvas.clipRect(left, top + 10, left + bitmap.getWidth(), top + bitmap.getHeight()/2);
        canvas.clipRect(left + 50, top + 50, left + 300, top + 200)
        canvas.drawBitmap(bitmap, left.toFloat(), top.toFloat(), paint)
        canvas.restore()
        canvas.save()

        path.reset()
        path.moveTo(left.toFloat() + 200f, top.toFloat())

        path.rLineTo(200f, 100f)
        path.rLineTo(150f, 200f)
        path.close()
        path.close()

        canvas.clipPath(path)


        canvas.drawBitmap(bitmap, (left + 200).toFloat(), top.toFloat(), paint)
        canvas.restore()
    }
}