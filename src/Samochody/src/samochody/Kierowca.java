package samochody;

import java.util.ArrayList;

public class Kierowca{

	private long pesel;
	private String imie;
	private String nazwisko;
	private ArrayList<Samochod> samochody;

	public Kierowca(long pesel, String imie, String nazwisko) {
		this.pesel = pesel;
		this.imie = imie.trim();
		this.nazwisko = nazwisko.trim(); 
		samochody = new ArrayList<Samochod>();
	}

	public long getPesel() {
		return pesel;
	}

	public Samochod znajdzSamochod(String rejestracja) {
		for (Samochod s : samochody) {
			if (s.getRejestracja() == rejestracja)
				return s;
		}
		return null;
	}

	public String getImieINazwisko() {
		return imie + " " + nazwisko;
	}

	public void amortyzujSamochody() {
		for (Samochod s : samochody) {
			s.amortyzuj();
		}
	}

	public void dodajSamochod(Samochod samochod) {
		samochody.add(samochod);
	}
	
	public float obliczSredniaWartoscSamochodu()
	{
		float sumaWartosci = 0;
		for (Samochod s : samochody) {
			sumaWartosci += s.getWartosc();
		}
		if (sumaWartosci == 0) return 0;
		
		return sumaWartosci / samochody.size();
	}
	
	public void drukujSamochody() {
		for (Samochod samochod: samochody) {
			System.out.println(samochod.getRejestracja() + " " + samochod.getWartosc());
		}
	}
	
	public void usunSamochod(String rejestracja) {
		Samochod samochod = this.znajdzSamochod(rejestracja);
		if (samochod != null) {
			this.samochody.remove(samochod);
		}
	}
	
	public void przekazSamochod(String rejestracja, Kierowca kierowca) {
		Samochod samochod = this.znajdzSamochod(rejestracja);
		if (samochod != null) {
			kierowca.dodajSamochod(samochod);
			this.usunSamochod(rejestracja);
		}
		
	}
}
