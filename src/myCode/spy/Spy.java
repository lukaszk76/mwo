public class Spy {
    /**
     * Główna pętla programu. Tworzy menu i na podstawie wyborów użytkownika tworzy odpowiednią instancję klasy szyfrującej a następnie wywołuje metodę szyfrującą lub deszyfrującą
     * @param args - nie wykorzystywane
     */
    public static void main(String[] args){

        Menu menu1 = new Menu("WYBÓR SZYFRU", new String[]{"Szyfr Cezara", "Szyfr Viegenera"});
        Menu menu2 = new Menu("WYBÓR DZIAŁANIA", new String[]{"Szyfrowanie", "Deszyfrowanie"});

        int selection1 = -1;
        Cipher cipher = null;

        while (selection1 != 0){
            selection1 = menu1.makeSelection();

            switch (selection1){
                case 1:{
                    cipher = new Cezar();
                    break;
                }
                case 2:{
                    cipher = new Viegener();
                    break;
                }
            }

            if (selection1 != 0) {
                int selection2 = -1;

                while (selection2 != 0) {
                    selection2 = menu2.makeSelection();

                    switch (selection2) {
                        case 1: {
                            System.out.println( "Zaszyfrowana wiadomość: " + cipher.code() );
                            break;
                        }
                        case 2: {
                            System.out.println( "Odszyfrowana wiadomość: " + cipher.decode() );
                            break;
                        }
                    }
                }
            }
        }
    }
}
