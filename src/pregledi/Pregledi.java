package pregledi;


public class Pregledi {
	
	private int ID;
	private String pacijent;
	private String lekar;
	private String termin;
	private String soba;
	private String status;
	
	public Pregledi() {
		this.ID=0;
		this.pacijent = "";
		this.lekar = "";
		this.termin = "";
		this.soba = "";
		this.status = "";
	}

	public Pregledi(int ID,String pacijent, String lekar, String termin, String soba, String status) {
		super();
		this.ID=ID;
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.status = status;
	}
	
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getSoba() {
		return soba;
	}

	public void setSoba(String soba) {
		this.soba = soba;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "\nPacijent: " + pacijent
				 + "\nLekar: " + lekar
				 + "\nTermin pregleda: " + termin
				 + "\nSoba u kojoj ce se odrzati pregled: " + soba
				 + "\nStatus: " + status;
	}
	
	

}