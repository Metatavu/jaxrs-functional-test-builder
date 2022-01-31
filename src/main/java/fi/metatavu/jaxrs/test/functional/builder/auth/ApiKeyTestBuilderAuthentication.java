package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Tets
 * @param <C>
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
