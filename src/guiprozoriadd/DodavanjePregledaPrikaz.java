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
import pregledi.Pregledi;

public class DodavanjePregledaPrikaz extends JFrame {

	private JLabel lblId = new JLabel("ID");
	private JTextField txtId = new JTextField(20);
	private JLabel lblJmbgPacijenta = new JLabel("Jmbg pacijenta");
	private JTextField txtJmbgPacijenta = new JTextField(20);
	private JLabel lblJmbgLekara = new JLabel("Jmbg lekara");
	private JTextField txtJmbgLekara = new JTextField(20);
	private JLabel lblDatumPregleda = new JLabel("Datum pregleda");
	private JTextField txtDatumPregleda = new JTextField(20);
	private JLabel lblSoba = new JLabel("Soba pregleda");
	private JTextField txtSoba = new JTextField(20);
	private JLabel lblStatus=new JLabel("Status");
	private JTextField txtStatus = new JTextField(20);
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");

	private ZdrSistem sistem;
	private Pregledi pregledi;

	public DodavanjePregledaPrikaz(ZdrSistem sistem, Pregledi pregled) {
		this.sistem = sistem;
		this.pregledi = pregled;
		String id = pregled == null ? "Dodavanje pregleda" : "Izmena podataka o pregledu: " + pregled.getID();
		setTitle(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (pregled != null) {
			popuniPolja();
		}
		initListeners();
		pack();
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		add(lblId);
		add(txtId);
		add(lblJmbgPacijenta);
		add(txtJmbgPacijenta);
		add(lblJmbgLekara);
		add(txtJmbgLekara);
		add(lblDatumPregleda);
		add(txtDatumPregleda);
		add(lblSoba);
		add(txtSoba);
		add(lblStatus);
		add(txtStatus);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}

	private void popuniPolja() {
		txtId.setText(String.valueOf(pregledi.getID()));
		txtJmbgPacijenta.setText(pregledi.getPacijent());
		txtJmbgLekara.setText(pregledi.getLekar());
		txtDatumPregleda.setText(pregledi.getTermin());
		txtSoba.setText(pregledi.getSoba());
		txtStatus.setText(pregledi.getStatus());
	}

	private void initListeners() {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int Id = Integer.parseInt(txtId.getText().trim());
				String pacijent = txtJmbgPacijenta.getText().trim();
				String lekar = txtJmbgLekara.getText().trim();
				String termin = txtDatumPregleda.getText().trim();
				String soba = txtSoba.getText().trim();
				String status=txtStatus.getText().trim();
				if(pregledi == null) {
					Pregledi pregled = new Pregledi(Id, pacijent, lekar, termin, soba,status);
					sistem.dodajPregled(pregled);
				}else {
					pregledi.setID(Id);
					pregledi.setPacijent(pacijent);
					pregledi.setLekar(lekar);
					pregledi.setTermin(termin);
					pregledi.setSoba(soba);
					pregledi.setStatus(status);
				}
				sistem.snimiPreglede("pregledi.txt");
				JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje", JOptionPane.DEFAULT_OPTION);
				DodavanjePregledaPrikaz.this.dispose();
				DodavanjePregledaPrikaz.this.setVisible(false);
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePregledaPrikaz.this.dispose();
				DodavanjePregledaPrikaz.this.setVisible(false);
			}
		});
	}

}
