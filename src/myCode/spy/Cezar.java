import java.util.Scanner;

class Cezar extends Cipher {
    private int shift;
    private Scanner scanner = new Scanner( System.in );

    /**
     * zwraca literę przesuniętą o zadaną ilość pozycji w alfabecie uwzględniając ewentulane przeskoczenie na koniec/początek alfabetu w przypadku osiągnięcia A lub Z
     * @param letter litera wejściowa, którą należy zaszyforwać/odszyforwać - String
     * @param shift przesunięcie w alfabecie - int
     * @return litera przesunięta o zadaną ilość pozycji w stosunku do wejściowej - String
     */
    private String getLetter(String letter, int shift){
        int letterCode = (int)letter.toUpperCase().charAt(0) + shift;

        if (letterCode>90) {
            letterCode = letterCode - 26;
        }
        else if (letterCode<65) {
            letterCode = letterCode + 26;
        }
        return Character.toString((char) letterCode);
    }

    /**
     * prosi o podanie przesunięcia dla szyfru Cezara (o ile pozycji należy przesunąć litery z wiadomości) i zapamiętuje w prywatnym atrybucie klasy
     */
    private void setShift(){
        System.out.print("Wprowadź przesunięcie: ");
        while (true) {
            try {
                this.shift = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println( "Należy podać liczbę całkowitą!" );
            }
        }
    }

    @Override
    public String decode(){

        this.setMessage( "" );
        this.setCodedMessage();
        this.setShift();

        for (int a=0; a < this.getCodedMessage().length(); a++){
            String letter = this.getCodedMessage().substring(a, a+1);
            if (!letter.contains(" "))
                this.setMessage(this.getMessage() + getLetter(letter, -this.shift)) ;
            else
                this.setMessage(this.getMessage() + " ");
        }
        return this.getMessage();
    }

    @Override
    public String code(){

        this.setCodedMessage( "" );
        this.setMessage();
        this.setShift();

        for (int a=0; a < this.getMessage().length(); a++){
            String letter = this.getMessage().substring(a, a+1);
            if (!letter.contains(" "))
                this.setCodedMessage( this.getCodedMessage() + getLetter(letter, this.shift) );
            else
                this.setCodedMessage( this.getCodedMessage() + " " );
        }
        return this.getCodedMessage();
    }
}
