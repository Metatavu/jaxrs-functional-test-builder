package fi.metatavu.jaxrs.test.functional.builder;

/**
 * Interface describing a test builder resource.
 * 
 * @author Antti Leppä
 *
 * @param <T> resource type
 */
public interface TestBuilderResource <T> {
  
  /**
   * Adds closable into clean queue
   * 
   * @param t closeable
   * @return given instance
   */
  T addClosable(T t);
  
  /**
   * Cleans given resource
   * 
   * @param t resource
   * @throws Exception when cleaning fails
   */
  void clean(T t) throws Exception;

}
