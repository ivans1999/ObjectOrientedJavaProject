package uloge;


public enum KategorijaOsiguranja {
	Prva,
	Druga,
	Treca;
	
	public static KategorijaOsiguranja fromInt(int a){
		switch(a) {
		case 1:
			return Prva;
		case 2:
			return Druga;
		default:
			return Treca;
		}
	}
	
	public static int toInt(KategorijaOsiguranja kategorija) {
		switch (kategorija) {
		case Prva:
			return 1;
		case Druga:
			return 2;
		default:
			return 3;
		}
	}

}
