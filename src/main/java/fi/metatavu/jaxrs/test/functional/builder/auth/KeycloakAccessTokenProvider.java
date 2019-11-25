package fi.metatavu.jaxrs.test.functional.builder.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Keycloak access token provider
 * 
 * @author Antti Leppä
 */
public class KeycloakAccessTokenProvider implements AccessTokenProvider {
  
  private static final int EXPIRE_SLACK = 30;
  
  private String authServerUrl;
  private String realm;
  private String clientId;
  private String username;
  private String password;
  private String clientSecret;
  
  private String accessToken;
  private OffsetDateTime expires; 
  
  public KeycloakAccessTokenProvider(String authServerUrl, String realm, String clientId, String username, String password, String clientSecret) throws IOException {
    super();
    this.authServerUrl = authServerUrl;
    this.realm = realm;
    this.clientId = clientId;
    this.username = username;
    this.password = password;
    this.clientSecret = clientSecret;
    this.accessToken = null;
    this.expires = null;
  }

  @Override
  public String getAccessToken() throws IOException {
    if (accessToken != null && expires != null && expires.isAfter(OffsetDateTime.now())) {
      return accessToken;
    }
    
    Map<String, String> data = new HashMap<>();
    data.put("client_id", clientId);
    data.put("grant_type", "password");
    data.put("username", username);
    data.put("password", password);
    
    if (clientSecret != null) {
      data.put("client_secret", clientSecret);
    }
    
    URL url = URI.create(String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm)).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoInput(true);
    connection.setDoOutput(true);
    
    String postData = data.entrySet().stream()
      .map(entry -> String.format("%s=%s", urlEncode(entry.getKey()), urlEncode(entry.getValue())))
      .collect(Collectors.joining("&"));

    try (OutputStream out = connection.getOutputStream()) {
      out.write(postData.getBytes("UTF-8"));
    }
    
    connection.connect();
    
    try (InputStream in = connection.getInputStream()) {
      assertEquals(200, connection.getResponseCode());
      
      Map<String, Object> responseMap = readJsonMap(in);
      accessToken = (String) responseMap.get("access_token");
      Integer expiresIn = (Integer) responseMap.get("expires_in");
      
      this.expires = OffsetDateTime.now()
        .plus(expiresIn, ChronoUnit.SECONDS)
        .minus(EXPIRE_SLACK, ChronoUnit.SECONDS);
      
      assertNotNull(accessToken);
      
      return accessToken;      
    }
  }
  
  /**
   * Returns URL encoded string
   * 
   * @param string string
   * @return URL encoded string
   */
  private String urlEncode(String string) {
    try {
      return URLEncoder.encode(string, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return null;
    }
  }
  
  /**
   * Returns object mapper with default modules and settings
   * 
   * @return object mapper
   */
  private ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
    return objectMapper;
  }

  /**
   * Reads JSON src into Map
   * 
   * @param src input
   * @return map
   * @throws IOException throws IOException when there is error when reading the input 
   */
  private Map<String, Object> readJsonMap(InputStream src) throws IOException {
    return getObjectMapper().readValue(src, new TypeReference<Map<String, Object>>() {});
  }
}