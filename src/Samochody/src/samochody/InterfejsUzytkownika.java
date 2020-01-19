package samochody;

public class InterfejsUzytkownika {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObslugaKierowcow ok=ObslugaKierowcow.getInstance();
		
		ok.dodajKierowce(12121234567L, "Jan", "Kowalski");
		ok.dodajKierowce(12121234234L, "Maria", "Nowak");
		
		Kierowca k1=ok.znajdzKierowce(12121234567L);
		Kierowca k2=ok.znajdzKierowce(12121234234L);
		Samochod s1=new Samochod("KR1345",20000,10);
		Samochod s2=new Samochod("KCH3343",14000,12);
		Samochod s3=new Samochod("SK8849",30000,7);
		Samochod s4=new Samochod("GD8236",71000,12);
		k1.dodajSamochod(s1);
		k1.dodajSamochod(s2);
		k2.dodajSamochod(s3);
		k2.dodajSamochod(s4);
		Samochod kk1=k1.znajdzSamochod("KR1345");

		kk1.amortyzuj();
		
		ok.amortyzujSamochodyKlientow();
		
		ok.wypiszKierowcow();
		


	}

}
