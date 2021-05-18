package com.location.aed.service;

import com.google.gson.*;
import com.location.aed.model.entity.Aed;
import com.location.aed.model.entity.Shelter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

@Component
@Log4j2
public class FileService {


    public ArrayList<Aed> createAedArray(){

        ArrayList<Aed> aed = new ArrayList<>();

        try{
            JsonObject jsonObject;
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new FileReader("C:/Users/user/IdeaProjects/aed/build/resources/main/static/data/aed.json"));

            jsonObject = jsonElement.getAsJsonObject();
            JsonArray aedArray = (JsonArray) jsonObject.get("DATA");

            Gson gson = new Gson();

            for(int i = 0; i < aedArray.size(); i++){
                JsonElement tempJson = jsonParser.parse(String.valueOf(aedArray.get(i)));

                aed.add(gson.fromJson(tempJson, Aed.class));
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return aed;

    }

    public ArrayList<Shelter> createShelterArray(){

        ArrayList<Shelter> shelters = new ArrayList<>();

        try{
            JsonObject jsonObject;
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new FileReader("C:/Users/user/IdeaProjects/aed/build/resources/main/static/data/shelter.json"));

            jsonObject = jsonElement.getAsJsonObject();
            JsonArray shelterArray = (JsonArray) jsonObject.get("DATA");

            Gson gson = new Gson();

            for(int i = 0; i < shelterArray.size(); i++){
                JsonElement tempJson = jsonParser.parse(String.valueOf(shelterArray.get(i)));
                shelters.add(gson.fromJson(tempJson, Shelter.class));
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return shelters;

    }
}
