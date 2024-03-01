public abstract class Password {
    //propriete
    private int taille;
    private String password = "";

    //construct
    public Password(int taille) {
        this.taille = taille;
        this.password = this.genererPassword();
    }

    //get-set
    public int getTaille() { return taille; }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        if (this.estValide(password)) this.password = password;
    }

    //methode
    public abstract String genererPassword();

    public abstract boolean estValide(String password);
}
