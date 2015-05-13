package com.recruitiva.demo;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public HibernateAwareObjectMapper(EntityManagerFactory emf) {
        super();
        Hibernate4Module module = new Hibernate4Module(emf.unwrap(SessionFactory.class));

        module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        module.disable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
        registerModule(module);
    }

}
