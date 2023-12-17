package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice01LinearGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice01LinearGradientView(Context context) {
        super(context);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice01LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 LinearGradient
        // LinearGradient 的参数：坐标：(100, 100) 到 (500, 500) ；颜色：#E91E63 到 #2196F3

        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setColor(Color.parseColor("#009688"));
//        canvas.drawRect(30, 30, 230, 180, paint);
//
//        paint.setColor(Color.parseColor("#FF9800"));
//        canvas.drawLine(300, 30, 450, 180, paint);
//
//        paint.setColor(Color.parseColor("#E91E63"));
//        canvas.drawText("HenCoder", 500, 130, paint);
//
//        paint.setARGB(100, 255, 0, 0);
//        canvas.drawRect(0, 0, 200, 200, paint);
//        paint.setARGB(100, 0, 0, 0);
//        canvas.drawLine(0, 0, 200, 200, paint);

//        作者：扔物线
//        链接：https://juejin.cn/post/6844903487570968584
//        来源：稀土掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//        作者：扔物线
//        链接：https://juejin.cn/post/6844903487570968584
//        来源：稀土掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//        canvas.drawCircle(300, 300, 200, paint);



        canvas.drawCircle(300, 300, 200, paint);

    }
}
