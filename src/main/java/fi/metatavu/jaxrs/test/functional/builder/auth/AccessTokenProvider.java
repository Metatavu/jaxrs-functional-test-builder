package fi.metatavu.jaxrs.test.functional.builder.auth;

import java.io.IOException;

/**
 * Interface describing an access token provider for tests
 * 
 * @author Antti Leppä
 */
public interface AccessTokenProvider extends AuthProvider {

  /**
   * Returns access token
   * 
   * @return access token
   * @throws IOException when access token request fails
   */
  String getAccessToken() throws IOException;
  
}
