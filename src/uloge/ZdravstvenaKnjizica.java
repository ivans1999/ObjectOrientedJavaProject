package uloge;

import java.util.GregorianCalendar;

public class ZdravstvenaKnjizica {
	
	private String brojKnjizice;
	private GregorianCalendar datumIsteka;
	private String jmbgPacijenta;
	private KategorijaOsiguranja kategorijaOsiguranja;
	
	public ZdravstvenaKnjizica() {
		this.brojKnjizice = "";
		this.datumIsteka = new GregorianCalendar();
		this.jmbgPacijenta = "";
		this.kategorijaOsiguranja = KategorijaOsiguranja.Prva;
	}

	public ZdravstvenaKnjizica(String brojKnjizice,GregorianCalendar datumIsteka,String jmbgPacijenta, KategorijaOsiguranja kategorijaOsiguranja) {
		super();
		this.brojKnjizice = brojKnjizice;
		this.datumIsteka = datumIsteka;
		this.jmbgPacijenta= jmbgPacijenta;
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}

	public String getJmbgPacijenta() {
		return jmbgPacijenta;
	}

	public void setJmbgPacijenta(String jmbgPacijenta) {
		this.jmbgPacijenta = jmbgPacijenta;
	}

	public String getBrojKnjizice() {
		return brojKnjizice;
	}

	public void setBrojKnjizice(String brojKnjizice) {
		this.brojKnjizice = brojKnjizice;
	}

	public GregorianCalendar getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(GregorianCalendar datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public KategorijaOsiguranja getKategorijaOsiguranja() {
		return kategorijaOsiguranja;
	}

	public void setKategorijaOsiguranja(KategorijaOsiguranja kategorijaOsiguranja) {
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}

	
	@Override
	public String toString() {
		return   "\nBroj knjizice: " + this.brojKnjizice
				 + "\nDatum isteka knjizice: " + this.datumIsteka
				 + "\nKategorija osiguranja: " + this.kategorijaOsiguranja;
	}

}
