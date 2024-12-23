package Properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Properties;

public class ReadDataFromFile {

    public final static String DataPath = "src/test/resources/TestData/";


    public static String getPropertyValue(String fileName, String Key) throws IOException {

        Properties properties = new Properties();

        properties.load(new FileInputStream(DataPath + fileName + ".properties"));

        return properties.getProperty(Key);
    }


    public static String getJsonData(String fileName, String filed) {

        try {
            FileReader fileReader = new FileReader(DataPath + fileName + ".json");
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            return jsonElement.getAsJsonObject().get(filed).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
