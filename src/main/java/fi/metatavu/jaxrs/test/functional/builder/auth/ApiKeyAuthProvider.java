package fi.metatavu.jaxrs.test.functional.builder.auth;

/**
 * Default implementation of api key based test builder authentication provider
 *
 * @author Antti Leppä
 */
public class ApiKeyAuthProvider implements AuthProvider {

  private final String apiKey;

  public ApiKeyAuthProvider(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Returns api key
   *
   * @return api key
   */
  public String getApiKey() {
    return apiKey;
  }
  
}
