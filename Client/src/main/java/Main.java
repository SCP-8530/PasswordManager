public class Main {

    public static void main(String[] args) {

        Password p =  new PasswordNumerique(10);

        System.out.println(p.getPassword());
        System.out.println(p.estValide("8495452654"));
    }

}
