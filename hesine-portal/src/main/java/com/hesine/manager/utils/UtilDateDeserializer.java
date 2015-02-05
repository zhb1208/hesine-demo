package com.hesine.manager.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-3
 */
public class UtilDateDeserializer implements JsonDeserializer<java.util.Date> {

@Override
public java.util.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
        }
}