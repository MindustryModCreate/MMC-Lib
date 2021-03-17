package mmc.span;
import android.text.style.ReplacementSpan;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Color;
import android.content.Context;

public class BackgroundSpanMMC extends ReplacementSpan {

    private static int CORNER_RADIUS = 10;
    private int backgroundColor = 0;
    private int textColor = 0;
    private float size = 5;
    public BackgroundSpanMMC(int text,int background,float size) {
        //super();
        this.backgroundColor = background;
        this.textColor = text;
        this.size = size/2/2;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }

    private float measureText(Paint paint, CharSequence text, int start, int end) {
        return paint.measureText(text, start, end);
    }
}
