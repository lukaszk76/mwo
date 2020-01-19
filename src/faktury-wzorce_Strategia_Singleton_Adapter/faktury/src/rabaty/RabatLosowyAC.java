package rabaty;

import rabatlosowy.LosowyRabat;

public class RabatLosowyAC extends LosowyRabat implements IObliczCenePoRabacie {
    @Override
    public double obliczCenePoRabacie(double cena) {
        return cena - cena * this.losujRabat();
    }
}
