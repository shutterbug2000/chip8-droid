package com.shutterbug.chip8;
import android.view.*;
import android.content.*;
import android.graphics.*;
import com.shutterbug.chip8.Chip.*;
import android.util.*;

public class DrawingPanel extends View
{
	public Paint paint;
	public DrawingPanel(Context context, AttributeSet attrs){
		super(context, attrs);
	}
	Chip chip = new Chip();
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		byte[] display = chip.getDisplay();
		for(int i = 0; i < display.length; i++) {
			if(display[i] == 0){
				int x = (i % 64);
				int y = (int)Math.floor(i / 64);
				paint = new Paint();
				paint.setColor(Color.BLACK);
				canvas.drawRect(x * 10, y * 10, x * 10 + 10, y * 10 + 10, paint);

			} else{
				int x = (i % 64);
				int y = (int)Math.floor(i / 64);
				paint = new Paint();
				paint.setColor(Color.WHITE);
				canvas.drawRect(x * 10, y * 10, x * 10 + 10, y * 10 + 10, paint);
				
			}
			}
			}
			}
