package com.hesine.manager.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-3
 */
public class UtilDateSerializer implements JsonSerializer<java.util.Date> {

    @Override
    public JsonElement serialize(java.util.Date src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        return new JsonPrimitive(src.getTime());
    }

}