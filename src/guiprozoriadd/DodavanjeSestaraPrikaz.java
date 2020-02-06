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
import guiprozoriadd.DodavanjeSestaraPrikaz;
import net.miginfocom.swing.MigLayout;
import uloge.Pol;
import uloge.SluzbeZaSestru;
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
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JComboBox<SluzbeZaSestru> cbSluzba = new JComboBox<SluzbeZaSestru>(SluzbeZaSestru.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	
	private ZdrSistem sistem;
	private Medicinskasestra sestra;

	public DodavanjeSestaraPrikaz(ZdrSistem sistem,Medicinskasestra sestra,int identifikator){
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
		add(lblSluzba);
		add(cbSluzba);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(sestra.getIme());
		txtPrezime.setText(sestra.getPrezime());
		txtJMBG.setText(sestra.getJmbg());
		cbPol.setSelectedItem(this.sestra.getPol());
		txtAdresa.setText(sestra.getAdresa());
		txtBrTelefona.setText(sestra.getBrTelefona());
		txtKorisnicko.setText(sestra.getKorisnicko());
		pfLozinka.setText(sestra.getLozinka());
		sestra.setUloga(uloga);
		txtPlata.setText(String.valueOf(sestra.getPlata()));
		cbSluzba.setSelectedItem(this.sestra.getSluzba());
	}
	private void initListeners(int identifikator) {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija(identifikator)==true){
					String ime = txtIme.getText().trim();
					String prezime=txtPrezime.getText().trim();
					String JMBG=txtJMBG.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa=txtAdresa.getText().trim();
					String brTelefona=txtBrTelefona.getText().trim();
					String korisnicko=txtKorisnicko.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					double plata=Double.parseDouble(txtPlata.getText().trim());
					SluzbeZaSestru sluzba=(SluzbeZaSestru) cbSluzba.getSelectedItem();
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
			if(sistem.pronadjiSestru(txtJMBG.getText().trim())!=null){
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
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}