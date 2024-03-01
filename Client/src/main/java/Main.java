public class Main {

    public static void main(String[] args) {

        Password p1 =  new PasswordComplexe(25);
        Password p2 =  new PasswordComplexe(25);
        Password p3 =  new PasswordComplexe(25);

        System.out.println(p1.getPassword());
        System.out.println(p2.getPassword());
        System.out.println(p3.getPassword());
        System.out.println(p1.estValide(p1.getPassword()));
    }

}
