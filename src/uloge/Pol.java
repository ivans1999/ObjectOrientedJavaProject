package uloge;


public enum Pol {
	Zenski, 
	Muski;
	
	public static Pol fromInt(int a) {
		switch (a) {
		case 1:
			return Zenski;
		default:
			return Muski;
		}
	}
	
	public static int toInt(Pol pol) {
		switch (pol) {
		case Zenski:
			return 1;
		default:
			return 2;
		}
	}
	
}
