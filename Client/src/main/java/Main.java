import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Password p1 =  new PasswordComplexe(25);
        Password p2 =  new PasswordComplexe(25);
        Password p3 =  new PasswordComplexe(25);

        System.out.println(p1.getPassword());
        System.out.println(p2.getPassword());
        System.out.println(p3.getPassword());
        System.out.println(p1.estValide(p1.getPassword()));

        Map<String,String> list_password = new HashMap<String,String>();

        list_password.put("Facebook",p1.getPassword());
        list_password.put("Google",p2.getPassword());
        list_password.put("Twitter",p3.getPassword());

        //json
        try {
            Gson gson = new Gson();
            String json = gson.toJson(list_password, Map.class);
            FileWriter writer = new FileWriter("list_password.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
