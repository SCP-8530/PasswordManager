import java.util.Random;

public class PasswordTextuel extends Password {
    public PasswordTextuel(int taille) {
        super(taille);
    }

    @Override
    public String genererPassword() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String CharList = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";

        for (int i = 0; i < this.getTaille(); i++) {
            char nextChar = CharList.charAt(random.nextInt(CharList.length()));
            stringBuilder.append(nextChar);
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean estValide(String password) {
        return password.matches("^[a-zA-Z]{" + getTaille() + "}$");
    }
}
