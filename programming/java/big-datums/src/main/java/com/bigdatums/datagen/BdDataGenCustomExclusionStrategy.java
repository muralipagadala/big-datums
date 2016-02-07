package com.bigdatums.datagen;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class BdDataGenCustomExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipField(FieldAttributes f) {
        if(f.getDeclaringClass() == BdPerson.class && (f.getName().equals("header") || f.getName().equals("r") || f.getName().equals("currId")))
            return true;
        else return false;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

}
