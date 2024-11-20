package com.luv2code.springmvc.configuration;

import com.querydsl.jpa.impl.*;
import org.springframework.context.annotation.*;

import javax.persistence.*;

@Configuration
public class QueryDSLConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
