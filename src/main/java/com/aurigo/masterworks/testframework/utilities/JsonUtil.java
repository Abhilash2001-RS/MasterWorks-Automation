package com.aurigo.masterworks.testframework.utilities;

import com.aurigo.masterworks.testframework.utilities.helper.FileHelper;
import com.aurigo.masterworks.testframework.webUI.constants.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    /**
     * De-Serialize JSON to Class object.
     *
     * @param classType    Corresponding Response class type.
     * @param jsonResponse JSON Response.
     * @param <ClassType>  Class Type.
     * @return Corresponding class type.
     */
    public static <ClassType> ClassType deSerialize(Class<ClassType> classType, String jsonResponse) {
        try {
            Gson gson = new Gson();

            return gson.fromJson(jsonResponse, (Type) classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Serialize Class object to JSON String.
     *
     * @param classType Class object with data.
     * @return JSON String.
     */
    public static String serialize(Object classType) {
        try {
            Gson gson = new Gson();

            return gson.toJson(classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to write a value to Json File
     *
     * @param fileName - The json where we want to write the data
     * @param key      - The key for the Json file
     * @param value    - The Value to be Written for the Json Key
     */
    public static void jsonWriter(String fileName, String key, String value) {
        try {

            var file = FileHelper.createFile(fileName, true);
            FileWriter writer = new FileWriter(file.getPath());
            JsonObject data = new JsonObject();
            data.put(key, value);
            Jsoner.serialize(data, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to Write More than a key-Value pair to a Json
     *
     * @param fileName - The json where we want to write the data
     * @param jsonMap  - The Map containing all the key-value Pairs
     */
    public static void jsonWriter(String fileName, HashMap<String, String> jsonMap) {
        try {
            var file = FileHelper.createFile(fileName, true);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getPath()));

            JsonObject data = new JsonObject();
            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                data.put(entry.getKey(), entry.getValue());
            }
            Jsoner.serialize(data, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param fileName - The json where we want to read the data
     * @param key      - The key for the Json file whose value value is to be retrieved
     * @return - A string value for the Key sent
     */
    public static String jsonReader(String fileName, String key) {
        try {
            File file = new File(Constants.DEFAULT_DOWNLOAD_PATH + "/" + fileName);
            Reader reader = Files.newBufferedReader(file.toPath());
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
            return (String) parser.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deserialize content from a Json file to a specific type
     *
     * @param type     -   Class object type,
     * @param filePath -   File Path.
     * @return -   Object of a specific type
     */
    public static <T> T deSerializeFromFile(Type type, String filePath) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader((Paths.get(filePath)));
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse JSON string to Map.
     *
     * @param jsonData -   JSON Data
     * @return - Map Object.
     */
    public static Map<String, Object> parseJSON(String jsonData) {

        ObjectMapper mapper = new ObjectMapper();
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
        Map<String, Object> data = null;
        try {
            data = mapper.readValue(jsonData, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Parse JSON string to List Map.
     *
     * @param jsonData -   JSON Data
     * @return - List Map Object.
     */
    public static List<Map<String, Object>> parseJSONToListObject(String jsonData) {

        ObjectMapper mapper = new ObjectMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Map.class);
        List<Map<String, Object>> data = null;
        try {
            data = mapper.readValue(jsonData, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return data;
    }

}
