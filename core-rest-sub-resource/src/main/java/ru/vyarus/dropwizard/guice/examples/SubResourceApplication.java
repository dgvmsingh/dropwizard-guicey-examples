package ru.vyarus.dropwizard.guice.examples;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import ru.vyarus.dropwizard.guice.GuiceyOptions;

/**
 * @author Vyacheslav Rusakov
 * @since 27.07.2017
 */
public class SubResourceApplication extends Application<Configuration> {

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                // bridge is required to inject guice service into hk managed sub resource
                .option(GuiceyOptions.UseHkBridge, true)
                // make sure service will not be created in both contexts (advanced validation for test)
                .strictScopeControl()
                .build());
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
    }
}
