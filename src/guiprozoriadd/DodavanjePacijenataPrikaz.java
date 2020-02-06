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
import guiprozoriadd.DodavanjeKnjizicePrikaz;
import guiprozoriadd.DodavanjeLekaraPrikaz;
import guiprozoriadd.DodavanjePacijenataPrikaz;
import net.miginfocom.swing.MigLayout;
import uloge.Pol;
import uloge.ZdravstvenaKnjizica;
import uloge.Lekar;
import uloge.Pacijent;

   public class DodavanjePacijenataPrikaz extends JFrame {

	private String uloga = new String("Pacijent");
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
	private JLabel lblIzabraniLekar = new JLabel("Izabrani lekar");
	private JComboBox<String> cbLekar = new JComboBox<String>();
	private JButton btnLekar=new JButton("Prikazi lekara");
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");

	private ZdrSistem sistem;
	private Pacijent pacijent;
			
	public DodavanjePacijenataPrikaz(ZdrSistem sistem, Pacijent pacijent,int identifikator) {
		this.sistem = sistem;
		this.pacijent = pacijent;
		String jmbg = pacijent == null ? "Dodavanje pacijenta" : "Izmena podataka o pacijentu: " + pacijent.getJmbg();
		setTitle(jmbg);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (pacijent != null) {
			popuniPolja(identifikator);
		}
		initListeners(identifikator);
		pack();
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		for (Lekar lekar : this.sistem.getLekari()) {
			cbLekar.addItem(lekar.getJmbg());
		}
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
		add(lblIzabraniLekar);
		add(cbLekar,"split 2");
		add(btnLekar);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}

	private void popuniPolja(int identifikator) {
		if(identifikator==2){
			txtIme.setText(pacijent.getIme());
			txtPrezime.setText(pacijent.getPrezime());
			txtJMBG.setText(pacijent.getJmbg());
			cbPol.setSelectedItem(this.pacijent.getPol());
			txtAdresa.setText(pacijent.getAdresa());
			txtBrTelefona.setText(pacijent.getBrTelefona());
			txtKorisnicko.setText(pacijent.getKorisnicko());
			pfLozinka.setText(pacijent.getLozinka());
			pacijent.setUloga(uloga);
			cbLekar.setSelectedItem(pacijent.getIzabraniLekar().getJmbg());
		}
		if (identifikator==1){
			btnLekar.setVisible(false);
			txtIme.setEnabled(false);
			txtIme.setText(pacijent.getIme());
			txtPrezime.setEnabled(false);
			txtPrezime.setText(pacijent.getPrezime());
			txtJMBG.setEnabled(false);
			txtJMBG.setText(pacijent.getJmbg());
			cbPol.setEnabled(false);
			cbPol.setSelectedItem(this.pacijent.getPol());
			txtAdresa.setEnabled(false);
			txtAdresa.setText(pacijent.getAdresa());
			txtBrTelefona.setEnabled(false);
			txtBrTelefona.setText(pacijent.getBrTelefona());
			txtKorisnicko.setEnabled(false);
			txtKorisnicko.setText(pacijent.getKorisnicko());
			pfLozinka.setEnabled(false);
			pfLozinka.setText(pacijent.getLozinka());
			pacijent.setUloga(uloga);
			cbLekar.setEnabled(false);
			cbLekar.setSelectedItem(pacijent.getIzabraniLekar().getJmbg());
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
					String prezime = txtPrezime.getText().trim();
					String JMBG = txtJMBG.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					String brTelefona = txtBrTelefona.getText().trim();
					String korisnicko = txtKorisnicko.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					String izabraniLekarJmbg=cbLekar.getSelectedItem().toString();
					Lekar izabraniLekar=sistem.pronadjiLekara(izabraniLekarJmbg);
					ZdravstvenaKnjizica knjizica=sistem.pronadjiKnjizicu(JMBG);
					DodavanjeKnjizicePrikaz dkp=new DodavanjeKnjizicePrikaz(sistem, knjizica, 0,JMBG);
					dkp.setVisible(true);
					if(pacijent == null) {
						Pacijent pacijent = new Pacijent(ime, prezime, JMBG, pol, adresa, brTelefona, korisnicko, lozinka,uloga, izabraniLekar);
						sistem.dodajPacijenta(pacijent);
					}else {
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(JMBG);
						pacijent.setPol(pol);
						pacijent.setAdresa(adresa);
						pacijent.setBrTelefona(brTelefona);
						pacijent.setKorisnicko(korisnicko);
						pacijent.setLozinka(lozinka);
						pacijent.setUloga(uloga);
						pacijent.setIzabraniLekar(izabraniLekar);
					}
					sistem.snimiPacijenta("pacijenti.txt");
					DodavanjePacijenataPrikaz.this.dispose();
					DodavanjePacijenataPrikaz.this.setVisible(false);
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePacijenataPrikaz.this.dispose();
				DodavanjePacijenataPrikaz.this.setVisible(false);
			}
		});
		btnLekar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String jmbgLekara = cbLekar.getSelectedItem().toString();
				Lekar lekar=sistem.pronadjiLekara(jmbgLekara);
				DodavanjeLekaraPrikaz dlp=new DodavanjeLekaraPrikaz(sistem, lekar, 1);
				dlp.setVisible(true);
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
			if(sistem.pronadjiPacijenta(txtJMBG.getText().trim())!=null){
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
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}