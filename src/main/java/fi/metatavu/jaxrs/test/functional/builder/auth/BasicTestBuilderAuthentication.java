package fi.metatavu.jaxrs.test.functional.builder.auth;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

public abstract class BasicTestBuilderAuthentication<C> extends AuthorizedTestBuilderAuthentication<C> {

  /**
   * Constructor
   *
   * @param testBuilder  testBuilder
   * @param authProvider auth provider
   */
  public BasicTestBuilderAuthentication(AbstractTestBuilder<C> testBuilder, BasicAuthProvider authProvider) {
    super(testBuilder, authProvider);
  }

}
