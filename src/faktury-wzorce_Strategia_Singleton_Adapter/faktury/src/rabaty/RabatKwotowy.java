package rabaty;

public class RabatKwotowy implements IObliczCenePoRabacie {

    private double kwotaRabatu = 0.0;

    public double getKwotaRabatu() {
        return kwotaRabatu;
    }

    public RabatKwotowy(double kwotaRabatu) {
        this.kwotaRabatu = kwotaRabatu;
    }

    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena - this.getKwotaRabatu();
    }
}
