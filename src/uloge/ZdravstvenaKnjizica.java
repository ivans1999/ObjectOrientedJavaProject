package uloge;


public class ZdravstvenaKnjizica {
	
	private String brojKnjizice;
	private String datumIsteka;
	private String jmbgPacijenta;
	private int kategorijaOsiguranja;
	
	public ZdravstvenaKnjizica() {
		this.brojKnjizice = "";
		this.datumIsteka = "";
		this.jmbgPacijenta = "";
		this.kategorijaOsiguranja = 0;
	}

	public ZdravstvenaKnjizica(String brojKnjizice,String datumIsteka,String jmbgPacijenta, int kategorijaOsiguranja) {
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

	public String getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public int getKategorijaOsiguranja() {
		return kategorijaOsiguranja;
	}

	public void setKategorijaOsiguranja(int kategorijaOsiguranja) {
		this.kategorijaOsiguranja = kategorijaOsiguranja;
	}

	
	@Override
	public String toString() {
		return   "\nBroj knjizice: " + this.brojKnjizice
				 + "\nDatum isteka knjizice: " + this.datumIsteka
				 + "\nKategorija osiguranja: " + this.kategorijaOsiguranja;
	}

}
