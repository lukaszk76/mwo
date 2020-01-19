package rabaty;

public class Konfiguracja {
    private static Konfiguracja instance = null;
    private IObliczCenePoRabacie rabat = null;

    private Konfiguracja() {
    }

    public static Konfiguracja getInstance() {
        if (instance == null) {
            instance = new Konfiguracja();
        }
        return instance;
    }

    public IObliczCenePoRabacie getAktualnyRabat() {
        if (rabat == null) {
            //rabat = new RabatProcentowy(getAktualnyProcentRabatu());
            //rabat = new RabatKwotowy(getAktualnaKwotaRabatu());
            //rabat = new RabatLosowyAI();
            rabat = new RabatLosowyAO();
        }

        return rabat;
    }

    private double getAktualnaKwotaRabatu() {
        return 5;
    }

    private double getAktualnyProcentRabatu() {
        return 10;
    }
}
