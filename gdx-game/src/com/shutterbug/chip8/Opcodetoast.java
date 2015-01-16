package com.shutterbug.chip8;
import android.app.*;
import android.widget.*;
import android.content.*;


public class Opcodetoast
{
	public void toastdisplay(char opcode, Context c){
		AlertDialog ad = new AlertDialog.Builder(c).create();
		ad.setCancelable(true);
		ad.setMessage("Hello World");
		ad.show();
		Toast.makeText(c, "Unsupported opcode: " + Integer.toHexString(opcode), Toast.LENGTH_LONG).show();
	}
	
}
