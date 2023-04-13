package Objetuak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import APP.Pantaila;
import APP.Sarrera;

public class Ship extends Object{
	private Pantaila p;
	private Sarrera s;
	private double cooldown;
	private double dmg;
	
	public Ship(Pantaila p,Sarrera s){
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
	public double getDmg() {
		return dmg;
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
	public void setDmg(double dmg) {
		this.dmg = dmg;
	}
	
	//Creates a Ship
	public void sortu() {
		super.setX(100);
		super.setY(100);		
		super.setA(1);
		this.setDmg(1);
		
		try { 
			super.setIrudi(ImageIO.read(new FileInputStream("src/res/ship.png"))); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		super.setAltuera(super.getIrudi().getHeight()*3);
		super.setZabalera(super.getIrudi().getWidth()*3);
	}
}
