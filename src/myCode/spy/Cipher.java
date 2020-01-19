import java.util.Scanner;

public abstract class Cipher implements ICipher{
    private String message = "";
    private String codedMessage = "";
    private Scanner scanner = new Scanner(System.in);

    /**
     * zwraca niezaszyfrowaną/odkodowaną wiadomość przechowywyaną w prywatnym atrybucie klasy
     * @return niezaszyfrowana wiadomość - String
     */
    protected String getMessage() {
        return this.message;
    }

    /**
     * zwraca zaszyfrowaną wiadomość przechowywyaną w prywatnym atrybucie klasy
     * @return zaszyforwana wiadomość - String
     */
    protected String getCodedMessage() {
        return this.codedMessage;
    }

    @Override
    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public void setMessage(){
        System.out.print("Wprowadź wiadomość: ");
        this.setMessage( scanner.nextLine().toUpperCase() );
    }

    @Override
    public void setCodedMessage(String codedMessage){
        this.codedMessage = codedMessage;
    }

    @Override
    public void setCodedMessage(){
        System.out.print("Wprowadź zaszyfrowaną wiadomość: ");
        this.setCodedMessage( scanner.nextLine().toUpperCase() );
    }
}
