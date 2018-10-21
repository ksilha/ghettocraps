package com.kevsil.ghettocraps;

import com.kevsil.ghettocraps.health.TemplateHealthCheck;
import com.kevsil.ghettocraps.resources.DieResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ghettocrapsApplication extends Application<ghettocrapsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ghettocrapsApplication().run(args);
    }

    @Override
    public String getName() {
        return "ghettocraps";
    }

    @Override
    public void initialize(final Bootstrap<ghettocrapsConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(final ghettocrapsConfiguration configuration,
            final Environment environment) {
        final DieResource resource = new DieResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
