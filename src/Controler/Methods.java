package Controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import APP.Pantaila;
import APP.Sarrera;
import Objetuak.Bullet;
import Objetuak.Enemy;
import Objetuak.Ship;

public class Methods {

	public static String[][] loadLVLs(){
		String[][] lvls = new String[0][8];
		
		File file = new File("src/res/lvls.txt");
		BufferedReader fichero;
		try {
			fichero = new BufferedReader(new FileReader(file));
		String linea;
		
		while((linea = fichero.readLine())!=null)
		{
				String[] data = linea.split("#");				
				
				String[][] lvls_prov = new String[lvls.length+1][8];
				for(int i =0;i<lvls.length;i++){
					for(int j=0;j<lvls[i].length;j++) {
						lvls_prov[i][j]=lvls[i][j];
					}
				}
				lvls_prov[lvls.length][0] = data[0];
				lvls_prov[lvls.length][1] = data[1];
				lvls_prov[lvls.length][2] = data[2];
				lvls_prov[lvls.length][3] = data[3];
				lvls_prov[lvls.length][4] = data[4];
				lvls_prov[lvls.length][5] = data[5];
				lvls_prov[lvls.length][6] = data[6];
				lvls_prov[lvls.length][7] = data[7];
				lvls = lvls_prov;			
		}
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return lvls;
	}

	//Bulelt methods

	public ArrayList<Bullet> tiroEgin(Ship espaziontzi, ArrayList<Bullet> balak,Pantaila panel, Sarrera sarrera) {
		if(espaziontzi.getCooldown()<=0) {
			Bullet bala = new Bullet(panel,sarrera);
			espaziontzi.setCooldown(120);
			bala.sortu(espaziontzi);
			balak.add(bala);
		}
		return balak;
	}
	
	public ArrayList<Bullet> removeBala(ArrayList<Bullet> balak) {
		for(int i=0;i<balak.size();i++) {
			if(balak.get(i).getX()>=527+(balak.get(i).getZabalera()*2)) {
				balak.remove(i);
			}
		}
		return balak;
	}
	
	//Enemy methods	

	/*public int createEnemy(int enemyCooldown, int enemiesToClear,ArrayList<Enemy> enemies,Pantaila panel, Sarrera sarrera, String[][] lvls, int actual_lvl) {
		if(enemyCooldown==0 && enemiesToClear>0) {
			Enemy enemy = new Enemy(panel,sarrera);
			enemyCooldown = 300;
			enemy.sortu(Integer.parseInt(lvls[actual_lvl][2]),Double.valueOf(lvls[actual_lvl][3]),lvls[actual_lvl][4]);
			enemies.add(enemy);
		}
		return enemyCooldown;
	}*/
	
	public boolean removeEnemy(ArrayList<Enemy> enemies) {
		boolean lose = false;
		for(int i=0;i<enemies.size();i++) {
			if(enemies.get(i).getX()<=0-(enemies.get(i).getZabalera()*2)) {
				lose=true;
			}
		}
		return lose;
	}
}