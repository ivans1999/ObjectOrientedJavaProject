package guiprozoridisplay;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import funk.ZdrSistem;
import guiprozoriadd.DodavanjeSestaraPrikaz;
import uloge.Medicinskasestra;

public class PrikazSestara extends JFrame {
	
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/imgGui/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/imgGui/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/imgGui/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolBar = new JToolBar();
	private JTable sestreTabela;
	private ZdrSistem sistem;

	public PrikazSestara(ZdrSistem sistem) {
		this.sistem = sistem;
		setTitle("Lista sestara");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}

	private void initGUI() {
		toolBar.add(btnAdd);
		toolBar.add(btnEdit);
		toolBar.add(btnRemove);
		add(toolBar, BorderLayout.NORTH);
		String[] zaglavlje = new String[] { "Ime", "Prezime", "JMBG", "Pol ", "Adresa", "Broj telefona", "Korisnicko",
				"Lozinka", "Uloga", "Plata", "Sluzba" };
		Object[][] podatak = new Object[sistem.getSestre().size()][zaglavlje.length];
		for (int i = 0; i < sistem.getSestre().size(); i++) {
			Medicinskasestra sestra = sistem.getSestre().get(i);
			podatak[i][0] = sestra.getIme();
			podatak[i][1] = sestra.getPrezime();
			podatak[i][2] = sestra.getJmbg();
			podatak[i][3] = sestra.getPol();
			podatak[i][4] = sestra.getAdresa();
			podatak[i][5] = sestra.getBrTelefona();
			podatak[i][6] = sestra.getKorisnicko();
			podatak[i][7] = sestra.getLozinka();
			podatak[i][8] = sestra.getUloga();
			podatak[i][9] = sestra.getPlata();
			podatak[i][10] = sestra.getSluzba();
		}
		DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
		sestreTabela = new JTable(model);
		sestreTabela.setRowSelectionAllowed(true);
		sestreTabela.setColumnSelectionAllowed(false);
		sestreTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sestreTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(sestreTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeSestaraPrikaz dlp=new DodavanjeSestaraPrikaz(sistem, null,0);
				dlp.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = sestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = sestreTabela.getValueAt(red, 2).toString();
					Medicinskasestra sestra = sistem.pronadjiSestru(jmbg);
					if(sestra != null) {
						DodavanjeSestaraPrikaz dSestara = new DodavanjeSestaraPrikaz(sistem, sestra,2);
						dSestara.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = sestreTabela.getValueAt(red, 2).toString();
					Medicinskasestra sestra = sistem.pronadjiSestru(jmbg);
					if(sestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete sestru?", sestra.getIme()+sestra.getPrezime() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) sestreTabela.getModel();
							if(sestra instanceof Medicinskasestra) {
								sistem.getSestre().remove(sestra);
							}
							model.removeRow(red);
							sistem.snimiSestru("sestre.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}

}