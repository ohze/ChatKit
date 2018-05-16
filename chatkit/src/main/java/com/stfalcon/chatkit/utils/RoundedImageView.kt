package com.stfalcon.chatkit.utils

import android.content.Context
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.net.Uri
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Thanks to Joonho Kim (https://github.com/pungrue26) for his lightweight SelectableRoundedImageView,
 * that was used as default image message representation
 */
class RoundedImageView : AppCompatImageView {

    private var mResource = 0
    private var mDrawable: Drawable? = null

    private var mRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        mResource = 0
        mDrawable = RoundedCornerDrawable.fromDrawable(drawable, resources)
        super.setImageDrawable(mDrawable)
        updateDrawable()
    }

    override fun setImageBitmap(bm: Bitmap) {
        mResource = 0
        mDrawable = RoundedCornerDrawable.fromBitmap(bm, resources)
        super.setImageDrawable(mDrawable)
        updateDrawable()
    }

    override fun setImageResource(resId: Int) {
        if (mResource != resId) {
            mResource = resId
            mDrawable = resolveResource()
            super.setImageDrawable(mDrawable)
            updateDrawable()
        }
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        setImageDrawable(drawable)
    }

    fun setCorners(@DimenRes leftTop: Int, @DimenRes rightTop: Int,
                   @DimenRes rightBottom: Int, @DimenRes leftBottom: Int) {
        setCorners(
                if (leftTop == 0) 0f else resources.getDimension(leftTop),
                if (rightTop == 0) 0f else resources.getDimension(rightTop),
                if (rightBottom == 0) 0f else resources.getDimension(rightBottom),
                if (leftBottom == 0) 0f else resources.getDimension(leftBottom)
        )
    }

    fun setCorners(leftTop: Float, rightTop: Float, rightBottom: Float, leftBottom: Float) {
        mRadii = floatArrayOf(leftTop, leftTop, rightTop, rightTop, rightBottom, rightBottom, leftBottom, leftBottom)

        updateDrawable()
    }

    private fun resolveResource(): Drawable? {
        var d: Drawable? = null

        if (mResource != 0) {
            try {
                d = ContextCompat.getDrawable(context, mResource)
            } catch (e: NotFoundException) {
                mResource = 0
            }

        }
        return RoundedCornerDrawable.fromDrawable(d, resources)
    }

    private fun updateDrawable() {
        if (mDrawable == null) return

        (mDrawable as RoundedCornerDrawable).setCornerRadii(mRadii)
    }

    private class RoundedCornerDrawable private constructor(private val mBitmap: Bitmap, r: Resources) : Drawable() {
        private val mBounds = RectF()

        private val mBitmapRect = RectF()
        private val mBitmapWidth: Int
        private val mBitmapHeight: Int

        private val mBitmapPaint: Paint

        private val mBitmapShader: BitmapShader

        private val mRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        private val mPath = Path()
        private var mBoundsConfigured = false

        init {
            mBitmapShader = BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

            mBitmapWidth = mBitmap.getScaledWidth(r.displayMetrics)
            mBitmapHeight = mBitmap.getScaledHeight(r.displayMetrics)

            mBitmapRect.set(0f, 0f, mBitmapWidth.toFloat(), mBitmapHeight.toFloat())

            mBitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            mBitmapPaint.style = Paint.Style.FILL
            mBitmapPaint.shader = mBitmapShader
        }

        companion object {
            fun fromBitmap(bitmap: Bitmap?, r: Resources): RoundedCornerDrawable? {
                return if (bitmap != null)
                    RoundedCornerDrawable(bitmap, r)
                else
                    null
            }

            fun fromDrawable(drawable: Drawable?, r: Resources): Drawable? {
                if (drawable != null) {
                    if (drawable is RoundedCornerDrawable) {
                        return drawable
                    } else if (drawable is LayerDrawable) {
                        val ld = drawable as LayerDrawable?
                        val num = ld!!.numberOfLayers
                        for (i in 0 until num) {
                            val d = ld.getDrawable(i)
                            ld.setDrawableByLayerId(ld.getId(i), fromDrawable(d, r))
                        }
                        return ld
                    }

                    val bm = drawableToBitmap(drawable)
                    if (bm != null) return RoundedCornerDrawable(bm, r)
                }
                return drawable
            }

            fun drawableToBitmap(drawable: Drawable?): Bitmap? {
                if (drawable == null) return null

                if (drawable is BitmapDrawable) {
                    return drawable.bitmap
                }

                var bitmap: Bitmap?
                val width = Math.max(drawable.intrinsicWidth, 2)
                val height = Math.max(drawable.intrinsicHeight, 2)
                try {
                    bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888)
                    val canvas = Canvas(bitmap!!)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                    bitmap = null
                }

                return bitmap
            }
        }

        private fun configureBounds(canvas: Canvas) {
            val canvasMatrix = canvas.matrix

            applyScaleToRadii(canvasMatrix)
            mBounds.set(mBitmapRect)
        }

        private fun applyScaleToRadii(m: Matrix) {
            val values = FloatArray(9)
            m.getValues(values)
            for (i in mRadii.indices) {
                mRadii[i] = mRadii[i] / values[0]
            }
        }

        override fun draw(canvas: Canvas) {
            canvas.save()
            if (!mBoundsConfigured) {
                configureBounds(canvas)
                mBoundsConfigured = true
            }
            mPath.addRoundRect(mBounds, mRadii, Path.Direction.CW)
            canvas.drawPath(mPath, mBitmapPaint)
            canvas.restore()
        }

        internal fun setCornerRadii(radii: FloatArray?) {
            if (radii == null) return
            if (radii.size != 8)
                throw ArrayIndexOutOfBoundsException("radii[] needs 8 values")

            System.arraycopy(radii, 0, mRadii, 0, radii.size)
        }

        override fun getOpacity(): Int {
            return if (/*mBitmap == null || */mBitmap.hasAlpha() || mBitmapPaint.alpha < 255)
                PixelFormat.TRANSLUCENT
            else
                PixelFormat.OPAQUE
        }

        override fun setAlpha(alpha: Int) {
            mBitmapPaint.alpha = alpha
            invalidateSelf()
        }

        override fun setColorFilter(cf: ColorFilter?) {
            mBitmapPaint.colorFilter = cf
            invalidateSelf()
        }

        override fun setDither(dither: Boolean) {
            mBitmapPaint.isDither = dither
            invalidateSelf()
        }

        override fun setFilterBitmap(filter: Boolean) {
            mBitmapPaint.isFilterBitmap = filter
            invalidateSelf()
        }

        override fun getIntrinsicWidth(): Int {
            return mBitmapWidth
        }

        override fun getIntrinsicHeight(): Int {
            return mBitmapHeight
        }
    }
}
