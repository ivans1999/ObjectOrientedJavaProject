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
import guiprozoriadd.DodavanjeLekaraPrikaz;
import guiprozoriadd.DodavanjePacijenataPrikaz;
import guiprozoriadd.DodavanjePregledaPrikaz;
import net.miginfocom.swing.MigLayout;
import pregledi.StatusPregleda;
import pregledi.Pregledi;
import uloge.Lekar;
import uloge.Pacijent;

public class DodavanjePregledaPrikaz extends JFrame {

	private JLabel lblId = new JLabel("ID");
	private JTextField txtId = new JTextField(20);
	private JLabel lblJmbgPacijenta = new JLabel("Jmbg pacijenta");
	private JComboBox<String> cbPacijent = new JComboBox<String>();
	private JButton btnPacijent=new JButton("Prikazi pacijenta");
	private JLabel lblJmbgLekara = new JLabel("Jmbg lekara");
	private JComboBox<String> cbLekar = new JComboBox<String>();
	private JButton btnLekar=new JButton("Prikazi lekara");
	private JLabel lblDatumPregleda = new JLabel("Vreme pregleda");
	private JTextField txtDatumPregleda = new JTextField(20);
	private JLabel lblSoba = new JLabel("Soba pregleda");
	private JTextField txtSoba = new JTextField(20);
	private JLabel lblStatus=new JLabel("Status");
	private JComboBox<StatusPregleda> cbStatus=new JComboBox<StatusPregleda>(StatusPregleda.values());
	private JButton btnOK = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private JComboBox<StatusPregleda> cbPosebniStatusi= new JComboBox<StatusPregleda>();

	private ZdrSistem sistem;
	private Pregledi pregled;

	public DodavanjePregledaPrikaz(ZdrSistem sistem, Pregledi pregled,String identifikator,String jmbg,int i) {
		this.sistem = sistem;
		this.pregled = pregled;
		String id = pregled == null ? "Dodavanje pregleda" : "Izmena podataka o pregledu: " + pregled.getID();
		setTitle(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if (pregled == null){
			if(identifikator.equals("Pacijent")){
				Pacijent pacijent=sistem.pronadjiPacijenta(jmbg);
				txtId.setEnabled(false);
				txtId.setText(String.valueOf(sistem.pronadjiNajveciId()+1));
				lblJmbgPacijenta.setVisible(false);
				cbPacijent.setVisible(false);
				cbPacijent.setSelectedItem(pacijent.getJmbg());
				btnPacijent.setVisible(false);
				cbLekar.setEnabled(false);
				cbLekar.setSelectedItem(pacijent.getIzabraniLekar().getJmbg());
				GregorianCalendar datum=new GregorianCalendar(2020,12,01,07,00);
				txtDatumPregleda.setEnabled(false);
				txtDatumPregleda.setText(String.valueOf(sistem.VremeUString(datum,sistem.getFormatTermina())));
				txtSoba.setEnabled(false);
				txtSoba.setText("0");
				cbStatus.setEnabled(false);
				cbPosebniStatusi.setVisible(false);
			}
			if (identifikator.equals("Sestra")){
				txtId.setEnabled(false);
				txtId.setText(String.valueOf(sistem.pronadjiNajveciId()+1));
				cbPosebniStatusi.setVisible(false);
			}
			
		}
		if (pregled != null) {
			popuniPolja(identifikator,i);
		}
		initListeners(identifikator,i);
		pack();
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		for (Pacijent pacijent : this.sistem.getPacijenti()) {
			cbPacijent.addItem(pacijent.getJmbg());
		}
		for (Lekar lekar : this.sistem.getLekari()) {
			cbLekar.addItem(lekar.getJmbg());
		}
		add(lblId);
		add(txtId);
		add(lblJmbgPacijenta);
		add(cbPacijent,"split2");
		add(btnPacijent);
		add(lblJmbgLekara);
		add(cbLekar,"split2");
		add(btnLekar);
		add(lblDatumPregleda);
		add(txtDatumPregleda);
		add(lblSoba);
		add(txtSoba);
		add(lblStatus);
		add(cbStatus,"split 2");
		add(cbPosebniStatusi);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnOtkazi);
	}

