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
	private JTextField txtPol = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Broj telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblKorisnicko = new JLabel("Korisnicko");
	private JTextField txtKorisnicko = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JTextField txtLozinka = new JTextField(20);
	private JLabel lblIzabraniLekar = new JLabel("Izabrani lekar");
	private JTextField txtIzabraniLekar = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");

	private ZdrSistem sistem;
	private Pacijent pacijent;

	public DodavanjePacijenataPrikaz(ZdrSistem sistem, Pacijent pacijent) {
		this.sistem = sistem;
		this.pacijent = pacijent;
		String jmbg = pacijent == null ? "Dodavanje pacijenta" : "Izmena podataka o pacijentu: " + pacijent.getJmbg();
		setTitle(jmbg);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (pacijent != null) {
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
		add(lblIzabraniLekar);
		add(txtIzabraniLekar);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}

	private void popuniPolja() {
		txtIme.setText(pacijent.getIme());
		txtPrezime.setText(pacijent.getPrezime());
		txtJMBG.setText(pacijent.getJmbg());
		txtPol.setText(pacijent.getPol());
		txtAdresa.setText(pacijent.getAdresa());
		txtBrTelefona.setText(pacijent.getBrTelefona());
		txtKorisnicko.setText(pacijent.getKorisnicko());
		txtLozinka.setText(pacijent.getLozinka());
		pacijent.setUloga(uloga);
		txtIzabraniLekar.setText(String.valueOf(pacijent.getIzabraniLekar()));
	}

	private void initListeners() {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String JMBG = txtJMBG.getText().trim();
				String pol = txtPol.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String brTelefona = txtBrTelefona.getText().trim();
				String korisnicko = txtKorisnicko.getText().trim();
				String lozinka = txtLozinka.getText().trim();
				Lekar izabraniLekar=new Lekar();
				izabraniLekar.setJmbg(txtIzabraniLekar.getText().trim());
				if(pacijent == null) {
					Pacijent pacijent = new Pacijent(ime, prezime, JMBG, pol, adresa, brTelefona, korisnicko, lozinka,uloga, izabraniLekar.getJmbg());
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
					pacijent.setIzabraniLekar(izabraniLekar.getJmbg());
				}
				sistem.snimiPacijenta("pacijenti.txt");
				JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje", JOptionPane.DEFAULT_OPTION);
				DodavanjePacijenataPrikaz.this.dispose();
				DodavanjePacijenataPrikaz.this.setVisible(false);
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePacijenataPrikaz.this.dispose();
				DodavanjePacijenataPrikaz.this.setVisible(false);
			}
		});
	}
}