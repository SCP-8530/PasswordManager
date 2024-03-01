import java.util.Random;

public class PasswordNumerique extends Password {
    public PasswordNumerique(int taille) {
        super(taille);
    }

    @Override
    public String genererPassword() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.getTaille(); i++) {
            int nextInt = random.nextInt(10);
            stringBuilder.append(nextInt);
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean estValide(String password) {
        return password.matches("^\\d{" + getTaille() + "}$");
    }
}
