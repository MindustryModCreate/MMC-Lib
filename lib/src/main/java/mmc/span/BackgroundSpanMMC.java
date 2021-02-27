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

    public BackgroundSpanMMC() {
        //super();
        backgroundColor = Color.GRAY;
        textColor = Color.BLACK;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x + measureText(paint, text, start, end), bottom);
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.STROKE);
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
