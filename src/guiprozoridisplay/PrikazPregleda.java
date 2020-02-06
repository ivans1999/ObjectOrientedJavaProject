package guiprozoridisplay;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import funk.ZdrSistem;
import guiprozoriadd.DodavanjeLekaraPrikaz;
import guiprozoriadd.DodavanjePacijenataPrikaz;
import guiprozoriadd.DodavanjePregledaPrikaz;
import pregledi.Pregledi;
import pregledi.StatusPregleda;
import uloge.Lekar;
import uloge.Pacijent;
import uloge.ZdravstvenaKnjizica;


public class PrikazPregleda extends JFrame{
	
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/imgGui/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/imgGui/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/imgGui/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JButton btnLekar=new JButton("Prikazi lekara pregleda");
	private JButton btnPacijent=new JButton("Prikazi pacijenta pregleda");
	private JToolBar toolBar = new JToolBar();
	private JTable preglediTabela;
	private ZdrSistem sistem;
	private JButton btnRacun = new JButton("Prikazi racun");
	private JTextField txtRacun = new JTextField(20);
	private JLabel lblDinara= new JLabel("dinara.");
	private JButton btnIzmeniStatus=new JButton(editIcon);
	private JButton btnZatraziPregled=new JButton(addIcon);
	private ArrayList<Pregledi> pregledi= new ArrayList<Pregledi>();
	
	public PrikazPregleda(ZdrSistem sistem,String identifikator,String jmbg) {
		this.sistem = sistem;
		setTitle("Lista pregleda");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(identifikator,jmbg);
		initListeners(identifikator,jmbg);
	}

