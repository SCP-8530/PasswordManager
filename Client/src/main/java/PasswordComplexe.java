import java.util.Random;

public class PasswordComplexe extends Password{
    public PasswordComplexe(int taille) {
        super(taille);
    }

    @Override
    public String genererPassword() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String CharList = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890!$?&*.-_()<>";

        for (int i = 0; i < this.getTaille(); i++) {
            char nextChar = CharList.charAt(random.nextInt(CharList.length()));
            stringBuilder.append(nextChar);
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean estValide(String password) {
        return password.matches("^[a-zA-Z0-9!$?&*.\\-_()<>]{" + getTaille() + "}$");
    }
}
