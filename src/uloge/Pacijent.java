package uloge;

import uloge.Osoba;
import uloge.Pacijent;
import uloge.ZdravstvenaKnjizica;

public class Pacijent extends Osoba {
	
	private String izabraniLekar;
	private ZdravstvenaKnjizica zdravstvena;
	
	public Pacijent() {
		this.izabraniLekar = "";
	}

	public Pacijent(String ime, String prezime, String jmbg, String pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga,String izabraniLekar) {
		super(ime,prezime,jmbg,pol,adresa,brTelefona,korisnicko,lozinka,uloga);
		this.izabraniLekar = izabraniLekar;
	}
	
	public Pacijent(Pacijent original) {
		super(original);
		this.izabraniLekar = original.izabraniLekar;
		this.zdravstvena = original.zdravstvena;
	}

	public String getIzabraniLekar() {
		return izabraniLekar;
	}

	public void setIzabraniLekar(String izabraniLekar) {
		this.izabraniLekar = izabraniLekar;
	}
	
	@Override
	public String toString() {
	   String s = "Pacijent " + super.toString() + 
				"\nIzabrani lekar: " + this.izabraniLekar;
		return s;
	}

}