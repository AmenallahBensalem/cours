package com.amen.cours.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "intituleCr", types = { Cour.class })
public interface CourProjection {
    public String getIntituleCour();
}