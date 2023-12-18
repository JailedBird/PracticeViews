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
import cn.jailedbird.template.R
import cn.jailedbird.template.utils.log
import kotlin.math.min
import kotlin.random.Random


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
    private var mBackgroundColor = 0
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
                        mTitleText = a.getString(attr) ?: ""
                    }

                    R.styleable.CustomTitleView_titleTextColor -> {
                        mTitleTextColor = a.getColor(attr, Color.BLACK)
                    }

                    R.styleable.CustomTitleView_backgroundColor -> {
                        mBackgroundColor = a.getColor(attr, Color.TRANSPARENT)
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

        setOnClickListener {
            mTitleText = randomText()
            "onClick: mTitleText is $mTitleText".log()
            requestLayout()
            // requestLayout() 刷新布局 当查高度变化时候 对应的居中也会支持 onMeasure ~ onDraw invalidate只会冲走onDraw
            /**
             * [android.widget.TextView#setText] 会调用 [android.widget.TextView#checkForRelayout] 是否进行invalidate和requestLayout
             * */
            // invalidate() // 必须要刷新 才会重新走onDraw流程 如果字符长度变化 不会走onMeasure 字体不会居中 尺寸不会重新测量

            // postInvalidate() //
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mPaint.textSize = mTitleTextSize.toFloat()
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mBound)

        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

        val measureWidth = when (widthSpecMode) {
            MeasureSpec.EXACTLY -> {
                widthSpecSize
            }

            MeasureSpec.AT_MOST -> {
                val textWidth: Float = mBound.width().toFloat()
                val desired = (paddingLeft + textWidth + paddingRight).toInt()
                min(desired, widthSpecSize)

            }

            MeasureSpec.UNSPECIFIED -> {
                //
                getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
            }

            else -> {
                error("Not happened")
            }

        }

        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        val measureHeight = when (heightSpecMode) {
            MeasureSpec.EXACTLY -> {
                heightSpecSize
            }

            MeasureSpec.AT_MOST -> {
                val textHeight: Float = mBound.height().toFloat()
                val desired = (paddingTop + textHeight + paddingBottom).toInt()
                min(desired, heightSpecSize)
            }

            MeasureSpec.UNSPECIFIED -> {
                getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
            }

            else -> {
                error("Not happened")
            }

        }

        "onMeasure: (measureWidth, measureHeight) is $measureWidth $measureHeight".log()
        setMeasuredDimension(measureWidth, measureHeight)


    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = mBackgroundColor
        canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), mPaint)
        mPaint.color = mTitleTextColor
        canvas.drawText(
            mTitleText,
            (width / 2 - mBound.width() / 2).toFloat(),
            (height / 2 + mBound.height() / 2).toFloat(),
            mPaint
        )
        "onDraw".log()

    }

    private fun randomText(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val randomLength = Random.nextInt(2, 21) // 生成长度在 2 到 20 之间的随机数

        return (1..randomLength)
            .map { charPool.random() }
            .joinToString("")
    }


}