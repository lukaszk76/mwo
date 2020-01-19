package samochody;

import java.util.ArrayList;

public class ObslugaKierowcow{
	static ObslugaKierowcow instancja=new ObslugaKierowcow();	
	public static ObslugaKierowcow getInstance() {return instancja;}
	
	ArrayList<Kierowca> klienci;
	
	private ObslugaKierowcow()
	{
		klienci=new ArrayList<Kierowca>();
	}
	
	public Kierowca znajdzKierowce(long pesel)
	{
		for (Kierowca k : klienci)
		{
			if(k.getPesel()==pesel)
				return k;
		}
		return null;		
	}
	public void dodajKierowce(long pesel, String imie, String nazwisko)
	{
		Kierowca k=new Kierowca(pesel, imie, nazwisko);
		klienci.add(k);
	}
	public void amortyzujSamochodyKlientow()
	{
		for (Kierowca k : klienci)
		{
			k.amortyzujSamochody();
		}
	}
	public void wypiszKierowcow()
	{
		for (Kierowca k : klienci)
		{
			System.out.println(k.getImieINazwisko());
			k.drukujSamochody();
		}
	}
	
}
