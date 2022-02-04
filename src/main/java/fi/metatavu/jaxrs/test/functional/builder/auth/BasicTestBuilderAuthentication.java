package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Default implementation for basic authentication provider
 *
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class BasicTestBuilderAuthentication<C> extends AuthorizedTestBuilderAuthentication<C, BasicAuthProvider> {

  /**
   * Constructor
   *
   * @param testBuilder  testBuilder
   * @param authProvider auth provider
   */
  public BasicTestBuilderAuthentication(AbstractTestBuilder<C, BasicAuthProvider> testBuilder, BasicAuthProvider authProvider) {
    super(testBuilder, authProvider);
  }

}
