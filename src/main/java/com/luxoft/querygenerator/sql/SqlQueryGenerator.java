package com.luxoft.querygenerator.sql;

import com.luxoft.querygenerator.api.QueryGenerator;
import com.luxoft.querygenerator.domain.Column;
import com.luxoft.querygenerator.domain.Entity;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class SqlQueryGenerator implements QueryGenerator {
    @Override
    public String findAll(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("SELECT ");

        StringJoiner columnNames = new StringJoiner(", ");
        columnNames = getColumnNames(columnNames, clazz);
        query.append(columnNames);
        query.append(" FROM ");
        query.append(getTableName(clazz));
        query.append(";");
        // SELECT id, person_name, salary from persons;
        return query.toString();
    }


    //SELECT id FROM Person;
    @Override
    public String findById( Class<?> clazz)
    {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("SELECT ");

        String id = getEntityId(clazz);
        if (id == null){
           throw new IllegalArgumentException("Table don't have id column");
        }
        query.append(id);
        query.append(" FROM ");
        query.append(getTableName(clazz));
        query.append(";");

        return query.toString();
    }


    @Override
    public String insert(Class<?> clazz)
    {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("INSERT INTO ");

        StringJoiner columnNames = new StringJoiner(", ");
        columnNames = getColumnNames(columnNames, clazz);
        //INSERT INTO persons (id, name, salary) VALUES (value1, value2, value3);
        query.append(getTableName(clazz));
        query.append(" (");
        query.append(columnNames);
        query.append(")");
        query.append(" VALUES ");
        query.append("(");
        StringJoiner valuesNames = new StringJoiner(", ");
        for (int i = 1; i < 4; i++)
        {
          valuesNames.add(String.format("value%d", i));
        }
        query.append(valuesNames);
        query.append(")");
        query.append(";");
        // SELECT id, person_name, salary from persons;
        return query.toString();
    }

    //DELETE FROM persons WHERE id = value1;
    @Override
    public String remove(Class<?> clazz)
    {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("DELETE FROM ");

        StringJoiner columnNames = new StringJoiner(" ");

        String id = getEntityId(clazz);
        if (id == null){
            throw new IllegalArgumentException("Entity das not have id");
        }
        query.append(columnNames);
        query.append(" WHERE id = ");
        query.append(id);
        query.append(";");

        return query.toString();
    }

    // UPDATE persons SET id = id, name = value2, salary = value3;
    @Override
    public String update(Class<?> clazz)
    {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Annotation @Entity should be present");
        }
        StringBuilder query = new StringBuilder("UPDATE ");

        StringJoiner columnNames = new StringJoiner(", ");
        columnNames = getColumnNames(columnNames, clazz);
        query.append(getTableName(clazz));

        query.append(" SET id = ");
        String id = getEntityId(clazz);
        if (id == null){
            throw new IllegalArgumentException("Entity das not have id");
        }
        query.append(id);
        query.append(", ");

        query.append("name = ");
        String name = getEntityName(clazz);
        if (id == null){
            throw new IllegalArgumentException("Entity das not have name");
        }
        query.append(name);
        query.append(", ");

        query.append("salary = ");
        String salary = getEntitySalary(clazz);
        if (id == null){
            throw new IllegalArgumentException("Entity das not have salary");
        }
        query.append(salary);

        query.append(";");
        // SELECT id, person_name, salary from persons;
        return query.toString();
    }

    private StringJoiner getColumnNames(StringJoiner columnNames, Class<?> clazz) {
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                      : columnNameFromAnnotation;

                columnNames.add(columnName);
            }
        }
        return columnNames;
    }

    private String getTableName (Class<?> clazz){
        Entity clazzAnnotation = clazz.getAnnotation(Entity.class);
        return clazzAnnotation.table().isEmpty() ? clazz.getName() : clazzAnnotation.table();
    }

    private String getEntityId(Class<?> clazz) {
        String idValue = null;
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                      : columnNameFromAnnotation;
                if (Objects.equals(columnName, "id")){
                    idValue = columnName;
                }
            }
        }
        return idValue;
    }

    private String getEntityName(Class<?> clazz) {
        String idValue = null;
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                      : columnNameFromAnnotation;
                if (Objects.equals(columnName, "person_name")){
                    idValue = columnName;
                }
            }
        }
        return idValue;
    }

    private String getEntitySalary(Class<?> clazz) {
        String salaryValue = null;
        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnNameFromAnnotation = columnAnnotation.name();
                String columnName = columnNameFromAnnotation.isEmpty() ? declaredField.getName()
                      : columnNameFromAnnotation;
                if (Objects.equals(columnName, "salary")){
                    salaryValue = columnName;
                }
            }
        }
        return salaryValue;
    }

}
