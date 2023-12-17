package com.hencoder.hencoderpracticedraw2.practice

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Xfermode
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw2.R

class Practice08XfermodeView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmapman: Bitmap? = null
    var bitmaplog: Bitmap? = null
    lateinit var x1: Xfermode
    lateinit var x2: Xfermode
    lateinit var x3: Xfermode
    lateinit var x4: Xfermode

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        bitmapman = BitmapFactory.decodeResource(resources, R.drawable.batman)
        bitmaplog = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)
        x1 = PorterDuffXfermode(PorterDuff.Mode.SRC)
        x2 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        x3 = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        /*x2 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        */x2 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 使用 paint.setXfermode() 设置不同的结合绘制效果

        // 绘制图一
        // 别忘了用 canvas.saveLayer() 开启 off-screen buffer
        val saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)

        canvas.drawBitmap(bitmapman, 0f, 0f, paint)
        paint.setXfermode(x1)
        // 第一个：PorterDuff.Mode.SRC
        canvas.drawBitmap(bitmaplog, 0f, 0f, paint)
        paint.setXfermode(null)
        canvas.restoreToCount(saved)


        canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(bitmapman, (bitmapman!!.width + 100).toFloat(), 0f, paint)
        // 第二个：PorterDuff.Mode.DST_IN
        paint.setXfermode(x2)
        canvas.drawBitmap(bitmaplog, (bitmapman!!.width + 100).toFloat(), 0f, paint)
        paint.setXfermode(null)
        canvas.restore()


        canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG)
        canvas.drawBitmap(bitmapman, 0f, (bitmapman!!.height + 20).toFloat(), paint)
        paint.setXfermode(x3)
        // 第三个：PorterDuff.Mode.DST_OUT
        canvas.drawBitmap(bitmaplog, 0f, (bitmapman!!.height + 20).toFloat(), paint)
        paint.setXfermode(null)
        canvas.restore()
        // 用完之后使用 canvas.restore() 恢复 off-screen buffer
    }
}
