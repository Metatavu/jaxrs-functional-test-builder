package fi.metatavu.jaxrs.test.functional.builder;

import fi.metatavu.jaxrs.test.functional.builder.auth.AccessTokenProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.AuthorizedTestBuilderAuthentication;
import fi.metatavu.jaxrs.test.functional.builder.auth.InvalidAccessTokenProvider;
import fi.metatavu.jaxrs.test.functional.builder.auth.NullAccessTokenProvider;

/**
 * Abstract test builder class for access token based builders
 *
 * @param <C> API Client class
 * @author Antti Leppä
 */
public abstract class AbstractAccessTokenTestBuilder<C> extends AbstractTestBuilder<C, AccessTokenProvider> {

  public AuthorizedTestBuilderAuthentication<C, AccessTokenProvider> invalid = createTestBuilderAuthentication(this, new InvalidAccessTokenProvider());

  public AuthorizedTestBuilderAuthentication<C, AccessTokenProvider> anonymous = createTestBuilderAuthentication(this, new NullAccessTokenProvider());

}
