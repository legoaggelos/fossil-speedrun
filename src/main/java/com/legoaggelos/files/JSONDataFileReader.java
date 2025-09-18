package com.legoaggelos.files;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.legoaggelos.FASpeedrun.MOD_ID;

public class JSONDataFileReader {
    private final ArrayList<JsonObject> jsonObjects = new ArrayList<>();
    private final ArrayList<BufferedReader> jsonFiles =  new ArrayList<>();
    private final ArrayList<String> jsonFileNames;
    private String folderLocation = "data/"+MOD_ID.replace("-","");

    public JSONDataFileReader(String folderLocation, List<String> jsonFileNames) {
        this.folderLocation += folderLocation;
        this.jsonFileNames = new ArrayList<>();
        this.jsonFileNames.addAll(jsonFileNames);
    }
    public String getFullLocation(String jsonFileName) {
        if (folderLocation.endsWith("/")) {
            folderLocation = folderLocation.substring(0, folderLocation.length()-1);
        }
        if (jsonFileName.startsWith("/")) {
            jsonFileName = jsonFileName.substring(1);
        }
        return folderLocation + "/" +jsonFileName;
    }
    private void parseFile(String fileName) {
        fileName = getFullLocation(fileName);
        System.out.println(fileName);
        BufferedReader readIn = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream(fileName)), StandardCharsets.UTF_8));
        StringBuilder jsonString = new StringBuilder();
        readIn.lines().forEach(jsonString::append);
        jsonFiles.add(readIn);
        jsonObjects.add(JsonParser.parseString(jsonString.toString()).getAsJsonObject());
    }
    public void parseFiles() {
        for (String fileName : jsonFileNames) {
            parseFile(fileName);
        }
    }
    public ArrayList<JsonObject> getJSONData() {
        if (jsonObjects.isEmpty()) {
            parseFiles();
        }
        return jsonObjects;
    }
}
