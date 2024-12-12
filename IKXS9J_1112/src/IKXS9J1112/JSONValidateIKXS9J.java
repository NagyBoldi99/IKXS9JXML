package IKXS9J1112;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;


public class JSONValidateIKXS9J {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("./IKXS9J_1112/JSONParseIKXS9J/orarendIKXS9J.json"));
            String rawSchemaString = Files.readString(Paths.get("./IKXS9J_1112/JSONParseIKXS9J/orarendIKXS9JSchema.json"));
            JSONObject jsonObject = new JSONObject(content);
            JSONObject rawSchema = new JSONObject(rawSchemaString);
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(jsonObject);
            System.out.println("JSON is valid");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}