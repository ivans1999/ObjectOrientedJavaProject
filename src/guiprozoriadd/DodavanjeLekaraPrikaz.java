package guiprozoriadd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import funk.ZdrSistem;
import net.miginfocom.swing.MigLayout;
import uloge.Lekar;

public class DodavanjeLekaraPrikaz extends JFrame {
	
	private String uloga= new String("Lekar"); 
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JTextField txtPol = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Broj telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblKorisnicko = new JLabel("Korisnicko");
	private JTextField txtKorisnicko = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JTextField txtLozinka = new JTextField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JTextField txtSpecijalizacija = new JTextField(20);
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JTextField txtSluzba = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	
	private ZdrSistem sistem;
	private Lekar lekar;

	public DodavanjeLekaraPrikaz(ZdrSistem sistem,Lekar lekar){
		this.sistem=sistem;
		this.lekar=lekar;
		String jmbg = lekar == null ? "Dodavanje lekara"
				: "Izmena podataka o lekaru: " + lekar.getJmbg();
		setTitle(jmbg);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (lekar != null) {
			popuniPolja();
		}
		initListeners();
		pack();
	}
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblPol);
		add(txtPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrTelefona);
		add(txtBrTelefona);
		add(lblKorisnicko);
		add(txtKorisnicko);
		add(lblLozinka);
		add(txtLozinka);
		add(lblPlata);
		add(txtPlata);
		add(lblSpecijalizacija);
		add(txtSpecijalizacija);
		add(lblSluzba);
		add(txtSluzba);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(lekar.getIme());
		txtPrezime.setText(lekar.getPrezime());
		txtJMBG.setText(lekar.getJmbg());
		txtPol.setText(lekar.getPol());
		txtAdresa.setText(lekar.getAdresa());
		txtBrTelefona.setText(lekar.getBrTelefona());
		txtKorisnicko.setText(lekar.getKorisnicko());
		txtLozinka.setText(lekar.getLozinka());
		lekar.setUloga(uloga);
		txtPlata.setText(String.valueOf(lekar.getPlata()));
		txtSpecijalizacija.setText(lekar.getSpecijalizacija());
		txtSluzba.setText(lekar.getSluzba());
	}
	private void initListeners() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = txtIme.getText().trim();
				String prezime=txtPrezime.getText().trim();
				String JMBG=txtJMBG.getText().trim();
				String pol =txtPol.getText().trim();
				String adresa=txtAdresa.getText().trim();
				String brTelefona=txtBrTelefona.getText().trim();
				String korisnicko=txtKorisnicko.getText().trim();
				String lozinka=txtLozinka.getText().trim();
				double plata=Double.parseDouble(txtPlata.getText().trim());
				String specijalizacija=txtSpecijalizacija.getText().trim();
				String sluzba=txtSluzba.getText().trim();
				if(lekar == null) {
					Lekar lekar=new Lekar(ime,prezime,JMBG,pol,adresa,brTelefona,korisnicko,lozinka,uloga,plata,specijalizacija,sluzba);
					sistem.dodajLekara(lekar);
				}else {
					lekar.setIme(ime);
					lekar.setPrezime(prezime);
					lekar.setJmbg(JMBG);
					lekar.setPol(pol);
					lekar.setAdresa(adresa);
					lekar.setBrTelefona(brTelefona);
					lekar.setKorisnicko(korisnicko);
					lekar.setLozinka(lozinka);
					lekar.setUloga(uloga);
					lekar.setPlata(plata);
					lekar.setSpecijalizacija(specijalizacija);
					lekar.setSluzba(sluzba);
				}
				sistem.snimiLekara("lekari.txt");
				JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje",
						JOptionPane.DEFAULT_OPTION);
				DodavanjeLekaraPrikaz.this.dispose();
				DodavanjeLekaraPrikaz.this.setVisible(false);
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeLekaraPrikaz.this.dispose();
				DodavanjeLekaraPrikaz.this.setVisible(false);
			}
		});
	}
}