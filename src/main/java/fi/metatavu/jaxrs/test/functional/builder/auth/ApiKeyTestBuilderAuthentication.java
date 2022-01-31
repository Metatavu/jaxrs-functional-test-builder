package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Default implementation for api key authentication provider
 *
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class ApiKeyTestBuilderAuthentication<C> extends AuthorizedTestBuilderAuthentication<C> {

  /**
   * Constructor
   *
   * @param testBuilder  testBuilder
   * @param authProvider auth provider
   */
  public ApiKeyTestBuilderAuthentication(AbstractTestBuilder<C> testBuilder, ApiKeyAuthProvider authProvider) {
    super(testBuilder, authProvider);
  }

}