	private void initGUI(String identifikator,String jmbg) {
		if(identifikator.equals("Pacijent")){
			toolBar.add(btnZatraziPregled);
			toolBar.add(btnIzmeniStatus);
			toolBar.add(btnLekar);
			add(toolBar, BorderLayout.NORTH);
			String[] zaglavlje = new String[] { "Id", "Jmbg Pacijenta", "Jmbg Lekara", "Vreme pregleda", "Soba pregleda", "Status pregleda"};
			pregledi=sistem.pronadjiSvojePregledePacijent(jmbg);
			Object[][] podatak = new Object[pregledi.size()][zaglavlje.length];
			for (int i = 0; i < pregledi.size(); i++) {
				Pregledi pregled = pregledi.get(i);
				podatak[i][0] = pregled.getID();
				podatak[i][1] = pregled.getPacijent().getJmbg();
				podatak[i][2] = pregled.getLekar().getJmbg();
				podatak[i][3] = sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina());
				podatak[i][4] = pregled.getSoba();
				podatak[i][5] = pregled.getStatus();
			}
			DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
			preglediTabela = new JTable(model);
			preglediTabela.setRowSelectionAllowed(true);
			preglediTabela.setColumnSelectionAllowed(false);
			preglediTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			preglediTabela.setDefaultEditor(Object.class, null);
			JScrollPane scrollPane = new JScrollPane(preglediTabela);
			add(scrollPane, BorderLayout.CENTER);
		}

//		if (identifikator.equals("Lekar")){
//			toolBar.add(btnIzmeniStatus);
//			toolBar.add(btnPacijent);
//			add(toolBar, BorderLayout.NORTH);
//			String[] zaglavlje = new String[] { "Id", "Jmbg Pacijenta", "Jmbg Lekara", "Vreme pregleda", "Soba pregleda", "Status pregleda"};
//			pregledi=sistem.pronadjiSvojePregledeLekar(jmbg);
//			Object[][] podatak = new Object[pregledi.size()][zaglavlje.length];
//			for (int i = 0; i < pregledi.size(); i++) {
//				Pregledi pregled = pregledi.get(i);
//				podatak[i][0] = pregled.getID();
//				podatak[i][1] = pregled.getPacijent().getJmbg();
//				podatak[i][2] = pregled.getLekar().getJmbg();
//				podatak[i][3] = sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina());
//				podatak[i][4] = pregled.getSoba();
//				podatak[i][5] = pregled.getStatus();
//			}
//			DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
//			preglediTabela = new JTable(model);
//			preglediTabela.setRowSelectionAllowed(true);
//			preglediTabela.setColumnSelectionAllowed(false);
//			preglediTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			preglediTabela.setDefaultEditor(Object.class, null);
//			JScrollPane scrollPane = new JScrollPane(preglediTabela);
//			add(scrollPane, BorderLayout.CENTER);
//		}
		if (identifikator.equals("Sestra")){
			toolBar.add(btnAdd);
			toolBar.add(btnEdit);
			toolBar.add(btnRemove);
			toolBar.add(btnLekar);
			toolBar.add(btnPacijent);
			toolBar.add(btnRacun);
			toolBar.add(txtRacun);
			toolBar.add(lblDinara);
			add(toolBar, BorderLayout.NORTH);
			String[] zaglavlje = new String[] { "Id", "Jmbg Pacijenta", "Jmbg Lekara", "Vreme pregleda", "Soba pregleda", "Status pregleda"};
			Object[][] podatak = new Object[sistem.getPregledi().size()][zaglavlje.length];
			for (int i = 0; i < sistem.getPregledi().size(); i++) {
				Pregledi pregled = sistem.getPregledi().get(i);
				podatak[i][0] = pregled.getID();
				podatak[i][1] = pregled.getPacijent().getJmbg();
				podatak[i][2] = pregled.getLekar().getJmbg();
				podatak[i][3] = sistem.VremeUString(pregled.getTermin(),sistem.getFormatTermina());
				podatak[i][4] = pregled.getSoba();
				podatak[i][5] = pregled.getStatus();
			}
			DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
			preglediTabela = new JTable(model);
			preglediTabela.setRowSelectionAllowed(true);
			preglediTabela.setColumnSelectionAllowed(false);
			preglediTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			preglediTabela.setDefaultEditor(Object.class, null);
			JScrollPane scrollPane = new JScrollPane(preglediTabela);
			add(scrollPane, BorderLayout.CENTER);
		}
	}
	private void initListeners(String identifikator,String jmbg) {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePregledaPrikaz dpp=new DodavanjePregledaPrikaz(sistem, null,"Sestra",jmbg,0);
				dpp.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = preglediTabela.getValueAt(red, 0).toString();
					Pregledi pregled = sistem.pretraziId(id);
					if(pregled != null) {
						DodavanjePregledaPrikaz dPregleda = new DodavanjePregledaPrikaz(sistem, pregled,"Sestra",jmbg,1);
						dPregleda.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = preglediTabela.getValueAt(red, 0).toString();
					Pregledi pregled = sistem.pretraziId(id);
					if(pregled != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pregled?", pregled.getID() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) preglediTabela.getModel();
							if(pregled instanceof Pregledi) {
								sistem.getPregledi().remove(pregled);
							}
							model.removeRow(red);
							sistem.snimiPreglede("pregledi.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnLekar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String jmbg = preglediTabela.getValueAt(red, 2).toString();
					Lekar lekar = sistem.pronadjiLekara(jmbg);
					if (lekar != null) {
						DodavanjeLekaraPrikaz dLekara = new DodavanjeLekaraPrikaz(sistem, lekar, 1);
						dLekara.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnPacijent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String jmbg = preglediTabela.getValueAt(red, 1).toString();
					Pacijent pacijent = sistem.pronadjiPacijenta(jmbg);
					if (pacijent != null) {
						DodavanjePacijenataPrikaz dPacijenta = new DodavanjePacijenataPrikaz(sistem, pacijent, 1);
						dPacijenta.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRacun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}else{
					String jmbg = preglediTabela.getValueAt(red, 1).toString();
					Pacijent pacijent = sistem.pronadjiPacijenta(jmbg);
					ZdravstvenaKnjizica knjizica=sistem.pronadjiKnjizicu(pacijent.getJmbg());
					double racun=sistem.napraviRacun(knjizica.getKategorijaOsiguranja());
					txtRacun.setText(String.valueOf(racun));
				}
			}
		});
		btnIzmeniStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = preglediTabela.getValueAt(red, 0).toString();
					Pregledi pregled = sistem.pretraziId(id);
					if(pregled != null) {
						DodavanjePregledaPrikaz dPregleda = new DodavanjePregledaPrikaz(sistem, pregled,identifikator,jmbg,1);
						dPregleda.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnZatraziPregled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePregledaPrikaz dpp=new DodavanjePregledaPrikaz(sistem, null, "Pacijent",jmbg,0);
				dpp.setVisible(true);
			}
		});
	}

}