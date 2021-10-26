package com.luxoft.querygenerator.sql;

import com.luxoft.querygenerator.entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlQueryGeneratorTest {
    @Test
    public void testFindAllReturnValidQuery() {
        SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
        String expected = "SELECT id, person_name, salary FROM persons;";

        String actual = sqlQueryGenerator.findAll(Person.class);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindById(){
        SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
        String findById = "SELECT id FROM persons;";
        String actual = sqlQueryGenerator.findById(Person.class);
        assertEquals(actual, findById);

    }

    @Test
    public void testInsert(){
        SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
        String findById = "INSERT INTO persons (id, person_name, salary) VALUES (value1, value2, value3);";
        String actual = sqlQueryGenerator.insert(Person.class);
        assertEquals(actual, findById);

    }

    @Test void testRemove() {
        SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
        String findById = "DELETE FROM  WHERE id = id;";
        String actual = sqlQueryGenerator.remove(Person.class);
        assertEquals(actual, findById);
    }

   @Test void testUpdate() {
        SqlQueryGenerator sqlQueryGenerator = new SqlQueryGenerator();
        String findById = "UPDATE persons SET id = id, name = person_name, salary = salary;";
        String actual = sqlQueryGenerator.update(Person.class);
        assertEquals(actual, findById);
    }




}

/*
* public class Person {
    private int id;
    private String name;
    private double salary;
}

* */
