package Objetuak;

import java.awt.image.BufferedImage;

public class Object {
	private double x;
	private double y;
	private double a;
	private double zabalera;
	private double altuera;
	private BufferedImage irudi;
	
	
	
	public Object(double x, double y, double a, double zabalera, double altuera, BufferedImage irudi) {
		super();
		this.x = x;
		this.y = y;
		this.a = a;
		this.zabalera = zabalera;
		this.altuera = altuera;
		this.irudi = irudi;
	}

	public Object() {
		super();
		this.x=100;
		this.y=100;
		this.a=1;
	}

	//Getters
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getA() {
		return a;
	}
	public double getZabalera() {
		return zabalera;
	}
	public double getAltuera() {
		return altuera;
	}
	public BufferedImage getIrudi() {
		return irudi;
	}
	//Setters
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double d) {
		this.y = d;
	}
	public void setA(double a) {
		this.a = a;
	}
	public void setZabalera(double zabalera) {
		this.zabalera = zabalera;
	}
	public void setAltuera(double altuera) {
		this.altuera = altuera;
	}
	public void setIrudi(BufferedImage irudi) {
		this.irudi = irudi;
	}
}
