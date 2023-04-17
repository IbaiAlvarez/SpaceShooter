package APP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controler.Methods;
import Objetuak.Bullet;
import Objetuak.Enemy;
import Objetuak.Ship;
import Objetuak.Object;

public class Pantaila extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int spriteTamainaOriginala = 16;
	final int eskala = 3;
	public final int spriteTamaina =spriteTamainaOriginala * eskala;
	final int pantailaKarratuakAltuera = 12;
	final int pantailaKarratuakZabalera = 8;
	final int pantailaAltuera =spriteTamaina * pantailaKarratuakAltuera;
	final int pantailaZabalera =spriteTamaina * pantailaKarratuakZabalera;
	

	int FPS =20;
	Sarrera sarrera = new Sarrera();
	Object obj = new Object();
	Ship espaziontzi = new Ship(this, sarrera);
	Thread play;
	Bullet bala;
	Enemy enemy;
	//Create Objects Arrays
	ArrayList<Bullet> balak = new ArrayList<>();
	ArrayList<Enemy> enemies = new ArrayList<>();
	//LvLs config array
	String[][] lvls = Methods.loadLVLs();
	int actual_lvl = 0;
	int enemyCooldown = 0;
	int enemiesToClear = Integer.parseInt(lvls[actual_lvl][1]);
	int score = 0;
	int enemyCount=0;
	boolean lose = false;
	boolean stop = false;
	Methods methods = new Methods();

	public Pantaila(){
		this.setPreferredSize(new Dimension(pantailaAltuera, pantailaZabalera));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(sarrera);
		this.setFocusable(true);
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics) g;		
		//Ship Repaint
		g2.drawImage(espaziontzi.getIrudi(),(int) Math.round(espaziontzi.getX()),(int) Math.round(espaziontzi.getY()), (int) Math.round(espaziontzi.getAltuera()), (int) Math.round(espaziontzi.getZabalera()), null);
		//Bullet Repaint
		for(int i=0;i<balak.size();i++) {
			g2.drawImage(balak.get(i).getIrudi(),(int) Math.round(balak.get(i).getX()),(int) Math.round(balak.get(i).getY()), (int) Math.round(balak.get(i).getAltuera()), (int) Math.round(balak.get(i).getZabalera()), null);
		}
		//Enemy Repaint
		for(int i=0;i<enemies.size();i++) {
			g2.drawImage(enemies.get(i).getIrudi(),(int) Math.round(enemies.get(i).getX()),(int) Math.round(enemies.get(i).getY()), (int) Math.round(enemies.get(i).getAltuera()), (int) Math.round(enemies.get(i).getZabalera()), null);
		}
		g2.dispose();
	}
	
	
	public void asiJolasten() {
		play = new Thread(this);
		play.start();
	}
	
	
	@Override
	public void run() {
		espaziontzi.sortu();
		while(play != null) {
			play();
		}
	}
	
	public void play() {
		Double drawInterval = (double) (100000000/FPS);
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (!stop) 
		{		
			//1) Mugimentua kalkulatu
			kalkulatu();
			//2) Margotu
			repaint();
			try
			{
				double remainingTime =  nextDrawTime - System.nanoTime();
				remainingTime = remainingTime /10000000;
				if (remainingTime < 0) remainingTime = 0;
				Thread.sleep( (long) remainingTime);
				nextDrawTime = nextDrawTime + drawInterval;
			}
			/*
			try {
				Thread.sleep( 5 );
			} */
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String[] options = {lvls[actual_lvl][6],lvls[actual_lvl][7]};
		sarrera.resetValues();
		int selection = -2;
		selection = JOptionPane.showOptionDialog(null, "Choose Upgrade!","Upgrade", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
		if(selection == 0){
			espaziontzi.setCooldown(espaziontzi.getCooldown()/2);
			nextLvl();
		}else if(selection == 1){			
			espaziontzi.setDmg(espaziontzi.getDmg()*2);
			nextLvl();
		}else if(selection == -1) {
			System.exit(0);
		}	
	}
	
	public void kalkulatu() {
		//Gora eta Ezkerra
		if(sarrera.gora && sarrera.ezker && espaziontzi.getY()>0 && espaziontzi.getX()>0) {
			espaziontzi.setY(espaziontzi.getY()-(Math.sin(45)*espaziontzi.getA()));
			espaziontzi.setX(espaziontzi.getX()-(Math.cos(45)*espaziontzi.getA()));
		}
		//Gora eta Eskuma
		else if(sarrera.gora && sarrera.eskubi && espaziontzi.getY()>0 && espaziontzi.getX()<527) {
			espaziontzi.setY(espaziontzi.getY()-(Math.sin(45)*espaziontzi.getA()));
			espaziontzi.setX(espaziontzi.getX()+(Math.cos(45)*espaziontzi.getA()));
		}
		//Behera etz Ezkerra
		else if(sarrera.behera && sarrera.ezker && espaziontzi.getY()<349 && espaziontzi.getX()>0) {
			espaziontzi.setY(espaziontzi.getY()+(Math.sin(45)*espaziontzi.getA()));
			espaziontzi.setX(espaziontzi.getX()-(Math.cos(45)*espaziontzi.getA()));
		}
		//Behera eta Eskuma
		else if(sarrera.behera && sarrera.eskubi && espaziontzi.getY()<349 && espaziontzi.getX()<527) {
			espaziontzi.setY(espaziontzi.getY()+(Math.sin(45)*espaziontzi.getA()));
			espaziontzi.setX(espaziontzi.getX()+(Math.cos(45)*espaziontzi.getA()));
		}
		else if (sarrera.gora && espaziontzi.getY()>0) {
			espaziontzi.setY(espaziontzi.getY()-espaziontzi.getA());
		}
		else if (sarrera.behera && espaziontzi.getY()<349) {
			espaziontzi.setY(espaziontzi.getY()+espaziontzi.getA());
		}
		else if (sarrera.ezker && espaziontzi.getX()>0) {
			espaziontzi.setX(espaziontzi.getX()-espaziontzi.getA());
		}
		else if (sarrera.eskubi && espaziontzi.getX()<527) {
			espaziontzi.setX(espaziontzi.getX()+espaziontzi.getA());
		}
		
		//Shoots bullet
		if(sarrera.tiro) {
			methods.tiroEgin(espaziontzi,balak,this,sarrera);
		}
		
		//Calculates Bullet Collision
		calculateBulletCollision();
		//Calculates Ship Collision with Enemies
		calculateShipCollision();
		
		if(enemiesToClear==0) {
			stop=true;						
		}else if(!stop && enemiesToClear!=0) {
			createEnemy();
		}	
		//Bullet CoolDown
		if(espaziontzi.getCooldown()>0) {
			espaziontzi.setCooldown(espaziontzi.getCooldown()-1);
		}
		
		//Enemy CoolDown
		if(enemyCooldown>0 && !stop) {
			enemyCooldown-=1;
		}
		
		if(!stop) {
			//Bullet movement
			for(int i=0;i<balak.size();i++) {
				balak.get(i).setX(balak.get(i).getX()+balak.get(i).getA());
			}
			
			//Enemy Movement
			for(int i=0;i<enemies.size();i++) {
				enemies.get(i).setX(enemies.get(i).getX()-enemies.get(i).getA());
			}
			methods.removeBala(balak);
			if (methods.removeEnemy(enemies)) {
				play=null;
			}
		}
	}		
	
	//Calculates if the bullet hit the enemy
	public void calculateBulletCollision() {
		//Remove Objects Arrays
		ArrayList<Bullet> balakRemove = new ArrayList<>();
		ArrayList<Enemy> enemiesRemove = new ArrayList<>();
		
		for(int i=0;i<balak.size();i++) {
			for(int j=0;j<enemies.size();j++) {
				if(balak.get(i).getX() < enemies.get(j).getX() + enemies.get(j).getZabalera() && balak.get(i).getX() + balak.get(i).getZabalera() > enemies.get(j).getX() && balak.get(i).getY() < enemies.get(j).getY() + enemies.get(j).getAltuera() && balak.get(i).getAltuera() + balak.get(i).getY() > enemies.get(j).getY()) {
					balakRemove.add(balak.get(i));
					enemies.get(j).setHp(enemies.get(j).getHp()-espaziontzi.getDmg());
					if(enemies.get(i).getHp()<=0) {
						enemiesRemove.add(enemies.get(j));
						enemiesToClear--;				
					}
				}
			}
		}
		//Removes bullets from array
		for(int i=0;i<balakRemove.size();i++) {
			balak.remove(balakRemove.get(i));
		}
		//Removes enemies from array
		for(int i=0;i<enemiesRemove.size();i++) {
			enemies.remove(enemiesRemove.get(i));
			score+= Integer.parseInt(lvls[actual_lvl][5]);			
		}
	}
	
	public void nextLvl() {
		enemyCooldown=300;
		actual_lvl++;
		enemyCount=0;
		enemiesToClear=Integer.parseInt(lvls[actual_lvl][1]);
		stop=false;
		
	}
	
	public void calculateShipCollision() {		
			for(int j=0;j<enemies.size();j++) {
				if(espaziontzi.getX() < enemies.get(j).getX() + (enemies.get(j).getZabalera()/2) && espaziontzi.getX() + espaziontzi.getZabalera()*0.8 > enemies.get(j).getX() && espaziontzi.getY() < enemies.get(j).getY() + (enemies.get(j).getAltuera()/2) && espaziontzi.getAltuera() + (espaziontzi.getY()*0.8) > enemies.get(j).getY()) {
					play=null;
				}
			}	
	}
	
	public void createEnemy() {
		if(enemyCooldown==0 && enemiesToClear>0 && enemyCount<Integer.parseInt(lvls[actual_lvl][1]) && !stop) {
			Enemy enemy = new Enemy(this,sarrera);
			enemyCooldown = 300;
			enemy.sortu(Integer.parseInt(lvls[actual_lvl][2]),Double.valueOf(lvls[actual_lvl][3]),lvls[actual_lvl][4]);
			enemies.add(enemy);
			enemyCount++;
		}
	}
}
