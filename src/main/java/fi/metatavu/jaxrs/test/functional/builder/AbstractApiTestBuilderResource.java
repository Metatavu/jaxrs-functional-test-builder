package fi.metatavu.jaxrs.test.functional.builder;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Predicate;

import fi.metatavu.jaxrs.test.functional.builder.auth.AuthProvider;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Abstract base class for API test builder resources
 * 
 * @author Antti Leppä
 *
 * @param <T> type of resource
 * @param <A> type of API
 * @param <C> type of ApiClient
 * @param <P> Auth provider
 */
public abstract class AbstractApiTestBuilderResource <T, A, C, P extends AuthProvider> implements TestBuilderResource <T> {
  
  private AbstractTestBuilder<C, P> testBuilder;
  
  /**
   * Constructor
   * 
   * @param testBuilder testBuilder
   */
  public AbstractApiTestBuilderResource(AbstractTestBuilder<C, P> testBuilder) {
    this.testBuilder = testBuilder;
  }
  
  @Override
  public T addClosable(T t) {
    testBuilder.addClosable(new CloseableApiResource<>(this, t));
    return t;
  }
  
  /**
   * Returns API client
   * 
   * @return API client
   */
  protected abstract C getApiClient();
  
  /**
   * Removes a closable from clean queue
   * 
   * @param predicate filter predicate
   */
  public void removeCloseable(Predicate<Object> predicate) {
    testBuilder.removeCloseable(predicate);
  }
  
  /**
   * Builds API client
   * 
   * @return API client
   */
  protected abstract A getApi();

  /**
   * Returns API class from generic type arguments
   * 
   * @return API class
   */
  protected Class<A> getApiClass() {
    return getTypeArgument((ParameterizedType) getClass().getGenericSuperclass(), 1);
  }
  
  /**
   * Asserts that actual object equals expected object when both are serialized into JSON
   * 
   * @param expected expected
   * @param actual actual
   * @throws JSONException thrown when JSON serialization error occurs
   * @throws IOException thrown when IO Exception occurs
   */
  public void assertJsonsEqual(Object expected, Object actual) throws IOException, JSONException {
    JSONCompareResult compareResult = jsonCompare(expected, actual);
    assertTrue(compareResult.getMessage(), compareResult.passed());    
  }
  
  /**
   * Compares objects as serialized JSONs
   * 
   * @param expected expected
   * @param actual actual
   * @return comparison result
   * @throws JSONException when reading of JSON fails
   * @throws JsonProcessingException when processing of JSON fails
   */
  protected JSONCompareResult jsonCompare(Object expected, Object actual) throws JSONException, JsonProcessingException {
    CustomComparator customComparator = new CustomComparator(JSONCompareMode.LENIENT);
    return JSONCompare.compareJSON(toJSONString(expected), toJSONString(actual), customComparator);
  }  
  
  /**
   * Downloads binary data
   * 
   * @param authorization authorization header value 
   * @param url URL
   * @return downloaded data
   * @throws IOException thrown when downloading fails
   */
  protected byte[] getBinaryData(String authorization, URL url) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("Authorization", authorization);
    connection.setDoOutput(true);
    
    try (InputStream inputStream = connection.getInputStream()) {
      return IOUtils.toByteArray(inputStream);      
    }
  }
  
  /**
   * Serializes an object into JSON
   * 
   * @param object object
   * @return JSON string
   * @throws JsonProcessingException thrown when JSON serialization fails
   */
  private String toJSONString(Object object) throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    
    return getObjectMapper().writeValueAsString(object);
  }
  
  /**
   * Returns object mapper with default modules and settings
   * 
   * @return object mapper
   */
  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
    return objectMapper;
  }
  
  /**
   * Returns nth type argument from parameterized type
   * 
   * @param parameterizedType parameterized type
   * @param index index of argument
   * @return type argument
   */
  @SuppressWarnings("unchecked")
  private <R> Class<R> getTypeArgument(ParameterizedType parameterizedType, int index) {
    return (Class<R>) parameterizedType.getActualTypeArguments()[index];
  }
  
}