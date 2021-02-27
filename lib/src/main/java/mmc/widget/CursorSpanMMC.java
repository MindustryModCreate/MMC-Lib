package mmc.widget;

import android.text.style.ReplacementSpan;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Color;
import android.content.Context;

public class CursorSpanMMC extends ReplacementSpan {

    private static int CORNER_RADIUS = 0;
    private int backgroundColor = 0;
    private int textColor = 0;

    public CursorSpanMMC() {
        super();
        backgroundColor = Color.BLUE;
        textColor = Color.BLACK;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        RectF rect = new RectF(x, top, x+1, bottom);
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, paint);
        paint.setColor(textColor);
        canvas.drawText(text, start, end, x, y, paint);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return Math.round(paint.measureText(text, start, end));
    }
}
