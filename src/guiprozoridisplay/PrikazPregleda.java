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
import guiprozoriadd.DodavanjePregledaPrikaz;
import pregledi.Pregledi;


public class PrikazPregleda extends JFrame{
	
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolBar = new JToolBar();
	private JTable preglediTabela;
	private ZdrSistem sistem;

	public PrikazPregleda(ZdrSistem sistem) {
		this.sistem = sistem;
		setTitle("Lista pregleda");
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
//		112|123477789|123456789|25-05-2019|soba23|Zakazan|
		String[] zaglavlje = new String[] { "Id", "Jmbg Pacijenta", "Jmbg Lekara", "Datum pregleda", "Soba pregleda", "Status pregleda"};
		Object[][] podatak = new Object[sistem.getPregledi().size()][zaglavlje.length];
		for (int i = 0; i < sistem.getPregledi().size(); i++) {
			Pregledi pregled = sistem.getPregledi().get(i);
			podatak[i][0] = pregled.getID();
			podatak[i][1] = pregled.getPacijent();
			podatak[i][2] = pregled.getLekar();
			podatak[i][3] = pregled.getTermin();
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
	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePregledaPrikaz dpp=new DodavanjePregledaPrikaz(sistem, null);
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
						DodavanjePregledaPrikaz dPregleda = new DodavanjePregledaPrikaz(sistem, pregled);
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
	}

}