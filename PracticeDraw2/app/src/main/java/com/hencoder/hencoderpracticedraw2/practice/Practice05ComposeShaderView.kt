package com.hencoder.hencoderpracticedraw2.practice

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.ComposeShader
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.hencoder.hencoderpracticedraw2.R

@RequiresApi(Build.VERSION_CODES.Q)
class Practice05ComposeShaderView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null) // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.batman)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)
        paint.setShader(
            ComposeShader(
                BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP),
                BitmapShader(
                    bitmap2,
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP
                ),
                BlendMode.DST_IN
            )
        )


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(200f, 200f, 200f, paint)
    }
}