package cn.jailedbird.template.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import cn.jailedbird.practiceviewtemplate.R


/**
 * [自定义View教程一](https://blog.csdn.net/lmj623565791/article/details/24252901)
 *  继承自View需要的注意事项
 * */
class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private var mTitleText: String = ""
    private var mTitleTextColor = 0
    private var mTitleTextSize = 0

    private var mPaint = Paint()
    private val mBound = Rect()

    init {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CustomTitleView,
            defStyleAttr, 0
        )

        a.use {
            val count = a.indexCount
            for (i in 0 until count) {
                when (val attr = a.getIndex(i)) {
                    R.styleable.CustomTitleView_titleText -> {
                        mTitleText = a.getString(attr)
                    }

                    R.styleable.CustomTitleView_titleTextColor -> {
                        mTitleTextColor = a.getColor(attr, Color.BLACK)
                    }

                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    R.styleable.CustomTitleView_titleTextSize -> {
                        mTitleTextSize = a.getDimensionPixelSize(
                            attr, TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics
                            ).toInt()
                        )
                    }
                }
            }
        }
        mPaint = Paint()
        mPaint.textSize = mTitleTextSize.toFloat()
        mPaint.color = mTitleTextColor
        /** 重要 获取 文字对应的矩形框*/
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mBound)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

        val measureWidth = when (widthSpecMode) {
            MeasureSpec.EXACTLY -> {
                widthSpecSize
            }

            MeasureSpec.AT_MOST -> {
                //
                1
            }

            MeasureSpec.UNSPECIFIED -> {
                //
                1
            }

            else -> {
                0 // error("Not happened")
            }

        }


        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}