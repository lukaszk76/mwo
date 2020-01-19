package rabaty;

import rabatlosowy.LosowyRabat;

public class RabatLosowyAO implements IObliczCenePoRabacie {
    LosowyRabat losowyRabat;

    public RabatLosowyAO() {
        losowyRabat = new LosowyRabat();
    }

    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena - cena*this.losowyRabat.losujRabat();
    }
}
