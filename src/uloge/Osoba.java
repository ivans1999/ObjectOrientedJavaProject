package uloge;

import uloge.Osoba;

public abstract class Osoba {
	
	private String ime;
	private String prezime;
	private String jmbg;
	private String pol;
	private String adresa;
	private String brTelefona;
	private String korisnicko;
	private String lozinka;
	private String uloga;
	
	public Osoba() {
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.pol = "";
		this.adresa = "";
		this.brTelefona = "";
		this.korisnicko = "";
		this.lozinka = "";
		this.uloga = "";
	}

	public Osoba(String ime, String prezime, String jmbg, String pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.korisnicko = korisnicko;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}

	public Osoba(Osoba original) {
		this.ime =original.ime;
		this.prezime = original.prezime;
		this.jmbg = original.jmbg;
		this.pol = original.pol;
		this.adresa = original.adresa;
		this.brTelefona = original.brTelefona;
		this.korisnicko = original.korisnicko;
		this.lozinka = original.lozinka;
		this.uloga = original.uloga;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public String getKorisnicko() {
		return korisnicko;
	}

	public void setKorisnicko(String korisnicko) {
		this.korisnicko = korisnicko;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "\nIme: " + ime
				 + "\nPrezime: " + prezime
				 + "\nJMBG: " + jmbg
				 + "\nPol: " + pol
				 + "\nAdresa: " + adresa
				 + "\nBroj telefona: " + brTelefona
				 + "\nKorisnicko: " + korisnicko
				 + "\nLozinka: " + lozinka
				 + "\nUloga: " + uloga;
	}
	
	

}