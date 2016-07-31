package com.taptech.ttis;

import com.taptech.ttis.entity.Genre;
import com.taptech.ttis.entity.Movie;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by tap on 7/31/16.
 */
@Configuration
public class RepositoryConfiguration  extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Genre.class, Movie.class );
    }
}
