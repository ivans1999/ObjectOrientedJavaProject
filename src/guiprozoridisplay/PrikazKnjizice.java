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
import guiprozoriadd.DodavanjeKnjizicePrikaz;
import uloge.ZdravstvenaKnjizica;

public class PrikazKnjizice extends JFrame {
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/imgGui/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/imgGui/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolBar = new JToolBar();
	private JTable knjiziceTabela;
	private ZdrSistem sistem;
	
	public PrikazKnjizice(ZdrSistem sistem){
		
		this.sistem = sistem;
		setTitle("Lista lekara");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	
	private void initGUI() {
		toolBar.add(btnEdit);
		toolBar.add(btnRemove);
		add(toolBar, BorderLayout.NORTH);
		String[] zaglavlje = new String[] { "Broj knjizice", "Datum isteka", "Jmbg Pacijenta",
				"Kategorija osiguranja" };
		Object[][] podatak = new Object[sistem.getKnjizice().size()][zaglavlje.length];
		for (int i = 0; i < sistem.getKnjizice().size(); i++) {
			ZdravstvenaKnjizica knjizica = sistem.getKnjizice().get(i);
			podatak[i][0] = knjizica.getBrojKnjizice();
			podatak[i][1] = knjizica.getDatumIsteka();
			podatak[i][2] = knjizica.getJmbgPacijenta();
			podatak[i][3] = knjizica.getKategorijaOsiguranja();
		}
		DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
		knjiziceTabela = new JTable(model);
		knjiziceTabela.setRowSelectionAllowed(true);
		knjiziceTabela.setColumnSelectionAllowed(false);
		knjiziceTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjiziceTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(knjiziceTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initListeners() {
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int red = knjiziceTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String jmbg = knjiziceTabela.getValueAt(red, 2).toString();
					ZdravstvenaKnjizica knjizica = sistem.pronadjiKnjizicu(jmbg);
					if (knjizica != null) {
						DodavanjeKnjizicePrikaz dKnjizice = new DodavanjeKnjizicePrikaz(sistem, knjizica, 0,jmbg);
						dKnjizice.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String jmbg = knjiziceTabela.getValueAt(red, 2).toString();
					ZdravstvenaKnjizica knjizica = sistem.pronadjiKnjizicu(jmbg);
					if (knjizica != null) {
						int izbor = JOptionPane.showConfirmDialog(null,
								"Da li ste sigurni da zelite da obrisete knjizicu?",
								knjizica.getBrojKnjizice() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if (izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) knjiziceTabela.getModel();
							if (knjizica instanceof ZdravstvenaKnjizica) {
								sistem.getKnjizice().remove(knjizica);
							}
							model.removeRow(red);
							sistem.snimiKnjizicu("zdravstvena.txt");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu knjizicu!", "Greska",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
	}

}

