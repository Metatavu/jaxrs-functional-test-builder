package fi.metatavu.jaxrs.test.functional.builder;

import fi.metatavu.jaxrs.test.functional.builder.auth.AuthProvider;

/**
 * Describes API closeable resource
 * 
 * @author Antti Leppä
 *
 * @param <T> entity class
 * @param <A> API class
 * @param <C> API client class
 * @param <P> Auth provider
 */
public class CloseableApiResource<T, A, C, P extends AuthProvider> extends CloseableResource <T> {

  private AbstractApiTestBuilderResource<T, A, C, P> builder;

  /**
   * Constructor
   * 
   * @param builder resource builder
   * @param resource resource
   */
  public CloseableApiResource(AbstractApiTestBuilderResource<T, A, C, P> builder, T resource) {
    super(resource);
    this.builder = builder;
  }
  
  /**
   * Returns builder
   * 
   * @return builder
   */
  public AbstractApiTestBuilderResource<T, A, C, P> getBuilder() {
    return builder;
  }
  
  @Override
  public void close() throws Exception {
    builder.clean(getResource());
  }

}
