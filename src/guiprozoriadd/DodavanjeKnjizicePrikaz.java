package guiprozoriadd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import funk.ZdrSistem;
import guiprozoriadd.DodavanjeKnjizicePrikaz;
import net.miginfocom.swing.MigLayout;
import uloge.KategorijaOsiguranja;
import uloge.ZdravstvenaKnjizica;

public class DodavanjeKnjizicePrikaz extends JFrame {
	
	private JLabel lblBrKnjizice = new JLabel("Broj knjizice");
	private JTextField txtBrKnjizice = new JTextField(20);
	private JLabel lblDatumIsteka = new JLabel("Datum isteka");
	private JTextField txtDatumIsteka = new JTextField(20);
	private JLabel lblJmbgPacijenta = new JLabel("JMBG pacijenta");
	private JTextField txtJmbgPacijenta = new JTextField(20);
	private JLabel lblKategorijaOsiguranja = new JLabel("Kategorija osiguranja");
	private JComboBox<KategorijaOsiguranja> cbKategorija=new JComboBox<KategorijaOsiguranja>(KategorijaOsiguranja.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	
	private ZdrSistem sistem;
	private ZdravstvenaKnjizica knjizica;
	
	public DodavanjeKnjizicePrikaz(ZdrSistem sistem, ZdravstvenaKnjizica knjizica, int Identifikator,String jmbg){
		this.sistem=sistem;
		this.knjizica=knjizica;
		String id = knjizica == null ? "Dodavanje knjizice"
				: "Izmena podataka o knjizici: " + knjizica.getBrojKnjizice();
		setTitle(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if(knjizica==null){
			txtJmbgPacijenta.setText(jmbg);
			txtJmbgPacijenta.setEnabled(false);
			txtBrKnjizice.setEnabled(false);
			txtBrKnjizice.setText(String.valueOf(sistem.PronadjNajveciBr()+1));
		}
		if (knjizica != null) {
			popuniPolja(Identifikator);
		}
		initListeners();
		pack();
	}
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		add(lblBrKnjizice);
		add(txtBrKnjizice);
		add(lblDatumIsteka);
		add(txtDatumIsteka);
		add(lblJmbgPacijenta);
		add(txtJmbgPacijenta);
		add(lblKategorijaOsiguranja);
		add(cbKategorija);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}
	
	private void popuniPolja(int Identifikator) {
		if (Identifikator == 0) {
			txtBrKnjizice.setEnabled(false);
			txtBrKnjizice.setText(knjizica.getBrojKnjizice());
			txtDatumIsteka.setText(sistem.VremeUString(knjizica.getDatumIsteka(), sistem.getFormatKnjizice()));
			txtJmbgPacijenta.setEnabled(false);
			txtJmbgPacijenta.setText(knjizica.getJmbgPacijenta());
			cbKategorija.setSelectedItem(this.knjizica.getKategorijaOsiguranja());
		}
		if (Identifikator == 1) {
			txtBrKnjizice.setEnabled(false);
			txtBrKnjizice.setText(knjizica.getBrojKnjizice());
			txtDatumIsteka.setEnabled(false);
			txtDatumIsteka.setText(sistem.VremeUString(knjizica.getDatumIsteka(), sistem.getFormatKnjizice()));
			txtJmbgPacijenta.setEnabled(false);
			txtJmbgPacijenta.setText(knjizica.getJmbgPacijenta());
			cbKategorija.setEnabled(false);
			cbKategorija.setSelectedItem(this.knjizica.getKategorijaOsiguranja());
			btnOK.setVisible(false);
			btnOtkazi.setVisible(false);
		}
	}
	
	private void initListeners() {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()==true){
					String BrKnjizice = txtBrKnjizice.getText().trim();
					GregorianCalendar datumIsteka = sistem.SamoDatum(txtDatumIsteka.getText().trim());
					String JmbgPacijenta = txtJmbgPacijenta.getText().trim();
					KategorijaOsiguranja kategorija=(KategorijaOsiguranja) cbKategorija.getSelectedItem();
					if (knjizica == null) {
						ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(BrKnjizice, datumIsteka, JmbgPacijenta,
								kategorija);
						sistem.dodajKnjizicu(knjizica);
					} else {
						knjizica.setBrojKnjizice(BrKnjizice);
						knjizica.setDatumIsteka(datumIsteka);
						knjizica.setJmbgPacijenta(JmbgPacijenta);
						knjizica.setKategorijaOsiguranja(kategorija);
					}
					sistem.snimiKnjizicu("zdravstvena.txt");
					JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje", JOptionPane.DEFAULT_OPTION);
					DodavanjeKnjizicePrikaz.this.dispose();
					DodavanjeKnjizicePrikaz.this.setVisible(false);
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DodavanjeKnjizicePrikaz.this.dispose();
				DodavanjeKnjizicePrikaz.this.setVisible(false);
				
			}
		});
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		try {
			sistem.getFormatKnjizice().parse(txtDatumIsteka.getText().trim());
		}catch (ParseException e) {
			poruka += "- Datum mora biti formata dd.MM.yyyy\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
