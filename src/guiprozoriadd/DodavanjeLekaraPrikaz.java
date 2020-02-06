package guiprozoriadd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import funk.ZdrSistem;
import guiprozoriadd.DodavanjeLekaraPrikaz;
import net.miginfocom.swing.MigLayout;
import uloge.Pol;
import uloge.SluzbeZaLekara;
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
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Broj telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblKorisnicko = new JLabel("Korisnicko");
	private JTextField txtKorisnicko = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JTextField txtSpecijalizacija = new JTextField(20);
	private JLabel lblSluzba = new JLabel("Sluzbe za lekara");
	private JComboBox<SluzbeZaLekara> cbSluzba = new JComboBox<SluzbeZaLekara>(SluzbeZaLekara.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	
	private ZdrSistem sistem;
	private Lekar lekar;

	public DodavanjeLekaraPrikaz(ZdrSistem sistem,Lekar lekar,int identifikator){
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
			popuniPolja(identifikator);
		}
		initListeners(identifikator);
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
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrTelefona);
		add(txtBrTelefona);
		add(lblKorisnicko);
		add(txtKorisnicko);
		add(lblLozinka);
		add(pfLozinka);
		add(lblPlata);
		add(txtPlata);
		add(lblSpecijalizacija);
		add(txtSpecijalizacija);
		add(lblSluzba);
		add(cbSluzba);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}

	private void popuniPolja(int identifikator) {
		if (identifikator == 2) {
			txtIme.setText(lekar.getIme());
			txtPrezime.setText(lekar.getPrezime());
			txtJMBG.setText(lekar.getJmbg());
			cbPol.setSelectedItem(this.lekar.getPol());
			txtAdresa.setText(lekar.getAdresa());
			txtBrTelefona.setText(lekar.getBrTelefona());
			txtKorisnicko.setText(lekar.getKorisnicko());
			pfLozinka.setText(lekar.getLozinka());
			lekar.setUloga(uloga);
			txtPlata.setText(String.valueOf(lekar.getPlata()));
			txtSpecijalizacija.setText(lekar.getSpecijalizacija());
			cbSluzba.setSelectedItem(this.lekar.getSluzba());
		}
		if (identifikator == 1) {
			txtIme.setEnabled(false);
			txtIme.setText(lekar.getIme());
			txtPrezime.setEnabled(false);
			txtPrezime.setText(lekar.getPrezime());
			txtJMBG.setEnabled(false);
			txtJMBG.setText(lekar.getJmbg());
			cbPol.setEnabled(false);
			cbPol.setSelectedItem(this.lekar.getPol());
			txtAdresa.setEnabled(false);
			txtAdresa.setText(lekar.getAdresa());
			txtBrTelefona.setEnabled(false);
			txtBrTelefona.setText(lekar.getBrTelefona());
			txtKorisnicko.setEnabled(false);
			txtKorisnicko.setText(lekar.getKorisnicko());
			pfLozinka.setEnabled(false);
			pfLozinka.setText(lekar.getLozinka());
			lekar.setUloga(uloga);
			txtPlata.setEnabled(false);
			txtPlata.setText(String.valueOf(lekar.getPlata()));
			txtSpecijalizacija.setEnabled(false);
			txtSpecijalizacija.setText(lekar.getSpecijalizacija());
			cbSluzba.setEnabled(false);
			cbSluzba.setSelectedItem(this.lekar.getSluzba());
			btnOK.setVisible(false);
			btnOtkazi.setVisible(false);
		}

	}
	private void initListeners(int identifikator) {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija(identifikator) == true){
					String ime = txtIme.getText().trim();
					String prezime=txtPrezime.getText().trim();
					String JMBG=txtJMBG.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa=txtAdresa.getText().trim();
					String brTelefona=txtBrTelefona.getText().trim();
					String korisnicko=txtKorisnicko.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					double plata=Double.parseDouble(txtPlata.getText().trim());
					String specijalizacija=txtSpecijalizacija.getText().trim();
					SluzbeZaLekara sluzba = (SluzbeZaLekara) cbSluzba.getSelectedItem();
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
	
	private boolean validacija(int identifikator) {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		if(txtJMBG.getText().trim().equals("")) {
			poruka += "- Unesite JMBG\n";
			ok = false;
		}
		if(identifikator==0) {
			if(sistem.pronadjiLekara(txtJMBG.getText().trim())!=null){
				poruka += "- Ovaj JMBG vec postoji\n";
				ok = false;
			}
		}
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		if(txtBrTelefona.getText().trim().equals("")) {
			poruka += "- Unesite broj telefona\n";
			ok = false;
		}
		if(txtKorisnicko.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		if(identifikator==0) {
			if(sistem.pronadjiKorisnicko(txtKorisnicko.getText().trim()) != null) {
				poruka += "- Ovo korisnicko ime vec postoji\n";
				ok=false;
			}
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtPlata.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		if(txtSpecijalizacija.getText().trim().equals("")) {
			poruka += "- Unesite Specijalizaciju\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}