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
import guiprozoriadd.DodavanjeLekaraPrikaz;
import guiprozoriadd.DodavanjePacijenataPrikaz;
import uloge.Lekar;
import uloge.ZdravstvenaKnjizica;
import uloge.Pacijent;

public class PrikazPacijenata extends JFrame {
	
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/imgGui/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/imgGui/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/imgGui/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JButton btnPrikaziKnjizicu = new JButton("Prikazi knjizicu");
	private JButton btnPrikaziLekara = new JButton("Prikazi izabranog lekara");
	private JToolBar toolBar = new JToolBar();
	private JTable pacijentiTabela;
	private ZdrSistem sistem;

	public PrikazPacijenata(ZdrSistem sistem) {
		this.sistem = sistem;
		setTitle("Lista Pacijenata");
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
		toolBar.add(btnPrikaziKnjizicu);
		toolBar.add(btnPrikaziLekara);
		add(toolBar, BorderLayout.NORTH);
		String[] zaglavlje = new String[] { "Ime", "Prezime", "JMBG", "Pol ", "Adresa", "Broj telefona", "Korisnicko",
				"Lozinka", "Uloga", "Izabrani lekar"};
		Object[][] podatak = new Object[sistem.getPacijenti().size()][zaglavlje.length];
		for (int i = 0; i < sistem.getPacijenti().size(); i++) {
			Pacijent pacijent = sistem.getPacijenti().get(i);
			podatak[i][0] = pacijent.getIme();
			podatak[i][1] = pacijent.getPrezime();
			podatak[i][2] = pacijent.getJmbg();
			podatak[i][3] = pacijent.getPol();
			podatak[i][4] = pacijent.getAdresa();
			podatak[i][5] = pacijent.getBrTelefona();
			podatak[i][6] = pacijent.getKorisnicko();
			podatak[i][7] = pacijent.getLozinka();
			podatak[i][8] = pacijent.getUloga();
			podatak[i][9] = pacijent.getIzabraniLekar().getJmbg();
		}
		DefaultTableModel model = new DefaultTableModel(podatak, zaglavlje);
		pacijentiTabela = new JTable(model);
		pacijentiTabela.setRowSelectionAllowed(true);
		pacijentiTabela.setColumnSelectionAllowed(false);
		pacijentiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentiTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(pacijentiTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjePacijenataPrikaz dp=new DodavanjePacijenataPrikaz(sistem, null,0);
				dp.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = pacijentiTabela.getValueAt(red, 2).toString();
					Pacijent pacijent = sistem.pronadjiPacijenta(jmbg);
					if(pacijent != null) {
						DodavanjePacijenataPrikaz dPacijenta = new DodavanjePacijenataPrikaz(sistem, pacijent,2);
						dPacijenta.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = pacijentiTabela.getValueAt(red, 2).toString();
					Pacijent pacijent = sistem.pronadjiPacijenta(jmbg);
					if(pacijent != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete pacijenta?", pacijent.getIme()+pacijent.getPrezime() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) pacijentiTabela.getModel();
							if(pacijent instanceof Pacijent) {
								sistem.getPacijenti().remove(pacijent);
								ZdravstvenaKnjizica knjizica=sistem.pronadjiKnjizicu(jmbg);
								sistem.getKnjizice().remove(knjizica);
							}
							model.removeRow(red);
							sistem.snimiKnjizicu("zdravstvena.txt");
							sistem.snimiPacijenta("pacijenti.txt");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnPrikaziKnjizicu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = pacijentiTabela.getValueAt(red, 2).toString();
					ZdravstvenaKnjizica knjizica=sistem.pronadjiKnjizicu(jmbg);
					DodavanjeKnjizicePrikaz dkp=new DodavanjeKnjizicePrikaz(sistem, knjizica, 1, jmbg);
					dkp.setVisible(true);
				}
			}
		});
		btnPrikaziLekara.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String jmbg = pacijentiTabela.getValueAt(red, 9).toString();
					Lekar lekar= sistem.pronadjiLekara(jmbg);
					DodavanjeLekaraPrikaz dlp=new DodavanjeLekaraPrikaz(sistem, lekar, 1);
					dlp.setVisible(true);
				}
			}
		});
	}

}