package Objetuak;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import APP.Pantaila;
import APP.Sarrera;

public class Enemy extends Object{
	private Pantaila p;
	private Sarrera s;
	private double cooldown;
	private double hp;
	
	public Enemy(Pantaila p,Sarrera s){
		super();
		this.p = p;
		this.s = s;
	}

	//Getters
	public Pantaila getP() {
		return p;
	}
	public Sarrera getS() {
		return s;
	}
	public double getCooldown() {
		return cooldown;
	}
	public double getHp() {
		return hp;
	}

	//Setters
	public void setP(Pantaila p) {
		this.p = p;
	}
	public void setS(Sarrera s) {
		this.s = s;
	}
	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}

	public void sortu() {
		super.setX(540);
		super.setY(ThreadLocalRandom.current().nextInt(0, 335 + 1));		
		super.setA(0.75);
		this.setHp(1);
		try { 
			super.setIrudi(ImageIO.read(new FileInputStream("C:/Users/in1dm3/Desktop/space/SpaceShooter/src/res/enemigo.png"))); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		super.setAltuera(super.getIrudi().getHeight()*3);
		super.setZabalera(super.getIrudi().getWidth()*3);
	}
}
