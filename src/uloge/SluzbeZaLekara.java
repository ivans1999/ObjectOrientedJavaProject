package uloge;


public enum SluzbeZaLekara {
	OpstaMedicina,
	ZdravstvenaZastitaDece,
	Stomatoloska,
	ZdravstvenaZastitaRadnika;
	
	public static SluzbeZaLekara fromInt(int a){
		switch(a) {
		case 1:
			return OpstaMedicina;
		case 2:
			return ZdravstvenaZastitaDece;
		case 3:
			return Stomatoloska;
		default:
			return ZdravstvenaZastitaRadnika;
		}
	}
	
	public static int toInt(SluzbeZaLekara sluzbe) {
		switch (sluzbe) {
		case OpstaMedicina:
			return 1;
		case ZdravstvenaZastitaDece:
			return 2;
		case Stomatoloska:
			return 3;
		default:
			return 4;
		}
	}

}
