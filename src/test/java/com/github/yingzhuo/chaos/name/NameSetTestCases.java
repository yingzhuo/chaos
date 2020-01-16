package com.github.yingzhuo.chaos.name;

import org.junit.Test;

public class NameSetTestCases {

    @Test
    public void test1() {
        NameSet.FAMILY_NAME_SET.forEach(System.out::println);
    }

}
