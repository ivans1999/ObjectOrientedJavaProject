package pregledi;

public enum StatusPregleda {
	
	Zatrazen,
	Zakazan,
	Otkazan,
	Zavrsen;
	
	public static StatusPregleda fromInt(int a){
		switch(a) {
		case 1:
			return Zatrazen;
		case 2:
			return Zakazan;
		case 3:
			return Otkazan;
		default:
			return Zavrsen;
		}
	}
	
	public static int toInt(StatusPregleda status) {
		switch (status) {
		case Zatrazen:
			return 1;
		case Zakazan:
			return 2;
		case Otkazan:
			return 3;
		default:
			return 4;
		}
	}
}