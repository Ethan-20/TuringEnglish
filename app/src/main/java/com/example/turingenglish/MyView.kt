package com.example.turingenglish

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

/**
 * 自定义的小球
 */
class MyView : View {
    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    //创建画笔
    var paint = Paint()

    //设置一个圆心的点
    var point = Point()
    var width1 = 0
    var height1 = 0
    var currentX = 0
    var currentY = 0

    //初始化数据
    fun initView() {
        //对画笔的基本设置
        //设置抗锯齿
        paint.isAntiAlias = true
        //设置防抖动
        paint.isDither = true
        //设置颜色
        paint.color = Color.RED
        //设置透明的
        paint.alpha = 128
        //设置线条粗细
        paint.strokeWidth = 10f
        width1 = resources.displayMetrics.widthPixels
        height1 = resources.displayMetrics.heightPixels
        currentX = width1 / 2
        currentY = height1 / 2
        point[width1 / 2] = height1 / 2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制多条直线
        canvas.drawLines(
            floatArrayOf(
                0f,
                (height1 / 2).toFloat(),
                width1
                    .toFloat(),
                (height1 / 2).toFloat(),
                (width1 / 2).toFloat(),
                0f,
                (width1 / 2).toFloat(),
                height1.toFloat()
            ), paint
        )
        paint.style = Paint.Style.FILL //
        //绘制一个实心圆（默认情况）
        canvas.drawCircle(currentX.toFloat(), currentY.toFloat(), 50f, paint)
        //画一个空心圆也设置画笔
        paint.style = Paint.Style.STROKE //
        canvas.drawCircle(
            (width1 / 2).toFloat(),
            (height1 / 2).toFloat(),
            (width1 / 2).toFloat(),
            paint
        )
    }

    //当屏幕方向发生改变时，调用这个方法对小球进行重绘
    fun initdata(x: Int, y: Int) {
        currentX = currentX - x
        currentY = currentY - y
        invalidate() //重绘
    }
}
