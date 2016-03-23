package com.example.taptap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.taptap.Game.Etat;

public class Dessin extends View{

	private Paint p;
	private Etat etat;

	public Dessin(Context context) {
		this(context, null, 0);
		this.etat = Etat.AWAKE;
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
		radius = 60;
		p.setStyle(Paint.Style.FILL);
		p.setColor(Color.WHITE);
		canvas.drawPaint(p);
		p.setColor(Deuxieme_activite.color);
		if (Deuxieme_activite.form == Form.CARRE){
			Rect r = new Rect(x/2 - radius, y/2 - radius, x/2 + radius, y/2 + radius);
			canvas.drawRect(r, p);
		}
		else{
			canvas.drawCircle(x / 2, y / 2, radius, p);
		}
		p.setColor(Color.BLACK);
		if (this.etat == Etat.SLEEP){
			canvas.drawText("Zzz", x/2 + (radius*1.2F), y/2 - (radius*1.2F), p);
		}
		else {
			canvas.drawOval(new RectF(x/2 - (radius/3)*1.6F, y/2 - (radius/3)*1.6F, x/2 - radius/3, y/2 - (radius/3)), p);
			canvas.drawOval(new RectF(x/2 + (radius/3)*1.45F, y/2 - (radius/3)*1.6F, x/2 + (radius/3)*2, y/2 - (radius/3)), p);
			p.setColor(Color.WHITE);
			canvas.drawOval(new RectF(x/2 - (radius/3)*2, y/2 - (radius/3)*2, x/2 - radius/3, y/2 - radius/4), p);
			canvas.drawOval(new RectF(x/2 + (radius/3), y/2 - (radius/3)*2, x/2 + (radius/3)*2, y/2 - radius/4), p);
		}
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

}
