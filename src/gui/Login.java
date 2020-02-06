package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import funk.ZdrSistem;
import guiprozoridisplay.PrikazPregleda;
import net.miginfocom.swing.MigLayout;
import uloge.Osoba;

public class Login extends JFrame {
	
	private JLabel lblPoruka=new JLabel("Dobrodosli, molimo Vas da se prijavite.");
	private JLabel lblKorisnickoIme=new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme=new JTextField(20);
	private JLabel lblSifra= new JLabel("Sifra");
	private JPasswordField txtSifra=new JPasswordField(20);
	private JLabel lblPrazno =new JLabel();
	private JButton btnPrijava=new JButton("Prijava");
	private JButton btnCancel=new JButton("Cancel");

	private ZdrSistem sistem;
	
	public Login(ZdrSistem sistem) {
		this.sistem=sistem;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initListeners();
		pack();
	}
	
	private void initGUI() {
		MigLayout mig=new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lblPoruka,"span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(txtSifra);
		add(lblPrazno);
		add(btnPrijava,"split 2");
		add(btnCancel);
	}

	private void initListeners() {
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				Login.this.setVisible(false);
			}
		});
		btnPrijava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = txtKorisnickoIme.getText().trim();
				String password = new String(txtSifra.getPassword()).trim();

				Osoba prijavljen = sistem.login(username, password);
				if (prijavljen == null) {
					JOptionPane.showMessageDialog(null, "Neispravni login podaci", "Prijava",
							JOptionPane.WARNING_MESSAGE);
				}
				else if (prijavljen.getUloga().equals("Lekar")) {
					ProzorzaLekara gp = new ProzorzaLekara(sistem, prijavljen);
					gp.setVisible(true);
					Login.this.dispose();
					Login.this.setVisible(false);
				} else if (prijavljen.getUloga().equals("Sestra")) {
					Prozorzasestru gp=new Prozorzasestru(sistem, prijavljen);
					gp.setVisible(true);
					Login.this.dispose();
					Login.this.setVisible(false);
				} else if (prijavljen.getUloga().equals("Pacijent")) {
					PrikazPregleda  pp = new PrikazPregleda(sistem,"Pacijent",prijavljen.getJmbg());
					pp.setVisible(true);
					Login.this.dispose();
					Login.this.setVisible(false);
				}

			}
		});
	}
}