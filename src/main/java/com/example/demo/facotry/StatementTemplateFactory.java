package com.example.demo.facotry;

import com.example.demo.templates.StatementTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatementTemplateFactory {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatementTemplateFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> StatementTemplate<T> createStatementTemplate(Class<T> type) {
        StatementTemplate<T> statementTemplate = new StatementTemplate<>(jdbcTemplate);
        statementTemplate.setType(type);
        return statementTemplate;
    }
}

