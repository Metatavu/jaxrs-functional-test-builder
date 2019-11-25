package fi.metatavu.jaxrs.test.functional.builder.auth;

import java.io.IOException;

/**
 * Interface describing an access token provider for tests
 * 
 * @author Antti Leppä
 */
public interface AccessTokenProvider {

  /**
   * Returns access token
   * 
   * @return access token
   * @throws IOException
   */
  public String getAccessToken() throws IOException;
  
}
