package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Default implementation for access token authentication provider
 *
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AccessTokenTestBuilderAuthentication<C> extends AuthorizedTestBuilderAuthentication<C, AccessTokenProvider> {

  /**
   * Constructor
   *
   * @param testBuilder  testBuilder
   * @param authProvider auth provider
   */
  public AccessTokenTestBuilderAuthentication(AbstractTestBuilder<C, AccessTokenProvider> testBuilder, AccessTokenProvider authProvider) {
    super(testBuilder, authProvider);
  }

}
