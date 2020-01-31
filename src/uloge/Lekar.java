package uloge;

import uloge.SluzbeZaLekara;

public class Lekar extends Osoba {
	
	private double plata;
	private String specijalizacija;
	private SluzbeZaLekara sluzba;
	
	public Lekar() {
		this.plata = 0;
		this.specijalizacija = "";
		this.sluzba = SluzbeZaLekara.OpstaMedicina;
	}

	public Lekar(String ime, String prezime, String jmbg, Pol pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga,double plata, String specijalizacija, SluzbeZaLekara sluzba) {
		super(ime,prezime,jmbg,pol,adresa,brTelefona,korisnicko,lozinka,uloga);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
		this.sluzba = sluzba;
	}
	
	public Lekar(Lekar original) {
		super(original);
		this.plata = original.plata;
		this.specijalizacija = original.specijalizacija;
		this.sluzba = original.sluzba;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public SluzbeZaLekara getSluzba() {
		return sluzba;
	}

	public void setSluzba( SluzbeZaLekara sluzba) {
		this.sluzba = sluzba;
	}


	@Override
	public String toString() {
		String s = "Lekar " + super.toString() + 
				"\nPlata: " + this.plata + 
				"\nSpecijalizacija: " + this.specijalizacija +
				"\nSluzba: " + this.sluzba;
		return s;
	}
	
	

}

