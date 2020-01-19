package rabaty;

public class RabatProcentowy implements IObliczCenePoRabacie {

    private double procentRabatu = 0;

    public double getProcentRabatu() {
        return procentRabatu;
    }

    public RabatProcentowy(double procentRabatu) {
        this.procentRabatu = procentRabatu;
    }

    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena * (100.0 - this.getProcentRabatu()) / 100.0;
    }
}
