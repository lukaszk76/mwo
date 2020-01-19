package dokumenty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

import magazyn.Towar;
import rabaty.IObliczCenePoRabacie;
import rabaty.Konfiguracja;


public class Faktura {
    Date dataSprzedazy;
    String kontrahent;
    ArrayList<Pozycja> pozycje;
    double suma;
    IObliczCenePoRabacie rabat;

    public Faktura(Date dataSprzedazy, String kontrahent) {
        this.dataSprzedazy = dataSprzedazy;
        this.kontrahent = kontrahent;
        this.rabat = Konfiguracja.getInstance().getAktualnyRabat();
        pozycje = new ArrayList<Pozycja>();
        suma = 0;
    }

    public void dodajPozycje(Towar towar, double ilosc) {
        pozycje.add(new Pozycja(towar, ilosc));
        this.przeliczSume();
    }

    public double getSuma() {
        return suma;
    }

    public Date getDataSprzedazy() {
        return dataSprzedazy;
    }

    //jak sie zmieni cos na fakturze to trzeba wywolac te metode
    private void przeliczSume() {
        Iterator<Pozycja> iteratorPozycji = pozycje.iterator();
        Pozycja pozycja;
        suma = 0;
        while (iteratorPozycji.hasNext()) {
            pozycja = iteratorPozycji.next();
            suma += pozycja.getWartosc();
        }
        if (rabat != null) {
            suma = rabat.obliczCenePoRabacie(suma);
        }
    }

    public Iterator<Pozycja> getIteratorPozycji() {
        return pozycje.iterator();
    }

    public String getKontrahent() {
        return this.kontrahent;
    }


}
