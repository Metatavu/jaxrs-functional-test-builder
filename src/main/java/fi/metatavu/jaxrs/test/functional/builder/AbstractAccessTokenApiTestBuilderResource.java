package fi.metatavu.jaxrs.test.functional.builder;

import fi.metatavu.jaxrs.test.functional.builder.auth.AccessTokenProvider;

/**
 * Abstract base class for access token authenticated API test builder resources
 *
 * @author Antti Leppä
 *
 * @param <T> type of resource
 * @param <A> type of API
 * @param <C> type of ApiClient
 */
public abstract class AbstractAccessTokenApiTestBuilderResource<T, A, C> extends AbstractApiTestBuilderResource<T, A, C, AccessTokenProvider> {

  /**
   * Constructor
   *
   * @param testBuilder testBuilder
   */
  public AbstractAccessTokenApiTestBuilderResource(AbstractTestBuilder<C, AccessTokenProvider> testBuilder) {
    super(testBuilder);
  }

}
