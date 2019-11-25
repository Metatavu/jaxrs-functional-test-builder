package fi.metatavu.jaxrs.test.functional.builder.auth;

import java.io.IOException;

import fi.metatavu.jaxrs.test.functional.builder.AbstractTestBuilder;

/**
 * Abstract base class for all test builder authentication providers
 * 
 * @param <C> API Client class
 * @author Antti Leppä
 * @author Heikki Kurhinen
 */
public abstract class AbstractTestBuilderAuthentication <C> {

  private AbstractTestBuilder<C> testBuilder;
  
  /**
   * Constructor
   * 
   * @param testBuilder test builder
   */
  protected AbstractTestBuilderAuthentication(AbstractTestBuilder<C> testBuilder) {
    this.testBuilder = testBuilder;
  }
  
  /**
   * Returns test builder
   * 
   * @return test builder
   */
  protected AbstractTestBuilder<C> getTestBuilder() {
    return testBuilder;
  }

  /**
   * Creates an API client
   * 
   * @return an API client
   * @throws IOException thrown when authentication fails
   */
  protected abstract C createClient() throws IOException;
  
}
