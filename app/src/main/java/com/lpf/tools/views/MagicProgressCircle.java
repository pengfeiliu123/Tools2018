package com.lpf.tools.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.lpf.tools.R;

/**
 * Author: lpf
 * Time: 2019/3/6 2:10 PM
 * Description:
 */
public class MagicProgressCircle extends View {

    // 填充圆环的颜色
    private int fillColor;

    // 未填充圆环的颜色
    private int unFillColor;

    // 圆环宽度
    private int strokeWidth;

    // 当前进度的比例
    private float percent;

    // 填充圆环的画笔
    private Paint fillCirclePaint;

    // 开始和结束半圆的画笔
    private Paint headTailHalfCirclePaint;

    // 自定义颜色的列表
    private int[] customColors;

    // 进度条 100% 颜色值
    private int[] fullColors;

    // 进度条 0% 颜色值
    private int[] emptyColors;

    // 自定义位置
    private float[] customPositions;

    // 起点和终点位置
    private float[] extremePositions;

    // 用来画开始和结束用到的半圆的矩形
    private final RectF rectF = new RectF();

    public MagicProgressCircle(Context context) {
        super(context);
        init(context, null);
    }

    public MagicProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MagicProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {

        // 默认圆环的宽度
        final int strokeWidthValue = (int) (18 * getResources().getDisplayMetrics().density + 0.5f);

        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.MagicProgressCircle);
            // 当前进度
            percent = typedArray.getFloat(R.styleable.MagicProgressCircle_mpc_percent, 0.5f);
            // 当前画笔宽度
            strokeWidth = (int) typedArray.getDimension(R.styleable.MagicProgressCircle_mpc_stroke_width, strokeWidthValue);
            // 画笔开始颜色
            fillColor = typedArray.getColor(R.styleable.MagicProgressCircle_mpc_start_color, getResources().getColor(R.color.mpc_start_color));
            unFillColor = typedArray.getColor(R.styleable.MagicProgressCircle_mpc_default_color, getResources().getColor(R.color.mpc_default_color));

        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }

        fillCirclePaint = new Paint();
        fillCirclePaint.setAntiAlias(true);
        fillCirclePaint.setStrokeWidth(strokeWidth);
        fillCirclePaint.setStyle(Paint.Style.STROKE);
        fillCirclePaint.setStrokeJoin(Paint.Join.ROUND);
        fillCirclePaint.setStrokeCap(Paint.Cap.ROUND);

        headTailHalfCirclePaint = new Paint();
        headTailHalfCirclePaint.setColor(fillColor);
        headTailHalfCirclePaint.setAntiAlias(true);
        headTailHalfCirclePaint.setStyle(Paint.Style.FILL);

        // 中间位置的颜色和位置
        customColors = new int[]{fillColor, unFillColor};
        customPositions = new float[]{0, 1};

        fullColors = new int[]{fillColor, fillColor};
        emptyColors = new int[]{unFillColor, unFillColor};
        extremePositions = new float[]{0, 1};

    }

    /**
     * @param percent FloatRange(from = 0.0, to = 1.0)
     */
    public void setPercent(float percent) {
        percent = Math.min(1, percent);
        percent = Math.max(0, percent);

        if (this.percent != percent) {
            this.percent = percent;
            invalidate();
        }

    }

    public float getPercent() {
        return percent;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.rectF.left = getMeasuredWidth() / 2 - strokeWidth / 2;
        this.rectF.top = 0;
        this.rectF.right = getMeasuredWidth() / 2 + strokeWidth / 2;
        this.rectF.bottom = strokeWidth;
    }


    // 目前由于SweepGradient赋值只在构造函数，无法pre allocate & reuse instead
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int circleX = getMeasuredWidth() / 2;
        int circleY = getMeasuredHeight() / 2;
        int radius = getMeasuredWidth() / 2 - strokeWidth / 2;

        // 画渐变圆
        canvas.save();
        canvas.rotate(-90, circleX, circleY);

//        int[] colors;
//        float[] positions;
//        if (percent < 1 && percent > 0) {
//            colors = customColors;
//            customPositions[0] = percent;
//            customPositions[1] = percent;
//            positions = customPositions;
//        } else if (percent == 1) {
//            colors = fullColors;
//            positions = extremePositions;
//        } else {
//            colors = emptyColors;
//            positions = extremePositions;
//        }
//        SweepGradient sweepGradient = new SweepGradient(circleX, circleY, colors, positions);
//        fillCirclePaint.setShader(sweepGradient);
//        fillCirclePaint.setColor(Color.RED);



        /**
         * 第二种写法
         */

        // 先画圆环
        fillCirclePaint.setColor(unFillColor);
        canvas.drawCircle(circleX, circleY, radius, fillCirclePaint);
        canvas.save();
        canvas.restore();

        // 画圆弧
        Paint paint = new Paint();
        paint.setColor(fillColor);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(false);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF2 = new RectF(strokeWidth/2,strokeWidth/2,getMeasuredWidth()-strokeWidth/2,getMeasuredHeight()-strokeWidth/2);
        canvas.drawRect(rectF2,paint);
        canvas.rotate(90, circleX, circleY);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawArc(rectF2,-90,percent * 360,false,paint);
        canvas.restore();

        //绘制开始和结束时候的半圆
        if (percent > 0 && percent < 1) {
            canvas.save();
            canvas.rotate((int) Math.floor(360.0f * percent) - 1, circleX, circleY);
            canvas.drawArc(rectF, -90f, 180f, true, headTailHalfCirclePaint);
            canvas.restore();
            canvas.save();
            canvas.drawArc(rectF, 90f, 180f, true, headTailHalfCirclePaint);
            canvas.restore();
        }

        canvas.restoreToCount(canvas.save());

    }
}
