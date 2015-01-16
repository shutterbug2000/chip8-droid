package com.shutterbug.chip8;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.shutterbug.chip8.Chip.*;
import android.content.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.Pixmap.Format;
import java.io.*;
import com.badlogic.gdx.Input.Keys;
import android.util.*;
import java.util.logging.*;
import com.badlogic.gdx.files.FileHandle;
import java.nio.charset.*;

public class MyGdxGame implements ApplicationListener
{
	public OrthographicCamera camera;
	public Context c;
	public MyGdxGame(Context cin){
		c = cin;
	}
	Texture texture;
	Texture w;
	Texture b;
	SpriteBatch batch;
	test chip = new test();
	public Texture w2;
	public Texture b2;
	public byte[] display;
	public int displen;
	public FPSLogger logger;
	public FileHandle vertex;
	public FileHandle fragment;
	ShaderProgram shader;
	Mesh mesh;
	float time = 0;

	@Override
	public void create()
	{
		ShaderProgram.pedantic = false;
			vertex = new FileHandle("/sdcard/V.vert");
			fragment = new FileHandle("/sdcard/Frag.frag");
			
		logger = new FPSLogger();
		Pixmap pixmapb = new Pixmap( 10,10, Format.RGBA8888);
		pixmapb.setColor( 0, 0, 0, .0000000000000000001f );
		pixmapb.fillRectangle(1,1,10,10);
		b2 = new Texture( pixmapb );
		pixmapb.dispose();
		Pixmap pixmapw = new Pixmap( 10,10, Format.RGBA8888);
		pixmapw.setColor( 1, 0, 1, 1f );
		pixmapw.fillRectangle(1,1,10,10);
		w2= new Texture( pixmapw );
		pixmapw.dispose();
		// w = new Texture(Gdx.files.internal("whitebox.jpg"));
		// b = new Texture(Gdx.files.internal("blackbox.jpg"));
		texture = new Texture(Gdx.files.internal("android.jpg"));
		String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "uniform mat4 u_projTrans;\n" //
			+ "varying vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "\n" //
			+ "void main()\n" //
			+ "{\n" //
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
			+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "}\n";
		String fragmentShader = "#ifdef GL_ES\n" +
			"precision mediump float;\n" + 
			"#endif\n" + 
			"varying vec4 v_color;\n" + 
			"varying vec2 v_texCoords;\n" + 
			"uniform sampler2D u_texture;\n" + 
			"void main()                                  \n" + 
			"{                                            \n" + 
			"  gl_FragColor =  gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0) * texture2D(u_texture, v_texCoords);\n" +
			"}";
			Log.d("cggg",fragment.toString());
			shader = new ShaderProgram(vertexShader, fragment.readString());
		Log.d("jjjjj",shader.getLog());
		batch = new SpriteBatch(1000,shader);
		camera = new OrthographicCamera(640, 320);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		chip.init();
		chip.loadProgram("/storage/emulated/0/Download/pong2.c8");
		display = chip.display;
		displen = display.length;
		}

	@Override
	public void render()
	{      
		logger.log();
		chip.run();
		chip.randkey();
		Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		
		for(int i = 0; i < displen; i++) {
			if(display[i] == 0){
				/**
				int x = (i % 64);
				int y = (int)Math.floor(i / 64);
				batch.draw(b2, (x * 10), (y * 10), 10, 10);
				
				*/
			
			} else{
				int x = (i % 64);
				int y = (int)Math.floor(i / 64);
				batch.begin();
				batch.draw(w2, (x * 10), (y * 10), 10, 10);
				time = (float)(time + .0001);
				shader.setUniformf("time", time);
				batch.end();
			}
			}
		
		boolean isp1uPressed = Gdx.input.isKeyPressed(Keys.W);
		boolean isp1dPressed = Gdx.input.isKeyPressed(Keys.S);
		boolean isp2uPressed = Gdx.input.isKeyPressed(Keys.I);
		boolean isp2dPressed = Gdx.input.isKeyPressed(Keys.K);
		if(isp1uPressed){
			chip.keys[1] = 1;
			} else if(isp1dPressed){
				
			chip.keys[4] = 1;
			}else if(isp2uPressed){
				chip.keys[12] = 1;
				}else if(isp2dPressed){
				chip.keys[13] = 1;
				}
				else{
					chip.keys[1] = 0;
					chip.keys[10] = 0;
					chip.keys[12] = 0;
					chip.keys[15] = 0;
				}
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
	public void storage(){
		texture = new Texture(Gdx.files.internal("android.jpg"));
		batch = new SpriteBatch();
	}
	
}
