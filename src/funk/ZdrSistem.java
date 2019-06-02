package funk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import pregledi.Pregledi;
import uloge.Lekar;
import uloge.Medicinskasestra;
import uloge.Osoba;
import uloge.Pacijent;
import uloge.ZdravstvenaKnjizica;

public class ZdrSistem {

	private ArrayList<String> lista1 = new ArrayList<String>();
	private ArrayList<Pregledi> preglediPacijenata = new ArrayList<Pregledi>();
	private ArrayList<Medicinskasestra> sestre;
	private ArrayList<Lekar> lekari;
	private ArrayList<Osoba> osobe;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<Pregledi> pregledi;
	private ArrayList<ZdravstvenaKnjizica> knjizice;

	public ZdrSistem() {
		this.sestre = new ArrayList<Medicinskasestra>();
		this.lekari = new ArrayList<Lekar>();
		this.pacijenti = new ArrayList<Pacijent>();
		this.pregledi = new ArrayList<Pregledi>();
		this.knjizice = new ArrayList<ZdravstvenaKnjizica>();
		this.osobe = new ArrayList<Osoba>();
	}

	public ArrayList<Medicinskasestra> getSestre() {
		return sestre;
	}

	public void setSestre(ArrayList<Medicinskasestra> sestre) {
		this.sestre = sestre;
	}

	public void dodajSestru(Medicinskasestra sestra) {
		this.sestre.add(sestra);
	}

	public ArrayList<Osoba> getOsobe() {
		return osobe;
	}

	public void dodajOsobu(Osoba osobe) {
		this.osobe.add(osobe);
	}

	public void setOsobe(ArrayList<Osoba> osobe) {
		this.osobe = osobe;
	}

	public ArrayList<Lekar> getLekari() {
		return lekari;
	}

	public void dodajLekara(Lekar lekar) {
		this.lekari.add(lekar);
	}

	public void setLekari(ArrayList<Lekar> lekari) {
		this.lekari = lekari;
	}

	public ArrayList<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void dodajPacijenta(Pacijent pacijent) {
		this.pacijenti.add(pacijent);
	}

	public void setPacijenti(ArrayList<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}

	public ArrayList<Pregledi> getPregledi() {
		return pregledi;
	}

	public void dodajPregled(Pregledi pregled) {
		this.pregledi.add(pregled);
	}

	public void setPregledi(ArrayList<Pregledi> pregledi) {
		this.pregledi = pregledi;
	}

	public ArrayList<ZdravstvenaKnjizica> getKnjizice() {
		return knjizice;
	}

	public void dodajKnjizicu(ZdravstvenaKnjizica knjizica) {
		this.knjizice.add(knjizica);
	}

	public void setKnjizice(ArrayList<ZdravstvenaKnjizica> knjizice) {
		this.knjizice = knjizice;
	}

