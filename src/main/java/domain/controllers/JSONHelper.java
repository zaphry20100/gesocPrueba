package domain.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class JSONHelper<T> {

    static ObjectMapper mapper;

    public String convertirAJson(T object) {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        }
        String result = "JSONHelper: NoValue";
        try {
            result = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public String convertirAJsonPrettyPlease(T object) {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
        }
        String result = "JSONHelper: NoValue";
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public String convertirListaAJson(List<T> list) {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//            mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        }
        String result = "JSONHelper: NoValue";
        try {
            result = mapper.writeValueAsString(list);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
