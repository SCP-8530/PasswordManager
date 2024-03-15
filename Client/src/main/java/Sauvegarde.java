import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Sauvegarde {
    public void Enregistrement(Map<String,String> list_password) {
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
