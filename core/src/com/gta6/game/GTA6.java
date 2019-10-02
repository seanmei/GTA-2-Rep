package com.gta6.game;


import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class GTA6 extends ApplicationAdapter {
	BitmapFont font;
	public final int glock = 0;
	public final int shotgun = 1;
	public final int flamethrower = 2;
	public final int punch = 3;
	public final int walkSpeed = 2;
	public final int sprintSpeed = 4;
	public String mode="foot"; 
	public boolean running = true; 
	boolean suitcase= false; 
	boolean hold = false;
	boolean g1= false;
	boolean g2= false;
	boolean g3= false;
	String page;
	public String game; 
	SpriteBatch batch;
	SpriteBatch hudbatch;
	Sprite bul;
	Texture b1;
	Texture hb;
	int counter; 
	int direction = 0;
	int timmer; 
	//using degrees , 90 = up. 0 = right. 270 = down . 180 = left 
	ArrayList<Bullet>bulletList;
	ArrayList<Sprite>spriteList;
	ArrayList<Weapon> tmpwlist;
	ArrayList<Sprite>spl;
	ArrayList<Sprite>pl;
	
	//ArrayList<NPC>poeple;
	Texture tmp;
	Texture menu,play,credits,controls,play2,credits2,controls2;
	int mouseY=0;
	int mouseX=0;
	Sprite sp;
	Sprite map;
	Sprite dead; 
	OrthographicCamera camera;
	String type;
	
	ArrayList<NPC> people; 
	ArrayList<Reload>points;
	Person Jan;
	Pixmap mask;
	Car vroom;
	int wall, water, turn,di, road;
	Texture health;
	Texture face;
	Texture bbar;
	Texture wasted;
	Texture fire;
	Texture cpage;
	Texture instruct, instruct2;
	Texture hud;
	Texture glockhud;
	Texture shothud;
	Texture flamehud;
	Texture car1;
	NPC Sean;
	Texture p,p2;
	float x;
	float y;
	
	Texture hp;
	ArrayList<Body>bodyList;
	Texture newhud;
	Texture chosen;
	Texture stamina;
	Texture target;
	Texture check; 
	Texture suit;
	Texture arrow; 
	Texture gun; 
	//mission counters
	int kills; 
	int scar;
	int hcar;
	Rectangle casebox;
	Rectangle drop; 
	Rectangle reload1;
	Rectangle reload2;
	Rectangle reload3;
	
	ArrayList<Vector>spawnList;
	Texture wantedbar;
	Texture blue;
	Texture red;
	Texture chp;
	ArrayList<Cop>police;
	
	
	
	@Override
	public void create () {
		spawnList = new ArrayList<Vector>();
		spawnList.add(new Vector(1386.8284,764.2014,0));
		spawnList.add(new Vector(3001.7666,1055.8898,0));
		spawnList.add(new Vector(3428.2927,1585.3267,0));
		spawnList.add(new Vector(3826.1353,2143.0825,0));
		spawnList.add(new Vector(2837.921,2273.192,180));
		spawnList.add(new Vector(2532.7502,1910.9072,270));
		spawnList.add(new Vector(1155.78,1688.5044,270));
		spawnList.add(new Vector(827.0935,1585.6165,180));
		spawnList.add(new Vector(734.7502,2262.9546,90));
		spawnList.add(new Vector(2552.7363,1052.9211,0));
		spawnList.add(new Vector(729.28613,1032.0554,270));
		spawnList.add(new Vector(729.28613,742.0554,270));
		spawnList.add(new Vector(625.1443,573.9136,180));
		spawnList.add(new Vector(503.1443,573.9136,0));
		spawnList.add(new Vector(3307.485,1585.9756,0));
		spawnList.add(new Vector(3157.485,1585.9756,180));
		spawnList.add(new Vector(3053.485,1585.9756,0));
		police = new ArrayList<Cop>();
		chp = new Texture("cophbar.png");
		wantedbar = new Texture("wantedbar.png");
		blue = new Texture("blue.png");
		red = new Texture("red.png");
		
		people = new ArrayList<NPC>();
		
		for(int a = 0; a < 10; a++){
			ArrayList<Sprite>dollyslist = new ArrayList<Sprite>(); 
			for(int i = 0; i < 5; i++){
				dollyslist.add(new Sprite(new Texture(i+".png")));
			}
			for(int i = 1; i <= 3; i++){
				dollyslist.add(new Sprite(new Texture("punch"+i+".png")));
			}
			dollyslist.add(new Sprite(new Texture("dead.png")));
			Vector z = spawnList.get(randint(0,spawnList.size()-1));
			people.add(new NPC ((int)z.x,(int)z.y,100,dollyslist,0,12,0));
		
		target = new Texture("target.png");
		font = new BitmapFont();
		font.getData().setScale(2f,2f);
		
		
		hb = new Texture("hitbox.png");
		health = new Texture("health.png");
		face = new Texture("face.png");
		bbar = new Texture("bulletbar.png");
		wasted = new Texture("wasted.png");
		cpage = new Texture("creditspage.png");
		instruct = new Texture("instruct.png");
		hud = new Texture("hud.png");
		glockhud = new Texture("glock.png");
		shothud = new Texture("shotgun.png");
		flamehud = new Texture("flamethrower.png");
		car1 = new Texture ("car1.png");
		people = new ArrayList<NPC>();
		points = new ArrayList<Reload>(); 
		batch = new SpriteBatch();
		
		
		mask = new Pixmap(Gdx.files.internal("mask.png"));
		wall = cnum(0,0,255,255);
		water = cnum(255,0,0,255);
		turn = cnum(0,255,0,255);
		road = cnum (100,100,100,255);
		
		spriteList = new ArrayList<Sprite>();
		for(int i = 0; i < 5; i++){
			String link = i+".png"; 
			tmp = new Texture(link);
			sp = new Sprite(tmp);
			spriteList.add(sp);
		}
		//spriteList.add(new Sprite(new Texture("shoot.png")));
		//spriteList.add(new Sprite(new Texture("dead.png")));
		spl = new ArrayList<Sprite>();
		for(int i = 0; i < 5; i++){
			spl.add(new Sprite(new Texture(i+".png")));
		}
		for(int i = 1; i <= 3; i++){
			spl.add(new Sprite(new Texture("punch"+i+".png")));
		}
		pl = new ArrayList<Sprite>();
		for(int i = 0; i < 5; i++){
			pl.add(new Sprite(new Texture(i+".png")));
		}
		for(int i = 1; i <= 3; i++){
			pl.add(new Sprite(new Texture("punch"+i+".png")));
		}
		Jan = new Person(1950,1050,100, spriteList, 0, Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT);
		vroom = new Car(0,0,2150,1250,100,car1);
		
		spl.add(new Sprite(new Texture("dead.png")));
		pl.add(new Sprite(new Texture("dead.png")));
		Jan.spList.get(0).setPosition(Jan.getX(),Jan.getY());
		Jan.spList.get(0).setRotation(Jan.direction);
		people.add(new NPC (1600,1054,100,spl,0,12,0));
		people.add(new NPC (2000,1054,100,pl,180,12,0));
		//people.add(new NPC (1000,1000,100,pl,0,12,0));
		people.get(0).spList.get(0).setPosition(people.get(0).getX(),people.get(0).getY());
		people.get(0).spList.get(0).setRotation(people.get(0).direction);
		
		points.add(new Reload(1440,1345,0,true));
		points.add(new Reload(300,2750,0,true));
		points.add(new Reload(2540,1490,0,true));
		points.add(new Reload(1270,2190,0,true));
		
		
		b1 = new Texture ("bul.png");
		fire = new Texture("fire.png"); 
		bulletList = new ArrayList<Bullet>();
		map = new Sprite(new Texture("map2.jpg"));
		dead = new Sprite(new Texture ("dead.png"));
		camera = new OrthographicCamera(1024, 576);
		camera.zoom = (float)0.7;
		
		menu = new Texture("menu.png");
		play = new Texture("play1.png");
		controls = new Texture ("controls1.png");
		credits = new Texture ("credits1.png");
		play2 = new Texture("play2.png");
		controls2 = new Texture ("controls2.png");
		credits2 = new Texture ("credits2.png");
		type = "menu";
		page= "mission";
		instruct2 = new Texture ("instruct2.png");
		p = new Texture ("mission.png");
		p2 = new Texture("stats.png");
		check = new Texture("check.png");
		suit = new Texture("suitcase.png");
		arrow = new Texture("arrow.png");
		gun = new Texture("gun.png");
		
		x = Jan.getX();
		y = Jan.getY();
		
		hp = new Texture("npchbar.png");
		bodyList = new ArrayList<Body>();
		hudbatch = new SpriteBatch();
		newhud = new Texture("newhud.png");
		chosen = new Texture("chosen.png");
		stamina = new Texture("stamina.png");
		}
	}	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		mouseX=Gdx.input.getX();
        mouseY=576-Gdx.input.getY();
		if(type.equals("menu")){
			buttons();
		}
		if(type.equals("game")){
			//cam(vroom);
	
			batch.setProjectionMatrix(camera.combined);
			float clampedX = Math.max(400, Math.min(4120, Jan.getX()));
			float clampedY = Math.max(288, Math.min(3153, Jan.getY()));
			camera.position.set(clampedX, clampedY, (float) 0.5);
			camera.update();
			map.draw(batch);
			//Sean.update();
			
			
			for(int i = 0; i < people.size(); i++){
				people.get(i).update();
			}
			for(Body e : bodyList){
				batch.draw(e.pic,e.x, e.y);
			}

			if(Jan.alive){
				vroom.update();
				Jan.update();
			}
			else{
				batch.draw(wasted, clampedX - 147, clampedY - 147);
			}
			for(int i = 0; i < points.size(); i++){
				points.get(i).update();
			}
			bullets();
			suitcase();
		}
		if(type.equals("credits")){
			batch.draw(cpage,  0,  0);
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				type = "menu";
			}
		}
		if(type.equals("controls")){
			batch.draw(instruct,  0,  0);
			if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				type = "menu";
			}
		}
		batch.end();
		if(type == "game"){
			hudbatch.begin();
			hudbatch.draw(newhud,  0,  0);
			gbuttons();
			mouseX=Gdx.input.getX();
	        mouseY=576-Gdx.input.getY();
	        //missions();
			hudbatch.draw(chosen,  609 + Jan.equipped*74,  493);
			for(int i = 0; i < Jan.health; i++){
				hudbatch.draw(health,  70 + i*2,  528);
			}
			for(int i = 0; i < (int)Jan.stamina; i++){
				hudbatch.draw(stamina,  338 + i*2,  528);
			}
			Weapon eq = Jan.wlist.get(Jan.equipped);
			for(int i = 0; i < eq.ammo; i++){
				hudbatch.draw(bbar,  70 + i*2,  485);
			}
			int wl = Jan.wantedLevel;
			if(Jan.wantedLevel > 100){
				wl = 100;
			}
			for(int i = 0; i < wl; i++){
				hudbatch.draw(wantedbar,  338 + i*2,  485);
			}
			if(Jan.wanted == true){
				if(Jan.flash%30 <= 15){
					hudbatch.draw(red, 337, 483);
				}
				else{
					hudbatch.draw(blue, 337, 483);
				}
				Jan.flash += 1;
			}
	        if(game=="pause"){
	        	mbuttons();
	        	if(page.equals("mission")){
	        		hudbatch.draw(p,155,100);
	        		missions(); 
	        	}
	        	if(page.equals("stats")){
	        		hudbatch.draw(p2,155,100);
	        		stats();
	        	}
	        	if(page.equals("controls")){
	        		hudbatch.draw(p2,155,100);
	        		hudbatch.draw(instruct2,173,123);
	        	}
	        	if(page.equals("main")){
	        		batch.begin();
	        		type="menu";
	        		buttons();
	        		batch.end();
	        		page="";
	        	}
	        }
			hudbatch.end();
		}
	}
	public void spawnCop(){
		ArrayList<Sprite>dollyslist = new ArrayList<Sprite>(); 
		for(int i = 0; i < 5; i++){
			dollyslist.add(new Sprite(new Texture(i+".png")));
		}
		for(int i = 1; i <= 3; i++){
			dollyslist.add(new Sprite(new Texture("punch"+i+".png")));
		}
		police.add(new Cop (0,0,100,dollyslist,12,0));
		police.get(police.size()-1).respawn();
	}
	public int randint(int min, int max){
		double rand = Math.random()*(max+1-min);
		return((int)rand+min);
	}
	public boolean clear(float x, float y){
		boolean hit = true;
		int c=0;
		if(x<0 || x>= mask.getWidth() || y<0 || y>= mask.getHeight()){
			return false;
		}
		for(int i=0; i<20;i++){
			for(int a=0; a<30; a++ ){
				c = mask.getPixel(Math.round(x+i), mask.getHeight()- Math.round(y+a));
				if(c==wall ){
					hit = false;
				}
			}
		}
		if(water == c){
			Jan.health=0;
		}
		return hit ;
	}
	
	public void around(){
		for(NPC a: people){
			if(a.direction==0){
				a.direction=180;
			}
			else if(a.direction==180){
				a.direction=0;
			}
			else if(a.direction==90){
				a.direction=270;
			}
			else if(a.direction==270){
				a.direction=90;
			}
		}
	}
	public void NDirection(){
		int di = (int)(Math.random()*4);
		System.out.println(di);
		for(NPC a:people){
			if(di==0){
				a.direction=90;
				a.hitbox.y+=5;
				a.hitbox.x+=5;
			}
			if(di==1){
				a.direction=180;
				a.hitbox.x-=10;
			}
			if(di==2){
				a.direction=270;
				a.hitbox.y-=10;
				a.hitbox.x+=5;
			}
			if(di==3){
				a.direction=0;
				a.hitbox.x+=5;
			} 
		}
	}
	public int cnum(int r, int g, int b, int a){
		return (r<<24) + (g<<16) + (b<<8) + a;
	}
	public void missions(){ 
		if(kills>=10){
    		hudbatch.draw(check,737,245);
		}
		if(scar>=1){
			hudbatch.draw(check,737,365);
		}
		if(hcar>=1){
			hudbatch.draw(check,737,325);
		}
		if(g1==true && g2==true && g3==true){
			hudbatch.draw(check,737,285);
		}
		if(suitcase==true){
			hudbatch.draw(check,737,200);
		}
	}
	public void suitcase(){
		casebox = new Rectangle(385,870,40,40);
		drop = new Rectangle(3780,2400,70,70);
		if(hold==false && suitcase==false){
			batch.draw(suit,385,870);
		}
		if((Jan.hitbox).overlaps(casebox) && Gdx.input.isKeyPressed(Keys.ENTER)){
			hold=true;
		}
		if(hold==true && suitcase==false){
			batch.draw(arrow,3830,2400);
		}
		if((Jan.hitbox).overlaps(drop) && Gdx.input.isKeyPressed(Keys.ENTER)){
			suitcase=true;
		}
		
	}
	public void stats(){
		String k;
		String h; 
		String s;
		String t;
		int tot=kills-hcar;
		k= Integer.toString(kills);
		h= Integer.toString(hcar);
		s= Integer.toString(scar);
		t= Integer.toString(tot);
		font.draw(hudbatch,t,700,355);
		font.draw(hudbatch,h,700,305);
		font.draw(hudbatch,s,700,255);
		font.draw(hudbatch,k,700,205);
	}
	public void gbuttons(){
		Rectangle rectB= new Rectangle (900,500,100,100);
		Rectangle rectMouse= new Rectangle (mouseX,mouseY,1,1);
		//if((rectB).overlaps(rectMouse)  ){
		if((rectB).overlaps(rectMouse) && Gdx.input.isButtonPressed(Buttons.LEFT) && type.equals("game") ){
			game="pause";
			running = false; 
		}
		if(type.equals("game") &&  Gdx.input.isKeyPressed(Keys.P)){ 
			game = "";
			running = true; 
		}
	}
	public void mbuttons(){
		Rectangle rectMouse= new Rectangle (mouseX,mouseY,1,1);
		Rectangle rectp1= new Rectangle (160,400,120,50);
		Rectangle rectp2= new Rectangle (280,400,120,50);
		Rectangle rectp3= new Rectangle (520,400,120,50);
		if((rectp1).overlaps(rectMouse) && Gdx.input.isButtonPressed(Buttons.LEFT) ){
			page="mission";
		}
		if((rectp2).overlaps(rectMouse) && Gdx.input.isButtonPressed(Buttons.LEFT)  ){
			page = "stats";
		}
		if((rectp3).overlaps(rectMouse) && Gdx.input.isButtonPressed(Buttons.LEFT)  ){
			page = "main";
		}
	}	
	public void buttons(){
		batch.draw(menu,0,0);
		batch.draw(play,100,100);
		batch.draw(controls,350,100);
		batch.draw(credits,730,100);
		Rectangle rectP= new Rectangle (100, 100,150,78);
		Rectangle rectCon= new Rectangle (350, 100,240,78);
		Rectangle rectCre= new Rectangle (730, 100,272,78);
		Rectangle rectMouse= new Rectangle (mouseX,mouseY,1,1);
		
		if((rectP).overlaps(rectMouse) | Gdx.input.isKeyPressed(Keys.ENTER)){
			batch.draw(play2,100,100);
			if(Gdx.input.isButtonPressed(Buttons.LEFT) | Gdx.input.isKeyPressed(Keys.ENTER)){
				type="game";
			}
		}
		if((rectCre).overlaps(rectMouse)){
			batch.draw(credits2,730,100);
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				type="credits";
			}
		}
		if((rectCon).overlaps(rectMouse)){
			batch.draw(controls2,350,100);
			if(Gdx.input.isButtonPressed(Buttons.LEFT)){
				type="controls";
			}
		}
	}
	
	public void bullets(){
		ArrayList<Bullet>tmplistbul = new ArrayList<Bullet>();
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).move();
			bulletList.get(i).pic.draw(batch);
			bulletList.get(i).life += 1;
			if(bulletList.get(i).life == bulletList.get(i).range){
				tmplistbul.add(bulletList.get(i));
			}
		}
		bulletList.removeAll(tmplistbul);
		tmplistbul.clear();

	}	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	/////////////////////////////////////////Person	
	class Person{
		int copCounter = 0;
		int flash = 0;
		int wantedLevel = 0, wantedCounter = 0;
		boolean wanted = false;
		int health, up, down, left, right;
		float stamina = 100;
		int direction;
		double moveSpeed = 2.5;
		boolean alive = true;
		Rectangle hitbox;
		int counter = 0, regen = 0, rest = 0;
		ArrayList<Sprite>spList;
		Weapon w1 = new Weapon("bullet", 25, 2, 10, 100,"glock", 1, 0, 100);
		Weapon w2 = new Weapon("bullet", 40, 5, 34, 100,"shotgun", 5, 40, 20);
		Weapon w3 = new Weapon("fire", 2, 1, 2, 100,"flamethrower", 1, 40, 20);
		ArrayList<Weapon>wlist = new ArrayList<Weapon>();
		int equipped = glock;
		
		public Person(int px, int py, int n, ArrayList<Sprite>sl, int di, int u, int d, int l, int r){ //constructor 			
			health = n;
			hitbox = new Rectangle(px, py, 30, 30);
			spList = sl;
			direction = di;
			up = u;
			down = d;
			left = l;
			right = r;
			wlist.add(w1);
			wlist.add(w2);
			wlist.add(w3);
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void shoot(Weapon choice){
			choice.shoot(getX(), getY(), direction);
		}
		public void drawGuy(){
			if(mode=="foot"){
			int pos = counter/10;
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX(),getY());
				spList.get(i).setRotation(direction);
			}
				spList.get(pos).draw(batch);
			}
		}
		public void move(){
			if(mode=="foot"&& running == true){
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			if(mode=="car" && running == true){
				hitbox.x += (vroom.Fspeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (vroom.Fspeed*Math.sin(Math.toRadians(direction)));
			}
		}
		public float fx(int dir){
			return hitbox.x + (float)(moveSpeed*Math.cos(dir));
		}
		public float fy(int dir){
			return hitbox.y + (float)(moveSpeed*Math.sin(dir));
		}
		public void handleInput(){
			if(Gdx.input.isKeyJustPressed(Keys.P)){
				System.out.println(getX() + "," + getY());
			}
			if(running ==true){
				if(Gdx.input.isKeyJustPressed(Keys.NUM_1)){
					g1=true;
					equipped = glock;
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_2)){
					g2=true;
					equipped = shotgun;
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_3)){
					g3=true;
					equipped = flamethrower;
				}
				if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && stamina > 0 && mode=="foot"){
					stamina -= 0.4;
					rest = 0;
					moveSpeed = sprintSpeed;
				}
				else{
					moveSpeed = walkSpeed;
				}
			}
			if(Gdx.input.isKeyPressed(right)&& clear(fx(0),fy(0)) && running == true){
				direction = 0;
				if(Gdx.input.isKeyPressed(up)&& clear(fx(45),fy(45))){
					direction += 45;
				}
				else if(Gdx.input.isKeyPressed(down)&& clear(fx(-45),fy(-45))){
					direction -= 45;
				}
				move();
				drawGuy();
			}
			else if(Gdx.input.isKeyPressed(left)&& clear(fx(180),fy(180))&& running == true){
				direction = 180;
				if(Gdx.input.isKeyPressed(up)&& clear(fx(135),fy(135))){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(down)&& clear(fx(225),fy(225))){
					direction += 45;
				}
				move();
				drawGuy();
			}
			else if(Gdx.input.isKeyPressed(up)&& clear(fx(90),fy(90))&& running == true){
				direction = 90;
				if(Gdx.input.isKeyPressed(right)&& clear(fx(45),fy(45))){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(left)&& clear(fx(135),fy(135))){
					direction += 45;
				}
				move();
				drawGuy();
			}
			else if(Gdx.input.isKeyPressed(down)&& clear(fx(270),fy(270))&& running == true){
				direction = 270;
				if(Gdx.input.isKeyPressed(left)&& clear(fx(225),fy(225))){
					direction -= 45;
				}
				else if(Gdx.input.isKeyPressed(right)&& clear(fx(315),fy(315))){
					direction += 45;
				}
				move();
				drawGuy();
			}
			else {
				spList.get(0).setRotation(direction);
				spList.get(0).draw(batch);
			}
			if(Gdx.input.isKeyPressed(Keys.S) && mode=="foot" && running == true){
				wlist.get(equipped).shoot(Jan.getX(),Jan.getY(), direction);
			}
		}
		public void update(){
			if(health <= 0){
				alive = false;
			}
			if(mode == "foot"){
				handleInput();
			}
			counter += 1;
			counter = counter % 49;
			for(Weapon i:wlist){
				i.update();
			}
			rest += 1;
			if(rest >= 100){
				if(stamina < 100 && running ==true){
					stamina += 0.2;
				}
			}
			regen += 1;
			if(regen >= 80){
				if(health < 100){
					health += 1;
					regen = 0;
				}
			}
			if(wantedLevel >= 100){
				wanted = true;
				wantedLevel = 0;
				wantedCounter = 500;
			}
			if(wanted == false){
				if(wantedCounter == 0 && wantedLevel > 0){
					wantedLevel -= 1;
					wantedCounter -= 20; 
				}
				else{
					wantedCounter += 1;
				}
			}
			else if(wanted == true){
				copCounter += 1;
				if(copCounter >= 300 && police.size() < 15){
					spawnCop();
					copCounter = 0;
				}
				wantedCounter -= 1;
				if(wantedCounter == 0){
					wantedLevel = 99;
					wanted = false;
				}
			}
			
			for(Cop popo : police){
				float hi = camera.viewportHeight;
				if(popo.getX() <= Jan.getX() + hi && popo.getX() >= Jan.getX() - hi && popo.getY() <= Jan.getY() + hi && popo.getY() >= Jan.getY() - hi){
					wantedCounter = 500;
				}
			}
		}
		
	}
