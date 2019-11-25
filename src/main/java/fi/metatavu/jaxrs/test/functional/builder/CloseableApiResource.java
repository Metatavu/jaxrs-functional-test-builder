package fi.metatavu.jaxrs.test.functional.builder;

/**
 * Describes API closeable resource
 * 
 * @author Antti Leppä
 *
 * @param <T> entity class
 * @param <A> API class
 * @param <C> API client class
 */
public class CloseableApiResource<T, A, C> extends CloseableResource <T> {

  private AbstractApiTestBuilderResource<T, A, C> builder;

  /**
   * Constructor
   * 
   * @param builder resource builder
   * @param resource resource
   */
  public CloseableApiResource(AbstractApiTestBuilderResource<T, A, C> builder, T resource) {
    super(resource);
    this.builder = builder;
  }
  
  /**
   * Returns builder
   * 
   * @return builder
   */
  public AbstractApiTestBuilderResource<T, A, C> getBuilder() {
    return builder;
  }
  
  @Override
  public void close() throws Exception {
    builder.clean(getResource());
  }

}
