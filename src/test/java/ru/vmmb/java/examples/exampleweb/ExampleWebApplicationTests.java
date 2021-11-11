package ru.vmmb.java.examples.exampleweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.lang.reflect.Method;


@SpringBootTest
class ExampleWebApplicationTests {

    @Test
    void contextLoads() {
        try {
            //Method m1 = Example1.class.getMethod("get");
               //Example1.class.getAnnotation()

            //Class<? extends Class> cls = Example1.class.getClass();
            //m1 = cls.getMethod("method1");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
