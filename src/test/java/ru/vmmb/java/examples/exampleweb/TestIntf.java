package ru.vmmb.java.examples.exampleweb;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public interface TestIntf {
    @Autowired
    public DataSource dataSource = null;
}
