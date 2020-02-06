package uloge;


public class Medicinskasestra extends Osoba {

	private double plata;
	private SluzbeZaSestru sluzba;

	public Medicinskasestra() {
		this.plata = 0;
		this.sluzba = SluzbeZaSestru.OpstaMedicina;
	}

	public Medicinskasestra(String ime, String prezime, String jmbg, Pol pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga, double plata, SluzbeZaSestru sluzba) {
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

	public SluzbeZaSestru getSluzba() {
		return sluzba;
	}

	public void setSluzba(SluzbeZaSestru sluzba) {
		this.sluzba = sluzba;
	}

	@Override
	public String toString() {
		return "Medicinska sestra " + super.toString() + "\nPlata: " + this.plata + "\nSluzba: " + this.sluzba;
	}
}