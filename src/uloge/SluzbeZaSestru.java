package uloge;


public enum SluzbeZaSestru {
	
	OpstaMedicina,
	ZdravstvenaZastitaDece,
	Stomatoloska,
	ZdravstvenaZastitaRadnika,
	PravniEkonomskiPoslovi,
	TehnoloskiPoslovi;
	
	public static SluzbeZaSestru fromInt(int a){
		switch(a) {
		case 1:
			return OpstaMedicina;
		case 2:
			return ZdravstvenaZastitaDece;
		case 3:
			return Stomatoloska;
		case 4:
			return ZdravstvenaZastitaRadnika;
		case 5:
			return PravniEkonomskiPoslovi;
		default:
			return TehnoloskiPoslovi;
		}
	}
	
	public static int toInt(SluzbeZaSestru sluzbe) {
		switch (sluzbe) {
		case OpstaMedicina:
			return 1;
		case ZdravstvenaZastitaDece:
			return 2;
		case Stomatoloska:
			return 3;
		case PravniEkonomskiPoslovi:
			return 4;
		case ZdravstvenaZastitaRadnika:
			return 5;
		default:
			return 6;
		}
	}
}