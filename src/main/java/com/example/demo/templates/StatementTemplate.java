package com.example.demo.templates;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatementTemplate<T> implements StringTemplate.Processor<List<T>, Throwable> {

    private final JdbcTemplate jdbcTemplate;

    @Setter
    private Class<T> type;

    @Override
    public List<T> process(StringTemplate tmpl) {
        String jsonStr = tmpl.interpolate();

        return jdbcTemplate.query(jsonStr, new ResultSetExtractor<List<T>>() {
            @SneakyThrows
            @Override
            public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
                return mapResultSetToObjectList(rs);
            }
        });
    }

    private List<T> mapResultSetToObjectList(ResultSet resultSet) throws Throwable {

        List<Field> fields = Arrays.stream(type.getDeclaredFields()).toList();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        List<T> list = new ArrayList<>();
        while (resultSet.next()) {

            T dto = type.getConstructor().newInstance();

            for (Field field : fields) {
                String name = field.getName();

                try {
                    String value = resultSet.getString(name);
                    field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            list.add(dto);
        }

        return list;
    }
}