	private void popuniPolja(String identifikator,int i) {
		if(identifikator.equals("Pacijent")){
			btnPacijent.setVisible(false);
			btnLekar.setVisible(true);
			txtId.setEnabled(false);
			txtId.setText(String.valueOf(pregled.getID()));
			lblJmbgPacijenta.setVisible(false);
			cbPacijent.setVisible(false);
			cbPacijent.setSelectedItem(pregled.getPacijent().getJmbg());
			cbLekar.setEnabled(false);
			cbLekar.setSelectedItem(pregled.getLekar().getJmbg());
			txtDatumPregleda.setEnabled(false);
			txtDatumPregleda.setText(String.valueOf(sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina())));
			txtSoba.setEnabled(false);
			txtSoba.setText(pregled.getSoba());
			cbStatus.setEnabled(false);
			cbStatus.setVisible(true);
			cbPosebniStatusi.setVisible(false);
			cbStatus.setSelectedItem(this.pregled.getStatus());
			btnOK.setVisible(false);
			btnOtkazi.setVisible(false);
			if (pregled.getStatus()==StatusPregleda.Zakazan){
				btnOK.setVisible(true);
				btnOtkazi.setVisible(true);
				cbStatus.setVisible(false);
				cbPosebniStatusi.addItem(StatusPregleda.Otkazan);
				cbPosebniStatusi.addItem(StatusPregleda.Zakazan);
				cbPosebniStatusi.setVisible(true);
				cbPosebniStatusi.setSelectedItem(this.pregled.getStatus());
			}
		}
		if(identifikator.equals("Lekar")){
			btnPacijent.setVisible(true);
			btnLekar.setVisible(false);
			txtId.setEnabled(false);
			txtId.setText(String.valueOf(pregled.getID()));
			cbPacijent.setEnabled(false);
			cbPacijent.setSelectedItem(pregled.getPacijent().getJmbg());
			lblJmbgLekara.setVisible(false);
			cbLekar.setVisible(false);
			cbLekar.setSelectedItem(pregled.getLekar().getJmbg());
			txtDatumPregleda.setEnabled(false);
			txtDatumPregleda.setText(String.valueOf(sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina())));
			txtSoba.setEnabled(false);
			txtSoba.setText(pregled.getSoba());
			cbStatus.setVisible(false);
			cbPosebniStatusi.addItem(StatusPregleda.Otkazan);
			cbPosebniStatusi.addItem(StatusPregleda.Zavrsen);
			cbPosebniStatusi.setSelectedItem(this.pregled.getStatus());
		}
		if(identifikator.equals("Sestra")){
			if (i==1){
				cbPosebniStatusi.setVisible(false);
				btnPacijent.setVisible(true);
				btnLekar.setVisible(true);
				txtId.setEnabled(false);
				txtId.setText(String.valueOf(pregled.getID()));
				cbPacijent.setEnabled(false);
				cbPacijent.setSelectedItem(pregled.getPacijent().getJmbg());
				cbLekar.setSelectedItem(pregled.getLekar().getJmbg());
				txtDatumPregleda.setEnabled(true);
				txtDatumPregleda.setText(String.valueOf(sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina())));
				txtSoba.setText(pregled.getSoba());
				cbStatus.setSelectedItem(this.pregled.getStatus());
			}else{
				cbPosebniStatusi.setVisible(false);
				btnPacijent.setVisible(true);
				btnLekar.setVisible(true);
				txtId.setEnabled(false);
				txtId.setText(String.valueOf(pregled.getID()));
				cbPacijent.setSelectedItem(pregled.getPacijent().getJmbg());
				cbLekar.setSelectedItem(pregled.getLekar().getJmbg());
				txtDatumPregleda.setText(String.valueOf(sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina())));
				txtSoba.setText(pregled.getSoba());
				cbStatus.setSelectedItem(this.pregled.getStatus());
			}
		}
	}

	private void initListeners(String identifikator,int i) {
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija(i,identifikator)==true){
					int Id = Integer.parseInt(txtId.getText().trim());
					String jmbgPacijenta = cbPacijent.getSelectedItem().toString();
					Pacijent pacijent=sistem.pronadjiPacijenta(jmbgPacijenta);
					String jmbgLekara = cbLekar.getSelectedItem().toString();
					Lekar lekar=sistem.pronadjiLekara(jmbgLekara);
					GregorianCalendar termin = sistem.napraviDatumIVreme(txtDatumPregleda.getText().trim());
					String soba = txtSoba.getText().trim();
					StatusPregleda status;
					if((identifikator.equals("Lekar")) || (identifikator.equals("Pacijent")) && (i==1)){
						status=(StatusPregleda) cbPosebniStatusi.getSelectedItem();
					}
					else if ((identifikator.equals("Pacijent") && (i==0))){
						status=(StatusPregleda) cbStatus.getSelectedItem();
					}
					else if(identifikator.equals("Sestra")){
						status=(StatusPregleda) cbStatus.getSelectedItem();
					}else{
						status=StatusPregleda.Zatrazen;
					}
					if(pregled == null) {
						Pregledi pregled = new Pregledi(Id, pacijent, lekar, termin, soba,status);
						sistem.dodajPregled(pregled);
					}else {
						pregled.setID(Id);
						pregled.setPacijent(pacijent);
						pregled.setLekar(lekar);
						pregled.setTermin(termin);
						pregled.setSoba(soba);
						pregled.setStatus(status);
					}
					sistem.snimiPreglede("pregledi.txt");
					JOptionPane.showMessageDialog(null, "Snimanje je uspesno.", "Obavestenje", JOptionPane.DEFAULT_OPTION);
					DodavanjePregledaPrikaz.this.dispose();
					DodavanjePregledaPrikaz.this.setVisible(false);
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePregledaPrikaz.this.dispose();
				DodavanjePregledaPrikaz.this.setVisible(false);
			}
		});
		btnPacijent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String jmbgPacijenta = cbPacijent.getSelectedItem().toString();
				Pacijent pacijent=sistem.pronadjiPacijenta(jmbgPacijenta);
				DodavanjePacijenataPrikaz dpp=new DodavanjePacijenataPrikaz(sistem, pacijent, 1);
				dpp.setVisible(true);
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
	
	private boolean validacija(int i,String s) {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtSoba.getText().trim().equals("")) {
			poruka += "- Unesite sobu pregleda\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
