package uloge;

public class Pacijent extends Osoba {
	
	private Lekar izabraniLekar;
	private ZdravstvenaKnjizica zdravstvena;
	
	public Pacijent() {
		this.izabraniLekar = new Lekar();
	}

	public Pacijent(String ime, String prezime, String jmbg, Pol pol, String adresa, String brTelefona,
			String korisnicko, String lozinka, String uloga,Lekar izabraniLekar) {
		super(ime,prezime,jmbg,pol,adresa,brTelefona,korisnicko,lozinka,uloga);
		this.izabraniLekar = izabraniLekar;
	}
	
	public Pacijent(Pacijent original) {
		super(original);
		this.izabraniLekar = original.izabraniLekar;
		this.zdravstvena = original.zdravstvena;
	}

	public Lekar getIzabraniLekar() {
		return izabraniLekar;
	}

	public void setIzabraniLekar(Lekar izabraniLekar) {
		this.izabraniLekar = izabraniLekar;
	}
	
	@Override
	public String toString() {
	   String s = "Pacijent " + super.toString() + 
				"\nIzabrani lekar: " + this.izabraniLekar;
		return s;
	}

}