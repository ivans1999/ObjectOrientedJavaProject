package pregledi;

import java.util.GregorianCalendar;

import uloge.Lekar;
import uloge.Pacijent;

public class Pregledi {
	
	private int ID;
	private Pacijent pacijent;
	private Lekar lekar;
	private GregorianCalendar termin;
	private String soba;
	private StatusPregleda status;
	
	public Pregledi() {
		this.ID=0;
		this.pacijent = new Pacijent();
		this.lekar = new Lekar();
		this.termin = new GregorianCalendar();
		this.soba = "";
		this.status = StatusPregleda.Zatrazen;
	}

	public Pregledi(int ID,Pacijent pacijent, Lekar lekar, GregorianCalendar termin, String soba, StatusPregleda status) {
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

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public GregorianCalendar getTermin() {
		return termin;
	}

	public void setTermin(GregorianCalendar termin) {
		this.termin = termin;
	}

	public String getSoba() {
		return soba;
	}

	public void setSoba(String soba) {
		this.soba = soba;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
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