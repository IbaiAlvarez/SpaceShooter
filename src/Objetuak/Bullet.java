package Objetuak;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import APP.Pantaila;
import APP.Sarrera;

public class Bullet extends Object{	

	private Pantaila p;
	private Sarrera s;
	private double indarra;
	
	public Bullet(Pantaila p,Sarrera s) {
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
	public double getIndarra() {
		return indarra;
	}
	//Setters
	public void setP(Pantaila p) {
		this.p = p;
	}
	public void setS(Sarrera s) {
		this.s = s;
	}
	public void setIndarra(double indarra) {
		this.indarra = indarra;
	}
	
	public void sortu(Ship espaziontzi) {
		super.setX(espaziontzi.getX()+espaziontzi.getZabalera());
		super.setY(espaziontzi.getY()+12);		
		super.setA(2);
		
		try { 
			super.setIrudi(ImageIO.read(new FileInputStream("C:/Users/Eclipse/Jokua/bala.png"))); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		super.setAltuera(super.getIrudi().getHeight());
		super.setZabalera(super.getIrudi().getWidth());
	}
	
}
