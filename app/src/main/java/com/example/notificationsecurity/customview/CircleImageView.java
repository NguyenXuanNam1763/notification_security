package com.example.notificationsecurity.customview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import androidx.appcompat.widget.AppCompatImageView;

public class CircleImageView extends AppCompatImageView {
    private static final ScaleType SCALE_TYPE;
    private static final Config CONFIG;
    private final RectF f9991c;
    private final RectF f9992d;
    private final Matrix f9993e;
    private final Paint f9994f;
    private final Paint f9995g;
    private int f9996h;
    private int f9997i;
    private Bitmap f9998j;
    private BitmapShader f9999k;
    private int f10000l;
    private int f10001m;
    private float f10002n;
    private float f10003o;
    private ColorFilter f10004p;
    private boolean f10005q;
    private boolean f10006r;

    public CircleImageView(Context context) {
        super(context);
        this.f9991c = new RectF();
        this.f9992d = new RectF();
        this.f9993e = new Matrix();
        this.f9994f = new Paint();
        this.f9995g = new Paint();
        this.f9996h = -16777216;
        this.f9997i = 0;
        this.m13227a();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9991c = new RectF();
        this.f9992d = new RectF();
        this.f9993e = new Matrix();
        this.f9994f = new Paint();
        this.f9995g = new Paint();
        this.f9996h = -16777216;
        this.f9997i = 0;
        this.m13227a();
    }

    private void m13227a() {
        this.f9997i = DensityUtilMS.dip2pxx(this.getContext(), 2.0F);
        this.f9996h = -1;
        super.setScaleType(SCALE_TYPE);
        this.f10005q = true;
        if (this.f10006r) {
            this.setup();
            this.f10006r = false;
        }

    }

    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void onDraw(Canvas canvas) {
        if (this.getDrawable() != null) {
            canvas.drawCircle((float)(this.getWidth() / 2), (float)(this.getHeight() / 2), this.f10002n, this.f9994f);
            if (this.f9997i != 0) {
                canvas.drawCircle((float)(this.getWidth() / 2), (float)(this.getHeight() / 2), this.f10003o, this.f9995g);
            }
        }

    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.setup();
    }

    public int getBorderColor() {
        return this.f9996h;
    }

    public void setBorderColor(int i) {
        if (i != this.f9996h) {
            this.f9996h = i;
            this.f9995g.setColor(this.f9996h);
            this.invalidate();
        }

    }

    public int getBorderWidth() {
        return this.f9997i;
    }

    public void setBorderWidth(int i) {
        if (i != this.f9997i) {
            this.f9997i = i;
            this.setup();
        }

    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.f9998j = bitmap;
        this.setup();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.f9998j = this.m13226a(drawable);
        this.setup();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.f9998j = this.m13226a(this.getDrawable());
        this.setup();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.f9998j = this.m13226a(this.getDrawable());
        this.setup();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.f10004p) {
            this.f10004p = colorFilter;
            this.f9994f.setColorFilter(this.f10004p);
            this.invalidate();
        }

    }

    private Bitmap m13226a(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        } else {
            try {
                Bitmap bitmap;
                if (drawable instanceof ColorDrawable) {
                    bitmap = Bitmap.createBitmap(2, 2, CONFIG);
                } else {
                    bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), CONFIG);
                }

                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmap;
            } catch (OutOfMemoryError var4) {
                return null;
            }
        }
    }

    private void setup() {
        if (!this.f10005q) {
            this.f10006r = true;
        } else if (this.f9998j != null) {
            this.f9999k = new BitmapShader(this.f9998j, TileMode.CLAMP, TileMode.CLAMP);
            this.f9994f.setAntiAlias(true);
            this.f9994f.setShader(this.f9999k);
            this.f9995g.setStyle(Style.STROKE);
            this.f9995g.setAntiAlias(true);
            this.f9995g.setColor(this.f9996h);
            this.f9995g.setStrokeWidth((float)this.f9997i);
            this.f10001m = this.f9998j.getHeight();
            this.f10000l = this.f9998j.getWidth();
            this.f9992d.set(0.0F, 0.0F, (float)this.getWidth(), (float)this.getHeight());
            this.f10003o = Math.min((this.f9992d.height() - (float)this.f9997i) / 2.0F, (this.f9992d.width() - (float)this.f9997i) / 2.0F);
            this.f9991c.set((float)this.f9997i, (float)this.f9997i, this.f9992d.width() - (float)this.f9997i, this.f9992d.height() - (float)this.f9997i);
            this.f10002n = Math.min(this.f9991c.height() / 2.0F, this.f9991c.width() / 2.0F);
            this.m13228b();
            this.invalidate();
        }

    }

    private void m13228b() {
        this.f9993e.set((Matrix)null);
        float f3 = 0.0F;
        float f;
        float f2;
        if ((float)this.f10000l * this.f9991c.height() > this.f9991c.width() * (float)this.f10001m) {
            f2 = this.f9991c.height() / (float)this.f10001m;
            f = (this.f9991c.width() - (float)this.f10000l * f2) * 0.5F;
        } else {
            f2 = this.f9991c.width() / (float)this.f10000l;
            f3 = (this.f9991c.height() - (float)this.f10001m * f2) * 0.5F;
            f = 0.0F;
        }

        this.f9993e.setScale(f2, f2);
        this.f9993e.postTranslate((float)((int)(f + 0.5F) + this.f9997i), (float)((int)(f3 + 0.5F) + this.f9997i));
        this.f9999k.setLocalMatrix(this.f9993e);
    }

    static {
        SCALE_TYPE = ScaleType.CENTER_CROP;
        CONFIG = Config.ARGB_8888;
    }
}
