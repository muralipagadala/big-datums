package com.bigdatums.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonExamples {

    public static void JsonSimpleExample(){
        //add key value pairs to Json
        JSONObject obj = new JSONObject();
        obj.put("first_name", "Beyonce");
        obj.put("last_name", "Knowles");
        obj.put("birth_date", "1981-09-04");
        obj.put("records_sold", 200000000);

        //add array to Json
        JSONArray list = new JSONArray();
        list.add("Dangerously in Love");
        list.add("B'Day");
        list.add("I Am... Sasha Fierce");
        list.add("4");
        list.add("Beyonce");
        obj.put("albums", list);

        System.out.println(obj.toJSONString());
    }

    public static void main(String[] args){
        JsonSimpleExample();
    }

}
