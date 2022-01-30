package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Default implementation of test builder authentication provider
 * 
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AuthorizedTestBuilderAuthentication <C> extends AbstractTestBuilderAuthentication <C> {

  private final AuthProvider authProvider;

  /**
   * Constructor
   * 
   * @param testBuilder testBuilder
   * @param authProvider auth provider
   */
  public AuthorizedTestBuilderAuthentication(AbstractTestBuilder<C> testBuilder, AuthProvider authProvider) {
    super(testBuilder);
    this.authProvider = authProvider;
  }

  @Override
  protected C createClient() {
    return createClient(authProvider);
  }

  /**
   * Creates ApiClient authenticated by the given auth provider
   *
   * @param authProvider auth provider
   * @return ApiClient authenticated by the given auth provider
   */
  protected abstract C createClient(AuthProvider authProvider);

}
