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
import guiprozoriadd.DodavanjeLekaraPrikaz;
import uloge.Lekar;

public class PrikazLekara extends JFrame {

	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolBar = new JToolBar();
	private JTable lekariTabela;
	private ZdrSistem sistem;

	public PrikazLekara(ZdrSistem sistem) {
		this.sistem = sistem;
		setTitle("Lista lekara");
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
				"Lozinka", "Uloga", "Plata", "Specijalizacija", "Sluzba" };
		Object[][] podatak = new Object[sistem.getLekari().size()][zaglavlje.length];
		for (int i = 0; i < sistem.getLekari().size(); i++) {
			Lekar lekar = sistem.getLekari().get(i);
			podatak[i][0] = lekar.getIme();
			podatak[i][1] = lekar.getPrezime();
			podatak[i][2] = lekar.getJmbg();
			podatak[i][3] = lekar.getPol();
			podatak[i][4] = lekar.getAdresa();
			podatak[i][5] = lekar.getBrTelefona();
			podatak[i][6] = lekar.getKorisnicko();
			podatak[i][7] = lekar.getLozinka();
			podatak[i][8] = lekar.getUloga();
			podatak[i][9] = lekar.getPlata();
			podatak[i][10] = lekar.getSpecijalizacija();
			podatak[i][11] = lekar.getSluzba();
		}
		DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
		lekariTabela = new JTable(model);
		lekariTabela.setRowSelectionAllowed(true);
		lekariTabela.setColumnSelectionAllowed(false);
		lekariTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lekariTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(lekariTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeLekaraPrikaz dlp=new DodavanjeLekaraPrikaz(sistem, null);
				dlp.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = lekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = lekariTabela.getValueAt(red, 2).toString();
					Lekar lekar = sistem.pronadjiLekara(jmbg);
					if(lekar != null) {
						DodavanjeLekaraPrikaz dLekara = new DodavanjeLekaraPrikaz(sistem, lekar);
						dLekara.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = lekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = lekariTabela.getValueAt(red, 2).toString();
					Lekar lekar = sistem.pronadjiLekara(jmbg);
					if(lekar != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete lekara?", lekar.getIme()+lekar.getPrezime() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) lekariTabela.getModel();
							if(lekar instanceof Lekar) {
								sistem.getLekari().remove(lekar);
							}
							model.removeRow(red);
							sistem.snimiLekara("lekari.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
