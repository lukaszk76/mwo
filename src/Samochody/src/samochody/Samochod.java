package samochody;

public class Samochod{
	private String rejestracja;
	private float wartosc;
	private float amortyzacja;
	
	public Samochod(String rejestracja, float wartosc, float amortyzacja)
	{
		this.rejestracja = rejestracja;
		this.wartosc = wartosc;
		this.amortyzacja = amortyzacja;
	}
	
	public String getRejestracja()
	{
		return rejestracja;
	}
	public float getWartosc()
	{
		return this.wartosc;
	}
	public void amortyzuj()
	{
		this.wartosc -= this.wartosc / (100/this.amortyzacja);
	}
	
}
