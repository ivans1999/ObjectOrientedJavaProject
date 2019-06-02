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
import uloge.Medicinskasestra;

public class DodavanjeSestaraPrikaz extends JFrame {
	
	private String uloga= new String("Sestra"); 
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
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JTextField txtSluzba = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	
	private ZdrSistem sistem;
	private Medicinskasestra sestra;

	public DodavanjeSestaraPrikaz(ZdrSistem sistem,Medicinskasestra sestra){
		this.sistem=sistem;
		this.sestra=sestra;
		String jmbg = sestra == null ? "Dodavanje sestre"
				: "Izmena podataka o sestri: " + sestra.getJmbg();
		setTitle(jmbg);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (sestra != null) {
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
		add(lblSluzba);
		add(txtSluzba);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(sestra.getIme());
		txtPrezime.setText(sestra.getPrezime());
		txtJMBG.setText(sestra.getJmbg());
		txtPol.setText(sestra.getPol());
		txtAdresa.setText(sestra.getAdresa());
		txtBrTelefona.setText(sestra.getBrTelefona());
		txtKorisnicko.setText(sestra.getKorisnicko());
		txtLozinka.setText(sestra.getLozinka());
		sestra.setUloga(uloga);
		txtPlata.setText(String.valueOf(sestra.getPlata()));
		txtSluzba.setText(sestra.getSluzba());
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
				String sluzba=txtSluzba.getText().trim();
				if(sestra == null) {
					Medicinskasestra sestra=new Medicinskasestra(ime,prezime,JMBG,pol,adresa,brTelefona,korisnicko,lozinka,uloga,plata,sluzba);
					sistem.dodajSestru(sestra);
				}else {
					sestra.setIme(ime);
					sestra.setPrezime(prezime);
					sestra.setJmbg(JMBG);
					sestra.setPol(pol);
					sestra.setAdresa(adresa);
					sestra.setBrTelefona(brTelefona);
					sestra.setKorisnicko(korisnicko);
					sestra.setLozinka(lozinka);
					sestra.setUloga(uloga);
					sestra.setPlata(plata);
					sestra.setSluzba(sluzba);
				}
				sistem.snimiSestru("medicinskeSestre.txt");
				JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje",
						JOptionPane.DEFAULT_OPTION);
				DodavanjeSestaraPrikaz.this.dispose();
				DodavanjeSestaraPrikaz.this.setVisible(false);
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeSestaraPrikaz.this.dispose();
				DodavanjeSestaraPrikaz.this.setVisible(false);
			}
		});
	}

}