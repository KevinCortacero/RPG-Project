package com.example.taptap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Dessin extends View{

	private Paint p;

	public Dessin(Context context) {
		this(context, null, 0);
	}

	public Dessin(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public Dessin(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		p = new Paint();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int x = getWidth();
		int y = getHeight();
		int radius;
		radius = 50;
		p.setStyle(Paint.Style.FILL);
		p.setColor(Color.WHITE);
		canvas.drawPaint(p);
		// Use Color.parseColor to define HTML colors
		p.setColor(Deuxieme_activite.color);
		if (Deuxieme_activite.form == Form.CARRE){
			Rect r = new Rect(x/2 - radius, y/2 - radius, x/2 + radius, y/2 + radius);
			canvas.drawRect(r, p);
		}
		else
			canvas.drawCircle(x / 2, y / 2, radius, p);
	}
}