package com.syf.test;

import io.goeasy.GoEasy;
import org.junit.Test;

public class TestGoEasy {
    @Test
    public void test1() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-b916cea53e2143dea744124bf7946d68");
        goEasy.publish("syf", "Hello, GoEasy!");
    }
}
