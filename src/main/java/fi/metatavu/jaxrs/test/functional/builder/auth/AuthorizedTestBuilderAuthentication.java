package fi.metatavu.jaxrs.test.functional.builder.auth;

import java.io.IOException;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Default implementation of test builder authentication provider
 * 
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AuthorizedTestBuilderAuthentication <C> extends AbstractTestBuilderAuthentication <C> {
  
  
  private AccessTokenProvider accessTokenProvider;

  /**
   * Constructor
   * 
   * @param testBuilder testBuilder
   * @param accessTokenProvider access token builder
   */
  public AuthorizedTestBuilderAuthentication(AbstractTestBuilder<C> testBuilder, AccessTokenProvider accessTokenProvider) {
    super(testBuilder);
    this.accessTokenProvider = accessTokenProvider;
  }
  
  /** 
   * Creates ApiClient authenticated by access token provided by the access token provider
   * 
   * @return ApiClient API client
   * @throws IOException thrown when access token generation fails
   */
  @Override
  protected C createClient() throws IOException {
    String accessToken = accessTokenProvider != null ? accessTokenProvider.getAccessToken() : null;
    return createClient(accessToken);
  }

  /**
   * Creates ApiClient authenticated by the given access token
   *  
   * @param accessToken access token
   * @return ApiClient authenticated by the given access token
   */
  protected abstract C createClient(String accessToken);
  
}
