package funk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import pregledi.Pregledi;
import uloge.KategorijaOsiguranja;
import uloge.Pol;
import uloge.SluzbeZaLekara;
import uloge.SluzbeZaSestru;
import uloge.Lekar;
import uloge.Medicinskasestra;
import uloge.Osoba;
import uloge.Pacijent;
import uloge.ZdravstvenaKnjizica;
import pregledi.StatusPregleda;

public class ZdrSistem {

	private ArrayList<Medicinskasestra> sestre;
	private ArrayList<Lekar> lekari;
	private ArrayList<Osoba> osobe;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<Pregledi> pregledi;
	private ArrayList<ZdravstvenaKnjizica> knjizice;
	SimpleDateFormat formatKnjizice = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat formatTermina = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

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
	public SimpleDateFormat getFormatKnjizice() {
		return formatKnjizice;
	}

	public void setFormatKnjizice(SimpleDateFormat formatKnjizice) {
		this.formatKnjizice = formatKnjizice;
	}

	public SimpleDateFormat getFormatTermina() {
		return formatTermina;
	}

	public void setFormatTermina(SimpleDateFormat formatTermina) {
		this.formatTermina = formatTermina;
	}

	public void ucitajKnjizicu(String imeFajla) {
		try {
			File file = new File("src/fajlovisapodacima/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String broj = split[0];
				GregorianCalendar datumIsteka = SamoDatum(split[1]);
				String jmbgPacijenta = split[2];
				int kategorijaInt = Integer.parseInt(split[3]);
				KategorijaOsiguranja kategorija = KategorijaOsiguranja.fromInt(kategorijaInt);
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
				content += knjizica.getBrojKnjizice() + "|" + VremeUString(knjizica.getDatumIsteka(), formatKnjizice)
						+ "|" + knjizica.getJmbgPacijenta() + "|"
						+ KategorijaOsiguranja.toInt(knjizica.getKategorijaOsiguranja()) + "\n";
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
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.fromInt(polInt);
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				double plata = Double.parseDouble(split[9]);
				String specijalizacija = split[10];
				int sluzbaInt = Integer.parseInt(split[11]);
				SluzbeZaLekara sluzba = SluzbeZaLekara.fromInt(sluzbaInt);
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
				content += lekar.getIme() + "|" + lekar.getPrezime() + "|" + lekar.getJmbg() + "|"
						+ Pol.toInt(lekar.getPol()) + "|" + lekar.getAdresa() + "|" + lekar.getBrTelefona() + "|"
						+ lekar.getKorisnicko() + "|" + lekar.getLozinka() + "|" + lekar.getUloga() + "|"
						+ lekar.getPlata() + "|" + lekar.getSpecijalizacija() + "|"
						+ SluzbeZaLekara.toInt(lekar.getSluzba()) + "\n";
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
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.fromInt(polInt);
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				String jmbglekara = split[9];
				Lekar izabraniLekar = new Lekar();
				izabraniLekar = pronadjiLekara(jmbglekara);
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, pol, adresa, brTelefona, korisnicko, lozinka,
						uloga, izabraniLekar);
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
						+ Pol.toInt(pacijent.getPol()) + "|" + pacijent.getAdresa() + "|" + pacijent.getBrTelefona()
						+ "|" + pacijent.getKorisnicko() + "|" + pacijent.getLozinka() + "|" + pacijent.getUloga() + "|"
						+ pacijent.getIzabraniLekar().getJmbg() + "\n";
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
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.fromInt(polInt);
				String adresa = split[4];
				String brTelefona = split[5];
				String korisnicko = split[6];
				String lozinka = split[7];
				String uloga = split[8];
				double plata = Double.parseDouble(split[9]);
				int sluzbaInt = Integer.parseInt(split[10]);
				SluzbeZaSestru sluzba = SluzbeZaSestru.fromInt(sluzbaInt);
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
				content += sestra.getIme() + "|" + sestra.getPrezime() + "|" + sestra.getJmbg() + "|"
						+ Pol.toInt(sestra.getPol()) + "|" + sestra.getAdresa() + "|" + sestra.getBrTelefona() + "|"
						+ sestra.getKorisnicko() + "|" + sestra.getLozinka() + "|" + sestra.getUloga() + "|"
						+ sestra.getPlata() + "|" + SluzbeZaSestru.toInt(sestra.getSluzba()) + "\n";
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
				GregorianCalendar termin = napraviDatumIVreme(split[3]);
				String soba = split[4];
				int statusInt = Integer.parseInt(split[5]);
				StatusPregleda status = StatusPregleda.fromInt(statusInt);
				Pacijent pacijentPregleda = new Pacijent();
				Lekar lekarPregleda = new Lekar();
				lekarPregleda = pronadjiLekara(jmbgLekara);
				pacijentPregleda = pronadjiPacijenta(jmbgPacijenta);
				Pregledi pregled = new Pregledi(id, pacijentPregleda, lekarPregleda, termin, soba, status);
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
				content += pregled.getID() + "|" + pregled.getPacijent().getJmbg() + "|" + pregled.getLekar().getJmbg()
						+ "|" + VremeUString(pregled.getTermin(), formatTermina) + "|" + pregled.getSoba() + "|"
						+ StatusPregleda.toInt(pregled.getStatus()) + "\n";
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
		int ID = Integer.parseInt(id);
		for (Pregledi pregled : pregledi) {
			if (ID == pregled.getID()) {
				return pregled;
			}
		}
		return null;
	}

	public ZdravstvenaKnjizica pronadjiKnjizicu(String jmbg) {
		for (ZdravstvenaKnjizica knjizica : knjizice) {
			if (jmbg.equals(knjizica.getJmbgPacijenta())) {
				return knjizica;
			}
		}
		return null;
	}

	public int pronadjiNajveciId() {
		int najveci = 0;
		for (Pregledi pregled : pregledi) {
			if (najveci < pregled.getID()) {
				najveci = pregled.getID();
			}
		}
		return najveci;
	}

	public int PronadjNajveciBr() {
		int najveci = 0;
		for (ZdravstvenaKnjizica knjizica : knjizice) {
			if (najveci < Integer.parseInt(knjizica.getBrojKnjizice())) {
				najveci = Integer.parseInt(knjizica.getBrojKnjizice());
			}
		}
		return najveci;
	}

	public GregorianCalendar napraviDatumIVreme(String s) {
		String[] split = s.split("\\ ");
		String[] datum = split[0].split("\\.");
		String[] vreme = split[1].split("\\:");
		int dan = Integer.parseInt(datum[0]);
		int mesec = Integer.parseInt(datum[1]);
		int godina = Integer.parseInt(datum[2]);
		int sat = Integer.parseInt(vreme[0]);
		int minute = Integer.parseInt(vreme[1]);
		GregorianCalendar termin = new GregorianCalendar(godina, mesec - 1, dan, sat, minute);
		return termin;
	}

	public GregorianCalendar SamoDatum(String s) {
		String[] vreme = s.split("\\.");
		int dan = Integer.parseInt(vreme[0]);
		int mesec = Integer.parseInt(vreme[1]);
		int godina = Integer.parseInt(vreme[2]);
		GregorianCalendar datum = new GregorianCalendar(godina, mesec - 1, dan);
		return datum;
	}

	public String VremeUString(GregorianCalendar termin, SimpleDateFormat f) {
		String s = f.format(termin.getTime());
		return s;
	}

	public ArrayList<String> LekariJmbg() {
		ArrayList<String> lista = new ArrayList<String>();
		for (Lekar lekar : lekari) {
			System.out.println(lekar.getJmbg());
			lista.add(lekar.getJmbg());
		}
		return lista;
	}

	public double napraviRacun(KategorijaOsiguranja kategorija) {
		double racun = 0;
		if (kategorija == KategorijaOsiguranja.Prva) {
			racun = 300;
		}
		if (kategorija == KategorijaOsiguranja.Druga) {
			racun = 50;
		}
		if (kategorija == KategorijaOsiguranja.Treca) {
			racun = 0;
		}
		return racun;
	}

	public ArrayList<Pregledi> pronadjiSvojePregledeLekar(String jmbg) {
		ArrayList<Pregledi> pregLekara = new ArrayList<Pregledi>();
		for (Pregledi pregled : pregledi) {
			if ((pregled.getLekar().getJmbg().equals(jmbg)) && ((pregled.getStatus() == StatusPregleda.Zakazan)
					|| (pregled.getStatus() == StatusPregleda.Otkazan)
					|| (pregled.getStatus() == StatusPregleda.Zavrsen))) {
				pregLekara.add(pregled);
			}
		}
		return pregLekara;
	}

	public ArrayList<Pregledi> pronadjiSvojePregledePacijent(String jmbg) {
		ArrayList<Pregledi> pregPacijenta = new ArrayList<Pregledi>();
		for (Pregledi pregled : pregledi) {
			if (pregled.getPacijent().getJmbg().equals(jmbg)) {
				pregPacijenta.add(pregled);
			}
		}
		return pregPacijenta;
	}

	public Osoba pronadjiKorisnicko(String korisnicko) {
		for (Osoba osoba : osobe) {
			if (osoba.getKorisnicko().equals(korisnicko)) {
				return osoba;
			}
		}
		return null;
	}

	public int pretvoriSveUMinute(String s) {
		String[] terminS = s.split("\\ ");
		String[] vremeS = terminS[1].split("\\:");
		int sat = Integer.parseInt(vremeS[0]);
		int min = Integer.parseInt(vremeS[1]);
		int vreme = (sat * 60) + min;
		return vreme;
	}

	public int samoSati(String s) {
		String[] termin = s.split("\\ ");
		int sat = Integer.parseInt(termin[1].split("\\:")[0]);
		return sat;
	}

	public boolean poredjenje(String s1, String s2) {
		String[] trazeniTermin = s1.split("\\.");
		String[] postojuciTermin = s2.split("\\.");
		int dan1 = Integer.parseInt(trazeniTermin[0]);
		int dan2 = Integer.parseInt(postojuciTermin[0]);
		int mesec1 = Integer.parseInt(trazeniTermin[1]);
		int mesec2 = Integer.parseInt(postojuciTermin[1]);
		int god1 = Integer.parseInt(trazeniTermin[2]);
		int god2 = Integer.parseInt(postojuciTermin[2]);
		if ((dan1 == dan2) && (mesec1 == mesec2) && (god1 == god2)) {
			return true;
		}
		return false;
	}
}