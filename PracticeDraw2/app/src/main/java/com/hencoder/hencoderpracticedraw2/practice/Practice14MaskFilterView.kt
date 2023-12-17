package com.hencoder.hencoderpracticedraw2.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw2.R

class Practice14MaskFilterView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap? = null

    val m1 = BlurMaskFilter(50f,BlurMaskFilter.Blur.NORMAL )
    val m2 = BlurMaskFilter(50f,BlurMaskFilter.Blur.SOLID )
    val m3 = BlurMaskFilter(50f,BlurMaskFilter.Blur.OUTER )
    val m4 = BlurMaskFilter(50f,BlurMaskFilter.Blur.INNER )

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.what_the_fuck)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 用 Paint.setMaskFilter 来设置不同的 BlurMaskFilter

        // 第一个：NORMAL
        paint.setMaskFilter(m1)
        canvas.drawBitmap(bitmap, 100f, 50f, paint)

        // 第二个：INNER
        paint.setMaskFilter(m2)

        canvas.drawBitmap(bitmap, (bitmap!!.width + 200).toFloat(), 50f, paint)

        // 第三个：OUTER
        paint.setMaskFilter(m3)

        canvas.drawBitmap(bitmap, 100f, (bitmap!!.height + 100).toFloat(), paint)

        // 第四个：SOLID
        paint.setMaskFilter(m4)

        canvas.drawBitmap(
            bitmap,
            (bitmap!!.width + 200).toFloat(),
            (bitmap!!.height + 100).toFloat(),
            paint
        )
    }
}