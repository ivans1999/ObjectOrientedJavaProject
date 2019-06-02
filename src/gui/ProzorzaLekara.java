package gui;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import funk.ZdrSistem;
import funk.ZdrSistem;
import uloge.Osoba;

public class ProzorzaLekara extends JFrame {
	
	private JButton btnPrikazPregleda=new JButton("Prikaz pregleda");
	private JButton btnOtkaziPregled=new JButton("Otkazi pregled");
	
	private ZdrSistem sistem;
	private Osoba osoba;
	
	public ProzorzaLekara(ZdrSistem sistem,Osoba osoba) {
		this.sistem=sistem;
		this.osoba=osoba;
		setTitle("Korisnik: "+osoba.getKorisnicko());
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		iniTListeners();
	}
	
	public void initGUI() {
		setLayout(new FlowLayout());
		add(btnPrikazPregleda);
		add(btnOtkaziPregled);
	}
	
	public void iniTListeners() {}

}
