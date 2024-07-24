package com.example.demo.service;

import com.example.demo.facotry.StatementTemplateFactory;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final StatementTemplateFactory statementTemplateFactory;

    public List<User> findAll() {
        var STATEMENT = statementTemplateFactory.createStatementTemplate(User.class);
        return STATEMENT."select * from users";
    }
}
