import java.util.Scanner;

public class Viegener extends Cipher{

    private String[] matrix = new String[26];
    private String key = "";
    private Scanner scanner = new Scanner( System.in );

    /**
     * Generuje i zapamiętuje w atrybucie klasy tablicę liter wykorzystywaną w szyfrze Viegenera do szyfrowania/deszyfrowania wiadomości
     */
    private void generateMatrix(){

        for (int line = 0; line < 26; line++) {
            StringBuilder text = new StringBuilder();
            int chr = 65+line;
            for (int i = 65; i < (65 + 26); i++) {
                text.append((char) chr);
                if (chr == 90) chr = 64;
                chr++;
            }
            this.matrix[line]= text.toString();
        }
    }

    /**
     * zwraca wiersz z tabeli do szyfru Viegenera zaczynający się na zadaną literę
     * @param a litera dla której należy podać wiersz z tablicy do szyfru Viegenera - String
     * @return linia z tabeli do szyfru Viegenera zaczynająca się na podaną literę - String
     */
    private String getRow(String a){
        String result = "";

        for (String line: this.matrix )
            if (line.substring(0,1).contains(a) )
                result = line;

        return result;
    }

    /**
     * zwraca kolumnę o podanym numerze z tabeli do szyfru Viegenera
     * @param a numer (kolejność) kolumny do pobrania z tabeli do szyfru Viegenera - int
     * @return wybrana kolumna z tabeli do szyfru Viegenera - String
     */
    private String getColumn(int a){
        String column = "";
        for (String line: this.matrix)
            column = column + line.substring(a, a + 1);

        return column;
    }

    /**
     * zwraca numer kolumny odpowiadającej pozycji podanej litery w pierwszym wierszu tabeli do szyfru Viegenera
     * @param b litera której pozycję w pierwszej linii tabeli należy ustalić - String
     * @return pozycja w pierwszym wierszu (numer kolumny) odpowiadająca podanej literze - int
     */
    private int selectColumn(String b){
        return this.matrix[0].indexOf(b);
    }

    /**
     * zwraca numer wiersza z tabeli do szyfru Viegenera odpowiadający pozycji podanej litery w danej kolumnie tej tabeli
     * @param column kolumna z tabeli do szyfru Viegenera w której należy szukać pozycji danej litery - String
     * @param b litera, której pozycji w podanej kolumnie poszukujemy - String
     * @return numer wiersz odpowiadający zadanej literze
     */
    private int selectRow(String column, String b){return column.indexOf(b);}

    /**
     * prosi o podanie klucza szyfrującego (wprowadzenie z klawiatury) i zapamiętuje go w prywtanym atrybucie
     */
    private void setKey(){
        System.out.print("Wprowadź klucz: ");
        this.key = multiplicateKey(scanner.nextLine().toUpperCase());
    }

    /**
     * powiela klucz tak aby miał długość równą szyfrowanej lub odszyfrowywanej wiadomości
     * @param rawKey klucz do powielenia - String
     * @return powielony klucz - String
     */
    private String multiplicateKey(String rawKey){
        StringBuilder multiplicatedKey = new StringBuilder();
        int keyPosition = 0;
        rawKey = rawKey.replace( " ", "" );
        String message;

        if (this.getMessage().length() > 0) {
            message = this.getMessage();
        } else if (this.getCodedMessage().length() > 0) message = this.getCodedMessage();
        else {
            System.out.println("Nie wprowadzono wiadomości!");
            return rawKey;
        }

        for (int i = 0; i < message.length(); i++){
            if (keyPosition == rawKey.length()) {
                keyPosition = 0;
            }
            if (message.substring(i, i+1).contains(" ")) {
                multiplicatedKey.append(" ");
            } else {
                multiplicatedKey.append(rawKey.substring(keyPosition, keyPosition + 1));
                keyPosition++;
            }
        }
        return multiplicatedKey.toString();
    }

    @Override
    public String decode() {

        this.setMessage( "" );
        this.setCodedMessage();
        generateMatrix();
        this.setKey();

        for (int a = 0; a < this.getCodedMessage().length(); a++) {
            String letter = this.getCodedMessage().substring( a, a + 1 );
            if (!letter.contains( " " )) {
                int row = selectRow( getColumn( selectColumn( this.key.substring( a, a + 1 ) ) ), letter );

                this.setMessage( this.getMessage() + matrix[row].substring( 0, 1 ) );
            } else
                this.setMessage( this.getMessage() + " " );
        }
        return this.getMessage();
    }

    @Override
    public String code(){

        this.setCodedMessage( "" );
        this.setMessage();
        generateMatrix();
        this.setKey();

        for (int a=0; a < this.getMessage().length(); a++){
            String letter = this.getMessage().substring(a, a+1);
            if (!letter.contains(" ")){
                int column = selectColumn(this.key.substring(a, a+1));
                this.setCodedMessage( this.getCodedMessage() + getRow(letter).substring(column, column + 1));
            } else
                this.setCodedMessage( this.getCodedMessage() + " ");
        }
        return this.getCodedMessage();
    }
}
