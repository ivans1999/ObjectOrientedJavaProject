package uloge;

import uloge.Medicinskasestra;
import uloge.Osoba;

public class Medicinskasestra extends Osoba {

	private double plata;
	private String sluzba;

	public Medicinskasestra() {
		this.plata = 0;
		this.sluzba = "";
	}

	public Medicinskasestra(String ime, String prezime, String jmbg, String pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga, double plata, String sluzba) {
		super(ime, prezime, jmbg, pol, adresa, brTelefona, korisnicko, lozinka, uloga);
		this.plata = plata;
		this.sluzba = sluzba;
	}

	public Medicinskasestra(Medicinskasestra original) {
		super(original);
		this.plata = original.plata;
		this.sluzba = original.sluzba;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getSluzba() {
		return sluzba;
	}

	public void setSluzba(String sluzba) {
		this.sluzba = sluzba;
	}

	@Override
	public String toString() {
		return "Medicinska sestra " + super.toString() + "\nPlata: " + this.plata + "\nSluzba: " + this.sluzba;
	}
}