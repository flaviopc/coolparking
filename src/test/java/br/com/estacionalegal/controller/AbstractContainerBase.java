package br.com.estacionalegal.controller;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Usada p testes em containers. Para usar é só estender ela com a classe de
 * teste
 */
public abstract class AbstractContainerBase {
    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;
    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:14-alpine");
        POSTGRE_SQL_CONTAINER.start();
        System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
    }
}
