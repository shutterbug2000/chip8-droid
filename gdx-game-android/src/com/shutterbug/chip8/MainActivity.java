package com.shutterbug.chip8;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.widget.*;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        initialize(new MyGdxGame(this), cfg);
    }
	public void toastdisplay(char opcode){
		Toast.makeText(getApplicationContext(), "Unsupported opcode: " + Integer.toHexString(opcode), Toast.LENGTH_LONG).show();
		}
		
}
