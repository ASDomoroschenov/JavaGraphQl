package ru.sberp.springgraphql.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class TestUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static Map<?, ?> toMap(Object object) {
    return objectMapper.convertValue(object, Map.class);
  }
}