////////////////////////////NPC
	class NPC{
		int turntimer = 0;
		int pshift = SpritePos.Walk;
		boolean canHit = true;;
		String mood = "normal";
		int health;
		int direction;
		double moveSpeed = 1;
		Rectangle hitbox;
		int counter = 0;
		ArrayList<Sprite>spList;
		int money;
		int timer;
		int hittimer;
		public NPC(int px, int py, int n, ArrayList<Sprite>sl, int di, int mo, int t){ //constructor 
			health = n;
			hitbox = new Rectangle(px, py, 30, 30);
			spList = sl;
			direction = di;
			money = mo;
			timer = t;
		}
		public boolean nclear(float x, float y){
			boolean hit = true;
			int c=0;
			if(x<0 || x>= mask.getWidth() || y<0 || y>= mask.getHeight()){
				return false;
			}
			for(int i=0; i<20;i++){
				for(int a=0; a<20; a++ ){
					c = mask.getPixel(Math.round(x+i), mask.getHeight()- Math.round(y+a));
					if(c==wall ){
						hit = false;
					}
				}
			}
			if(water == c){
				respawn();
			}
			return hit ;
		}
		public boolean path(float x, float y){
			boolean hit = false;
			int c=0;
			c = mask.getPixel(Math.round(x), mask.getHeight()- Math.round(y));
			if(c==turn){
				hit = true;
			}
			return hit;
		}
		public void respawn(){
			canHit = true;;
			mood = "normal";
			health = 100;
			
			moveSpeed = 1;
			Vector a = spawnList.get(randint(0, spawnList.size()-1));
			while((a.x < Jan.getX()+512 && a.x > Jan.getX()-512) || (a.y < Jan.getY()+288 && a.y > Jan.getY()-288)){
				a = spawnList.get(randint(0, spawnList.size()-1));
			}
			hitbox.x = (float)a.x;
			hitbox.y = (float)a.y;
			direction = a.direction;
			counter = 0;
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX(),getY());
				spList.get(i).setRotation(direction);
			}
			money = randint(10, 14);
			timer = 0;
			hittimer = 0;
		}
		public void turnTime(){
			for(NPC a:people){
				a.timer++;
			}
		}
		public void die(){
			health = 100;
			bodyList.add(new Body(getX(), getY(), spList.get(SpritePos.Die).getTexture()));
			Jan.wantedLevel += 20;
			Jan.wantedCounter = -200;
			respawn();
		}
		public void getShot(){
			Bullet tmp = null;
			for(Bullet i : bulletList){
				if(hitbox.contains(i.x, i.y)){			
					if(health == 100){
						if((int)(Math.random()*2) == 0){
							counter = 0;
							mood = "mad";
						}
						else{
							mood = "scared";
						}
					}
					health -= i.damage;
					tmp = i;
				}
			}
			bulletList.remove(tmp);
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void move(){
			drawGuy();
			double newX = getX() + (moveSpeed*Math.cos(Math.toRadians(direction)));
			double newY = getY() + (moveSpeed*Math.sin(Math.toRadians(direction)));
			if(nclear((float)newX,(float)newY) == true && (path((float)newX+10,(float)newY+10) || mood != "normal")&& running == true){
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else if(nclear((float)newX,getY()) == true && mood != "normal"&& running == true){
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
			}
			else if(nclear(getX(),(float)newY) == true && mood != "normal"&& running == true){
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
		}
		public void drawGuy(){
			int pos = counter/10;
			if(running == true){
				counter += 1;
				counter = counter % 49;
			}
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX(),getY());
				spList.get(i).setRotation(direction);
			}
			spList.get(pos).draw(batch);
		}
		public void drawGuy(int cstep, int pshift, int posStep){
			int pos = counter/posStep + pshift;
			counter += 1;
			counter = counter % cstep;
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX()-10,getY()-2);
				spList.get(i).setRotation(direction);
			}
				spList.get(pos).draw(batch);
		}
		
		////////////////////////////
		public void collide(){
			int tp;
			int k;
			int c;
			for(NPC a:people){
				for(NPC b:people){
					if(a != b){
						if((a.hitbox).overlaps(b.hitbox)){
							k = a.direction;
							tp = b.direction;
							b.direction=tp;
							a.direction=k;
						}
					}
				}
			}
		}
		////////////////////////////
		public void update(){
			turntimer += 1;
			if(health <= 0){
				kills+=1;
				die();
			}
			for(int i = 0; i < (int)health/4; i++){
				batch.draw(hp, hitbox.x+2+i, hitbox.y + 30);
			}
			if(mood == "mad"){
				moveSpeed = 2;
				direction = (int)Math.toDegrees(Math.atan2( Jan.getY()-getY(),Jan.getX()-getX()));
			}
			else if(mood == "scared"){
				moveSpeed = 2;
				direction = 180+(int)Math.toDegrees(Math.atan2( Jan.getY()-getY(),Jan.getX()-getX()));
				move();
			}
			else{
				moveSpeed = 1;
			}
			
			if(hitbox.overlaps(Jan.hitbox)){
				if(mood == "mad" && Jan.health >0){
					if(hittimer == 30){
						Jan.regen = -100;
						if(mode=="foot"){
							Jan.health -= 20;
						}
					}
					hittimer += 1;
					if(hittimer == 40){
						hittimer = 0;
					}
					drawGuy(39,5, 15);  //cStep, pShift, posStep
				}
				else if(mood == "normal"){
					spl.get(0).setPosition(getX(),getY());
					int d = (int)Math.toDegrees(Math.atan2( Jan.getY()-getY(),Jan.getX()-getX()));
					spl.get(0).setRotation(d);
					spl.get(0).draw(batch);
				}
			}
			else if(mood != "scared"){//move();
				float lX = getX()+10 + (float)(20*Math.cos(Math.toRadians(direction+90)));
				float lY = getY()+10 + (float)(20*Math.sin(Math.toRadians(direction+90)));
				float rX = getX()+10 + (float)(20*Math.cos(Math.toRadians(direction-90)));
				float rY = getY()+10 + (float)(20*Math.sin(Math.toRadians(direction-90)));
				if(path(lX, lY) && randint(1,20) == 1 && turntimer > 100){
					direction += 90;
					move();
					turntimer = 0;	
				}
				else if(path(rX, rY) && randint(1,20) == 1 && turntimer > 100){
					direction -= 90;
					move();
					turntimer = 0;
				}
				else{
					move();
				}
				
			}
			getShot();
			//turnTime();
			collide();
			//batch.draw(hb, getX(), getY());
		}
	}
	//////////////////////////////////Bullets
	class Bullet{
		int life = 0, range;
		float x, y; 
		int direction;
		Sprite pic;
		int damage;
		public Bullet(float bx, float by, int d, Texture p, int da, int l){
			x = bx;
			y = by;
			direction = d;
			pic = new Sprite(p);
			pic.setRotation(direction-90);
			damage = da;
			range = l;
		}
		public void move(){
			if(running  == true){
				x += (10*Math.cos(Math.toRadians(direction)));
				y += (10*Math.sin(Math.toRadians(direction)));
				pic.setPosition(x, y);
			}
		}
	}	
	////////////////////////////////////////Weapon
	class Weapon{
		int range;
		int tph;   //time per hit
		int hitCost, ammo;
		int damage;
		boolean canShoot = true;
		int timer = 0;
		String name;
		int shots, accuracy;
		Texture pic, hudpic;
		public Weapon(String p, int h, int hc, int d, int a, String n, int s, int ac, int l){
			tph = h;
			hitCost = hc;
			damage = d;
			ammo = a;
			name = n;
			shots = s;
			accuracy = ac;
			range = l;
			pic = new Texture(p+".png");
			hudpic = new Texture(name+".png");
		}
		public void shoot(float x, float y, int direction){
			if(timer >= tph && ammo > 0){
				ammo -= hitCost;
				timer = 0;
				
				int variance;
				for(int i = 0; i <= shots; i++){
					variance = (int)(Math.random()*accuracy - accuracy/2); 
					Bullet tmp = new Bullet(x+10, y+10, direction + variance, pic, damage, range);
					bulletList.add(tmp);
				}
			}
		}
		public void update(){
			timer += 1;
		}
	}
	///////////////////////////Drop
	class Drop{
		int value;
		int ammo;
		float x, y;
		public Drop(float nx, float ny, int v, int a){
			x = nx;
			y = ny;
			value = v;
			ammo = a;
		}
	}
	class Car{
		float Fspeed;
		int health; 
		int direction;
		float x,y;
		Rectangle hitbox;
		String type; 
		Sprite car;
		boolean backward =false;
		boolean forward = false; 
		float bspeed; 
		public Car(float a,float t, float b, float c, int d,  Texture model) {
			//type = k;
			Fspeed = a; 
			bspeed = t; 
			x = b;
			y =c;
			//health =d;
			direction = d; 
			car = new Sprite(model);
			car.setRotation(direction);
			car.setPosition(x, y);
			hitbox = new Rectangle(x,y,100,44);
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void speed(){
			
		}
		public void turn(){
			if(Gdx.input.isKeyPressed(Keys.UP)&& clear(hitbox.x+(float)(Fspeed*Math.sin(Math.toRadians(direction))+5), hitbox.y+(float)(Fspeed*Math.sin(Math.toRadians(direction))+5))){
				if(Fspeed <= 5){
					Fspeed+=.1;
				}	
				forward=true; 
				//hitbox.y+=speed;
				y+=(Fspeed*Math.sin(Math.toRadians(direction)));
				x+=(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.x+=(float)(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.y+=(float)(Fspeed*Math.sin(Math.toRadians(direction)));
				hitbox.x=x;
				hitbox.y=y;
				car.setPosition(x, y);
			}
			if(Fspeed>0 && Gdx.input.isKeyPressed(Keys.DOWN)){
				Fspeed-=.1;
				y+=(Fspeed*Math.sin(Math.toRadians(direction)));
				x+=(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.x+=(float)(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.y+=(float)(Fspeed*Math.sin(Math.toRadians(direction)));
				hitbox.x=x;
				hitbox.y=y;
				car.setPosition(x, y);
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN) && clear(hitbox.x-(float)(bspeed*Math.cos(Math.toRadians(direction)))+5, hitbox.y-(float)(bspeed*Math.sin(Math.toRadians(direction)))-5)){
				if(bspeed<2){
					bspeed+=.1;
				}
				backward=true;
				//hitbox.y-=speed;
				y-=(bspeed*Math.sin(Math.toRadians(direction)));
				x-=(bspeed*Math.cos(Math.toRadians(direction)));
				hitbox.x=x;
				hitbox.y=y;
				Jan.hitbox.x-=(float)(bspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.y-=(float)(bspeed*Math.sin(Math.toRadians(direction)));
				car.setPosition(x, y);
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)== false && Gdx.input.isKeyPressed(Keys.UP)==false && Fspeed>0){
				Fspeed-=.05;
				y+=(Fspeed*Math.sin(Math.toRadians(direction)));
				x+=(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.x+=(float)(Fspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.y+=(float)(Fspeed*Math.sin(Math.toRadians(direction)));
				hitbox.x=x;
				hitbox.y=y;
				car.setPosition(x, y);
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)== false && Gdx.input.isKeyPressed(Keys.UP)==false && bspeed>0){ //car = 30X70
				bspeed-=.05;
				y-=(bspeed*Math.sin(Math.toRadians(direction)));
				x-=(bspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.x-=(float)(bspeed*Math.cos(Math.toRadians(direction)));
				Jan.hitbox.y-=(float)(bspeed*Math.sin(Math.toRadians(direction)));
				hitbox.x=x;
				hitbox.y=y;
				car.setPosition(x, y);
			}
			if(clear(hitbox.x, hitbox.y+10)==false){
				bspeed=0; 
				Fspeed=0;
			}
			car.draw(batch);
		}
		public void direction(){
			if(Gdx.input.isKeyPressed(Keys.LEFT) && forward==true){
				direction+=1;
				car.setRotation(direction);
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT) && forward==true){
				direction-=1;
				car.setRotation(direction);
			}
			forward=false; 
			if(Gdx.input.isKeyPressed(Keys.LEFT) && backward==true){
				direction-=1;
				car.setRotation(direction);
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT) && backward==true){
				direction+=1;
				car.setRotation(direction);
			}
			backward=false; 
		}
		public void update(){
			for(int i=0; i<70 ;i++){
				for(int a=0; a<30; a++ ){
					batch.draw(target,Math.round(x+(int)(i*Math.cos(direction))), mask.getHeight()- Math.round(y+(int)(a*Math.sin(direction))));
				}
			}
			for(int i = 0; i < Jan.spList.size(); i++){
				Jan.spList.get(i).setPosition(Jan.getX(),Jan.getY());
				Jan.spList.get(i).setRotation(Jan.direction);
			}
			for(NPC a:people){
				if(a.hitbox.overlaps(hitbox) && mode=="car" && Fspeed>2){
					a.health=0;
					hcar+=1;
				}
			}
			if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				if(mode == "foot" && Jan.hitbox.overlaps(hitbox)){
					mode = "car";
					scar+=1; 
				}
				else if(mode == "car"){
					mode = "foot";
				}
			}
			if(mode=="car"){
				if(running == true){
					turn();
					direction();
				}
				if(running == false){
					car.draw(batch); 
				}
			}
			else{
				car.draw(batch);
			}
			
		}
	}
	class SpritePos{
		static final int Punch = 5;
		static final int Walk = 0;
		static final int Die = 8;
		static final int pReset = 49;
		static final int wReset = 29;
		static final int dReset = 9;
	}
	class Body{
		float x;
		float y;
		Texture pic;
		public Body(float nx, float ny, Texture p){
			x =nx;
			y = ny;
			pic = p;
		}
	}
	class Reload{
		int x;
		int y;
		int time;
		boolean swi;
		Rectangle box;
		public Reload(int rx, int ry, int t, boolean s){
			x=rx;
			y=ry;
			time=t;
			swi =s;
			box= new Rectangle(x,y,50,50);
		}
		public void drawPoints(){
			if(swi==true){
				batch.draw(gun,x,y);
			}
		}
		public void getAmmo(){
			if(swi ==true && Jan.hitbox.overlaps(box) && Gdx.input.isKeyJustPressed(Keys.ENTER) &&Jan.wlist.get(Jan.equipped).ammo != 100 ){
				swi=false;
				Jan.wlist.get(Jan.equipped).ammo = 100;
				time=0;
			}
		}
		public void timmer(){
			if(swi==false){
				time+=1;
			}
			if(time==1000){
				swi=true;
			}
		}
		public void update(){
			drawPoints();
			getAmmo();
			timmer();
		}
	}
	class Vector{
		double x, y;
		int direction;
		public Vector(double nx, double ny, int dir){
			x = nx;
			y = ny;
			direction = dir;
		}
	}
	class PoPoWeapon{
		int range;
		int tph;   //time per hit
		int hitCost, ammo;
		int damage;
		boolean canShoot = true;
		int timer = 0;
		String name;
		int shots, accuracy;
		Texture pic;
		public PoPoWeapon(String p, int h, int hc, int d, int a, String n, int s, int ac, int l){
			tph = h;
			hitCost = hc;
			damage = d;
			ammo = a;
			name = n;
			shots = s;
			accuracy = ac;
			range = l;
			pic = new Texture(p+".png");
		}
		public void shoot(float x, float y, int direction){
			if(timer >= tph && ammo > 0){
				ammo -= hitCost;
				timer = 0;
				
				int variance;
				for(int i = 0; i <= shots; i++){
					variance = (int)(Math.random()*accuracy - accuracy/2); 
					Bullet tmp = new Bullet(x+10, y+10, direction + variance, pic, damage, range);
				}
			}
		}
		public void update(){
			timer += 1;
		}
	}
	class Cop{
		boolean inrange = false;
		int turntimer = 0;
		int pshift = SpritePos.Walk;
		boolean canHit = true;;
		PoPoWeapon gun = new PoPoWeapon("bullet", 25, 2, 10, 100,"glock", 1, 0, 100);
		int health;
		int direction;
		double moveSpeed = 1;
		Rectangle hitbox;
		int counter = 0;
		ArrayList<Sprite>spList;
		int money;
		int timer;
		int hittimer;
		public Cop(int px, int py, int n, ArrayList<Sprite>sl, int di, int t){ //constructor 
			health = n;
			hitbox = new Rectangle(px, py, 30, 30);
			spList = sl;
			direction = di;
			timer = t;
		}
		public boolean nclear(float x, float y){
			boolean hit = true;
			int c=0;
			if(x<0 || x>= mask.getWidth() || y<0 || y>= mask.getHeight()){
				return false;
			}
			for(int i=0; i<20;i++){
				for(int a=0; a<20; a++ ){
					c = mask.getPixel(Math.round(x+i), mask.getHeight()- Math.round(y+a));
					if(c==wall ){
						hit = false;
					}
				}
			}
			if(water == c){
				respawn();
			}
			return hit ;
		}
		public boolean path(float x, float y){
			boolean hit = false;
			int c=0;
			c = mask.getPixel(Math.round(x), mask.getHeight()- Math.round(y));
			if(c==turn){
				hit = true;
			}
			return hit;
		}
		public void respawn(){
			health = 100;
			
			moveSpeed = 2;
			Vector a = spawnList.get(randint(0, spawnList.size()-1));
			while((a.x < Jan.getX()+512 && a.x > Jan.getX()-512) || (a.y < Jan.getY()+288 && a.y > Jan.getY()-288)){
				a = spawnList.get(randint(0, spawnList.size()-1));
			}
			hitbox.x = (float)a.x;
			hitbox.y = (float)a.y;
			direction = a.direction;
			counter = 0;
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX(),getY());
				spList.get(i).setRotation(direction);
			}
			timer = 0;
		}
		public void turnTime(){
			for(Cop a:police){
				a.timer++;
			}
		}
		public void die(){
			health = 100;
			bodyList.add(new Body(getX(), getY(), new Texture("dead.png")));
			Jan.wantedLevel += 20;
			Jan.wantedCounter = -200;
			respawn();
		}
		public void getShot(){
			Bullet tmp = null;
			for(Bullet i : bulletList){
				if(hitbox.contains(i.x, i.y)){
					health -= i.damage;
					tmp = i;
				}
			}
			bulletList.remove(tmp);
		}
		public float getX(){ 
			return hitbox.getX();
		}
		public float getY(){ 
			return hitbox.getY();
		}
		public void move(){
			drawGuy();
			double newX = getX() + (moveSpeed*Math.cos(Math.toRadians(direction)));
			double newY = getY() + (moveSpeed*Math.sin(Math.toRadians(direction)));
			if(nclear((float)newX,(float)newY) == true && (path((float)newX+10,(float)newY+10) && running == true)){
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
			else if(nclear((float)newX,getY()) == true&& running == true){
				hitbox.x += (moveSpeed*Math.cos(Math.toRadians(direction)));
			}
			else if(nclear(getX(),(float)newY) == true && running == true){
				hitbox.y += (moveSpeed*Math.sin(Math.toRadians(direction)));
			}
		}
		public void drawGuy(){
			int pos = counter/10;
			counter += 1;
			counter = counter % 49;
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX(),getY());
				spList.get(i).setRotation(direction);
			}
			spList.get(pos).draw(batch);
		}
		public void drawGuy(int cstep, int pshift, int posStep){
			int pos = counter/posStep + pshift;
			if (running == true){
				counter += 1;
				counter = counter % cstep;
			}
			for(int i = 0; i < spList.size(); i++){
				spList.get(i).setPosition(getX()-10,getY()-2);
				spList.get(i).setRotation(direction);
			}
			spList.get(pos).draw(batch);
		}

		public void update(){
			turntimer += 1;
			if(health <= 0){
				die();
			}
			if(Jan.flash%30 <= 5){
				for(int i = 0; i < (int)health/8; i++){
					batch.draw(hp, hitbox.x+2+i, hitbox.y + 30);
				}
				for(int i = 0; i < (int)health/8; i++){
					batch.draw(chp, hitbox.x+14+i, hitbox.y + 30);
				}
			}
			else{
				for(int i = 0; i < (int)health/8; i++){
					batch.draw(chp, hitbox.x+2+i, hitbox.y + 30);
				}
				for(int i = 0; i < (int)health/8; i++){
					batch.draw(hp, hitbox.x+14+i, hitbox.y + 30);
				}
			}
			direction = (int)Math.toDegrees(Math.atan2( Jan.getY()-getY(),Jan.getX()-getX()));
			if(hitbox.overlaps(Jan.hitbox)){
				if(Jan.health >0){
					if(hittimer == 30){
						Jan.regen = -100;
						if(mode=="foot"){
							Jan.health -= 20;
						}
					}
					hittimer += 1;
					if(hittimer == 40){
						hittimer = 0;
					}
					drawGuy(39,5, 15);  //cStep, pShift, posStep
				}
			}
			else{
				move();
			}
			getShot();
			
		}
	}
}

