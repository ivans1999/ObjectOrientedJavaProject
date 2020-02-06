package main;

import funk.ZdrSistem;
import gui.Login;


public class MojMain {

	private static String Lekari_fajl = "lekari.txt";
	private static String Pacijenti_fajl = "pacijenti.txt";
	private static String Sestre_fajl = "medicinskeSestre.txt";
	private static String Pregledi_fajl = "pregledi.txt";
	private static String Zdravstvene_fajl = "zdravstvena.txt";

	public static void main(String[] args) {
		ZdrSistem sistem = new ZdrSistem();
		sistem.ucitajLekare(Lekari_fajl);
		sistem.ucitajPacijenta(Pacijenti_fajl);
		sistem.ucitajSestru(Sestre_fajl);
		sistem.ucitajPreglede(Pregledi_fajl);
		sistem.ucitajKnjizicu(Zdravstvene_fajl);
		
		Login lProzor=new Login(sistem);
		lProzor.setVisible(true);
	
	}

}

