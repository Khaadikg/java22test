package com.example.demo.service;

import com.example.demo.common.Wrapper;
import com.example.demo.facotry.StatementTemplateFactory;
import com.example.demo.model.User;
import com.example.demo.templates.StatementTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final StatementTemplateFactory statementTemplateFactory;

    public List<User> findAll() {
        var STATEMENT = getStatement();
        return STATEMENT."select * from users";
    }

    public List<User> findByIdOrPassword(Wrapper<Object> object) {
        var STATEMENT = getStatement();

        return switch (object.getData()) {
            case Integer lon -> STATEMENT."select * from users u where u.id = \{lon}";
            case String str -> {
                str = STR."'\{str}'";
                yield STATEMENT."select * from users u where u.login like \{str}";
            }
        default -> List.of(new User());
        };
    }

    public User updateUserAndGet(Long id, Wrapper<String> newLogin) {
        var STATEMENT = getStatement();

        try {
            STATEMENT."""
            UPDATE users
            SET login = '\{newLogin.getData()}'
            WHERE id = \{id}""".getFirst();
        } catch (Exception e) {

        }

        return STATEMENT."select * from users where id = \{id}".getFirst();
    }

    private StatementTemplate<User> getStatement() {
        return statementTemplateFactory.createStatementTemplate(User.class);
    }
}
