package com.lpf.tools.feature.customview.drawOneCircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lpf.tools.R;

/**
 * Created by liupengfei on 2018/9/26 14:46.
 */
public class DrawOneCircle extends View {

    private Paint paint;
    private Path path;

    public DrawOneCircle(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
    }

    public DrawOneCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
    }

    public DrawOneCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawRGB(0,0,255);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(50);

//        canvas.drawCircle(200, 200, 150, paint);
////
//        paint.setColor(0x7EFFFF00);
//        canvas.drawCircle(200,200,100,paint);
//
//        paint.setStrokeWidth(15);
//        canvas.drawLine(100,100,200,200,paint);
//        canvas.drawLine(200,100,200,200,paint);
//        paint.setColor(Color.WHITE);
//        canvas.drawPoint(200,200,paint);
//
//
//        paint.setColor(Color.RED);
//        path.moveTo(350,100);
//        path.lineTo(350,200);
//        path.lineTo(450,200);
//        path.close();
//        canvas.drawPath(path,paint);


        path.moveTo(100,10);
        RectF rectF = new RectF(100,10,200,110);
        path.arcTo(rectF,0,90,true);
        canvas.drawRect(rectF,paint);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);
    }
}
