package com.solvd.hospital.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JacksonUtil {

    private static final Logger logger =
            Logger.getLogger(JacksonUtil.class.getName());

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .findAndRegisterModules();

    private JacksonUtil() {}

    public static <T> T fromJson(File file, Class<T> clazz) {
        try {
            return mapper.readValue(file, clazz);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Failed to read JSON from file: " + file.getName(), e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Failed to parse JSON string", e);
            return null;
        }
    }

    public static <T> List<T> fromJsonList(File file, Class<T> clazz) {
        try {
            return mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Failed to read JSON list from file: " + file.getName(), e);
            return null;
        }
    }

    public static void toJson(Object object, File file) {
        try {
            mapper.writeValue(file, object);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Failed to write JSON to file: " + file.getName(), e);
        }
    }

    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Failed to convert object to JSON string", e);
            return null;
        }
    }
}