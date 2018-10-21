/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevsil.ghettocraps.resources;

import com.kevsil.ghettocraps.api.Die;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

/**
 *
 * @author ksilh
 */
@Path("/die")
@Produces(MediaType.APPLICATION_JSON)
public class DieResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public DieResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Die sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Die(counter.incrementAndGet(), value);
    }
}
