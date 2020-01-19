public interface ICipher {

    /**
     * odkodowuje zaszyfrowaną wiadomość
     * @return odszyfrowana wiadomość - String
     */
    String code();

    /**
     * Szyfruje wiadomość
     * @return zaszyfrowana wiadomość - String
     */
    String decode();

    /**
     * prosi o podanie niezaszyfrowanej wiadomści (wprowadzenie z klawiatury) a następnie zapamiętuje ją w prywatnym atrybucie
     */
    void setMessage();

    /**
     * zapamiętuje w prywatnym atrybucie niezaszyfrowaną wiadomość
     * @param message niezaszyforwana wiadomość - String
     */
    void setMessage( String message );

    /**
     * prosi o podanie zaszyfrowanej wiadomośći (wprowadzenie z klawiatury) a następnie zapamiętuje ją w prywatnym atrybucie
     */
    void setCodedMessage();

    /**
     * zapamiętuje w prywatnym atrybucie zaszyfrowaną wiadomość
     * @param codedMessage zaszyfrowana wiadomość - String
     */
    void setCodedMessage( String codedMessage );
}
