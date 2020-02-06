package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;



import funk.ZdrSistem;
import guiprozoridisplay.PrikazLekara;
import guiprozoridisplay.PrikazPacijenata;
import guiprozoridisplay.PrikazPregleda;
import guiprozoridisplay.PrikazSestara;
import uloge.Osoba;

public class Prozorzasestru extends JFrame {
	
	private JButton btnLekar=new JButton("Operacije nad lekarima");
	private JButton btnSestra=new JButton("Operacije nad medicinskim sestrama");
	private JButton btnPacijent=new JButton("Operacije nad pacijentima");
	private JButton btnPregled=new JButton("Operacije nad pregledima");
	
	private ZdrSistem sistem;
	private Osoba osoba;
	
	public Prozorzasestru(ZdrSistem sistem,Osoba osoba) {
		this.sistem=sistem;
		this.osoba=osoba;
		setTitle("Korisnik: "+osoba.getKorisnicko());
		setSize(280, 160);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		iniTListeners();
	}
	
	public void initGUI() {		
		setLayout(new FlowLayout());
		add(btnLekar);
		add(btnSestra);
		add(btnPacijent);
		add(btnPregled);
	}
	
	public void iniTListeners() {
		btnLekar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazLekara pl=new PrikazLekara(sistem);
				pl.setVisible(true);
				
			}
		});
		btnSestra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazSestara ps=new PrikazSestara(sistem);
				ps.setVisible(true);
				
			}
		});
		btnPacijent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPacijenata pp=new PrikazPacijenata(sistem);
				pp.setVisible(true);
				
			}
		});
		btnPregled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrikazPregleda pp=new PrikazPregleda(sistem,"Sestra",osoba.getJmbg());
				pp.setVisible(true);
				
			}
		});
	}

}