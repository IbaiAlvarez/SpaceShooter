package Objetuak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import APP.Pantaila;
import APP.Sarrera;

public class Espaziontzi extends Objetua{
	private Pantaila p;
	private Sarrera s;
	private double cooldown;
	
	public Espaziontzi(Pantaila p,Sarrera s){
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
	
	public void sortu() {
		super.setX(100);
		super.setY(100);		
		super.setA(1);
		
		try { 
			super.setIrudi(ImageIO.read(new FileInputStream("C:/Users/Eclipse/Jokua/espaziontzi.png"))); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		super.setAltuera(super.getIrudi().getHeight()*3);
		super.setZabalera(super.getIrudi().getWidth()*3);
	}
}
