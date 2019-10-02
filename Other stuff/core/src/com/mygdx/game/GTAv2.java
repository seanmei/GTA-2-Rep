package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GTAv2 extends ApplicationAdapter {
	public final int walkSpeed = 2;
	//double moveSpeed;
	SpriteBatch batch;
	Sprite bul;
	Texture b1;
	int counter; 
	int direction = 0;
	//using degrees , 90 = up. 0 = right. 270 = down . 180 = left 
	ArrayList<Bullet>bulletList;
	ArrayList<Sprite>spriteList;
	Texture tmp;
	Sprite sp;
	Sprite map;
	OrthographicCamera camera;
	
	NPC Sean;
	Person Jan;
	@Override
	public void create () {
		batch = new SpriteBatch();
		spriteList = new ArrayList<Sprite>();
		for(int i = 0; i < 5; i++){
			String link = i+".png"; 
			tmp = new Texture(link);
			sp = new Sprite(tmp);
			spriteList.add(sp);
		}
		Sean = new NPC(1050,1050,100, spriteList, 0);
		Jan = new Person(1000,1050,100, spriteList, 0, Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT);
		b1= new Texture ("bul.png");
		bulletList = new ArrayList<Bullet>();
		//moveSpeed = walkSpeed;
		map = new Sprite(new Texture("map2.jpg"));
		camera = new OrthographicCamera(1024, 576);
		camera.zoom = (float)0.7;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		//if()
		camera.position.set(Jan.getX(), Jan.getY(), (float) 0.5);
		camera.update();
		map.draw(batch);
		//counter+=1;
		Jan.update();
		//Sean.update();
		
		bullets();
		batch.end();
	}
	
	public void bullets(){
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).move();
			bulletList.get(i).pic.draw(batch);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	
	
	
	
	
	
	
	
	
	

	/////////////////////////////////////////Person
	
	class Person{
		int health, up, down, left, right;
		int direction, moveSpeed = 5;
		Rectangle hitbox;
		int counter = 0;
		ArrayList<Sprite>spList;
		public Person(int px, int py, int n, ArrayList<Sprite>sl, int di, int u, int d, int l, int r){ //constructor 
			health = n;
			hitbox = new Rectangle(px, py, 30, 30);
			spList = sl;
			direction = di;
			up = u;
			down = d;
			left = l;
			right = r;
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void moveR(){
			hitbox.x += moveSpeed;
		}
		public void moveL(){
			hitbox.x -= moveSpeed;
		}
		public void moveD(){
			hitbox.y -= moveSpeed;
		}
		public void moveU(){
			hitbox.y += moveSpeed;
		}
		public void shoot(){
			Bullet tmp = new Bullet(getX()+10, getY()+10, direction, b1);
			bulletList.add(tmp);
		}
		public void handleInput(){
			int pos = counter/10;
			spList.get(pos).setPosition(getX(),getY());
			spList.get(0).setPosition(getX(),getY());
			if(Gdx.input.isKeyPressed(right)){
				direction = 0;
				if(Gdx.input.isKeyPressed(up)){
					direction += 45;
				}
				else if(Gdx.input.isKeyPressed(down)){
					direction -= 45;
				}
				spList.get(pos).setRotation(direction);
				spList.get(pos).draw(batch);
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else if(Gdx.input.isKeyPressed(left)){
				direction = 180;
				if(Gdx.input.isKeyPressed(up)){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(down)){
					direction += 45;
				}
				spList.get(pos).setRotation(direction);
				spList.get(pos).draw(batch);
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else if(Gdx.input.isKeyPressed(up)){
				if(Gdx.input.isKeyPressed(right)){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(left)){
					direction += 45;
				}
				direction = 90;
				spList.get(pos).setRotation(direction);
				spList.get(pos).draw(batch);
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else if(Gdx.input.isKeyPressed(down)){
				if(Gdx.input.isKeyPressed(left)){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(right)){
					direction += 45;
				}
				direction = 270;
				spList.get(pos).setRotation(direction);
				spList.get(pos).draw(batch);
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else{
				spList.get(0).setRotation(direction);
				spList.get(0).draw(batch);
			}
			counter = counter % 49;
			
			
		}
		/*public void handleInput(){
			if(Gdx.input.isKeyPressed(left)){
				moveL();
			}
			if(Gdx.input.isKeyPressed(right)){
				moveR();
			}
			if(Gdx.input.isKeyPressed(up)){
				moveU();
			}
			if(Gdx.input.isKeyPressed(down)){
				moveD();
			}
			if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
				shoot();
			}
		}*/
		public void update(){
			handleInput();
			counter += 1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////NPC
	class NPC{
		int health;
		int direction, moveSpeed = walkSpeed;
		Rectangle hitbox;
		int counter = 0;
		ArrayList<Sprite>spList;
		public NPC(int px, int py, int n, ArrayList<Sprite>sl, int di){ //constructor 
			health = n;
			hitbox = new Rectangle(px, py, 30, 30);
			spList = sl;
			direction = di;
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void moveR(){
			hitbox.x += moveSpeed;
		}
		public void moveL(){
			hitbox.x -= moveSpeed;
		}
		public void moveD(){
			hitbox.y -= moveSpeed;
		}
		public void moveU(){
			hitbox.y += moveSpeed;
		}
		public void shoot(){
			Bullet tmp = new Bullet(getX()+10, getY()+10, direction, b1);
			bulletList.add(tmp);
		}
		public void update(){
			counter += 1;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////Bullets
	class Bullet{
		float x, y; 
		int direction;
		Sprite pic;
		public Bullet(float bx, float by, int d, Texture p){
			x = bx;
			y = by;
			direction = d;
			pic = new Sprite(p);
			pic.setRotation(direction-90);
		}
		public void move(){
			x += (5*Math.cos(Math.toRadians(direction)));
			y += (5*Math.sin(Math.toRadians(direction)));
			pic.setPosition(x, y);
		}
	}
}