	public void ucitajKnjizicu(String imeFajla) {
		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String broj = split[0];
				String datumIsteka = split[1];
				String jmbgPacijenta = split[2];
				int kategorija = Integer.parseInt(split[3]);
				ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(broj, datumIsteka, jmbgPacijenta, kategorija);
				knjizice.add(knjizica);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja knjizica");
			e.printStackTrace();
		}
	}

	public void snimiKnjizicu(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			String content = "";
			for (ZdravstvenaKnjizica knjizica : knjizice) {
				content += knjizica.getBrojKnjizice() + "|" + knjizica.getDatumIsteka() + "|"
						+ knjizica.getJmbgPacijenta() + "|" + knjizica.getKategorijaOsiguranja() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja knjizica.");
		}

	}

	public void ucitajLekare(String imeFajla) {
		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");			
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String pol = split[3];
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				double plata = Double.parseDouble(split[9]);
				String specijalizacija = split[10];
				String sluzba = split[11];
				Lekar lekar = new Lekar(ime, prezime, jmbg, pol, adresa, brTelefona, korisnicko, lozinka, uloga, plata,
						specijalizacija, sluzba);
				lekari.add(lekar);
				dodajOsobu(lekar);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o lekaru.");
			e.printStackTrace();
		}

	}

	public void snimiLekara(String imeFajla) {
		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			String content = "";
			for (Lekar lekar : lekari) {
				content += lekar.getIme() + "|" + lekar.getPrezime() + "|" + lekar.getJmbg() + "|" + lekar.getPol()
						+ "|" + lekar.getAdresa() + "|" + lekar.getBrTelefona() + "|" + lekar.getKorisnicko() + "|"
						+ lekar.getLozinka() + "|" + lekar.getUloga() + "|" + lekar.getPlata() + "|"
						+ lekar.getSpecijalizacija() + "|" + lekar.getSluzba() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja lekara.");
		}

	}

	public void ucitajPacijenta(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String pol = split[3];
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				String jmbglekara = split[9];
				Lekar izabraniLekar = new Lekar();
				for (Pregledi pregled : pregledi) {
					for (String id : lista1) {
						if (id.equals(pregled.getID())) {
							preglediPacijenata.add(pregled);
						}
					}
				}
				for (Lekar lekar : lekari) {
					if (jmbglekara.equals(lekar.getJmbg())) {
						izabraniLekar = lekar;
					}
				}
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, pol, adresa, brTelefona, korisnicko, lozinka,
						uloga, izabraniLekar.getJmbg());
				dodajPacijenta(pacijent);
				dodajOsobu(pacijent);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o pacijentu.");
			e.printStackTrace();
		}

	}

	public void snimiPacijenta(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			String content = "";
			for (Pacijent pacijent : pacijenti) {
				content += pacijent.getIme() + "|" + pacijent.getPrezime() + "|" + pacijent.getJmbg() + "|"
						+ pacijent.getPol() + "|" + pacijent.getAdresa() + "|" + pacijent.getBrTelefona() + "|"
						+ pacijent.getKorisnicko() + "|" + pacijent.getLozinka() + "|" + pacijent.getUloga() + "|"
						+ pacijent.getIzabraniLekar()  + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pacijenata.");
		}
	}

	public void ucitajSestru(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String pol = split[3];
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				double plata = Double.parseDouble(split[9]);
				String sluzba = split[10];
				Medicinskasestra sestra = new Medicinskasestra(ime, prezime, jmbg, pol, adresa, brTelefona, korisnicko,
						lozinka, uloga, plata, sluzba);
				sestre.add(sestra);
				dodajOsobu(sestra);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o medicinskoj sestri.");
			e.printStackTrace();
		}

	}

	public void snimiSestru(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			String content = "";
			for (Medicinskasestra sestra : sestre) {
				content += sestra.getIme() + "|" + sestra.getPrezime() + "|" + sestra.getJmbg() + "|" + sestra.getPol()
						+ "|" + sestra.getAdresa() + "|" + sestra.getBrTelefona() + "|" + sestra.getKorisnicko() + "|"
						+ sestra.getLozinka() + "|" + sestra.getUloga() + "|" + sestra.getPlata() + "|"
						+ sestra.getSluzba() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja medicinske sestre.");
		}
	}

	public void ucitajPreglede(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				int id = Integer.parseInt(split[0]);
				String jmbgPacijenta = split[1];
				String jmbgLekara = split[2];
				String termin = split[3];
				String soba = split[4];
				String status = split[5];
				Pacijent pacijentPregleda = new Pacijent();
				Lekar lekarPregleda = new Lekar();
				for (Lekar lekar : lekari) {
					if (jmbgLekara.equals(lekar.getJmbg()))
						lekarPregleda = lekar;
				}
				for (Pacijent pacijent : pacijenti) {
					if (jmbgPacijenta.equals(pacijent.getJmbg()))
						pacijentPregleda = pacijent;
				}
				Pregledi pregled = new Pregledi(id, pacijentPregleda.getJmbg(), lekarPregleda.getJmbg(), termin, soba, status);
				pregledi.add(pregled);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja podataka o pregledima.");
			e.printStackTrace();
		}

	}

	public void snimiPreglede(String imeFajla) {

		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			String content = "";
			for (Pregledi pregled : pregledi) {
				content += pregled.getID() + "|" + pregled.getPacijent() + "|" + pregled.getLekar() + "|" + pregled.getTermin() + "|"
						+ pregled.getSoba() + "|" + pregled.getStatus() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pregleda.");
		}

	}

	public Osoba login(String korisnicko, String sifra) {
		for (Osoba osoba : osobe) {
			if (osoba.getKorisnicko().equalsIgnoreCase(korisnicko) && osoba.getLozinka().equals(sifra)) {
				return osoba;
			}
		}
		return null;
	}

	public Lekar pronadjiLekara(String jmbg) {
		for (Lekar lekar : lekari) {
			if (lekar.getJmbg().equals(jmbg)) {
				return lekar;
			}
		}
		return null;
	}
	
	public Medicinskasestra pronadjiSestru(String jmbg) {
		for (Medicinskasestra sestra : sestre) {
			if (sestra.getJmbg().equals(jmbg)) {
				return sestra;
			}
		}
		return null;
	}
	
	public Pacijent pronadjiPacijenta(String jmbg) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getJmbg().equals(jmbg)) {
				return pacijent;
			}
		}
		return null;
	}

	public Pregledi pretraziId(String id) {
		for (Pregledi pregled : pregledi) {
			if (id.equals(pregled.getID())) {
				return pregled;
			}
		}
		return null;
	}

}