package com.github.yingzhuo.chaos.dob;

import org.junit.Assert;
import org.junit.Test;

public class DateOfBirthTestCases {

    @Test
    public void test1() {
        DateOfBirth dob = DateOfBirthBuilder.newInstance()
                .startInclude(1982, 8, 19)
                .endExclude(1982, 8, 20)
                .build();
        Assert.assertNotNull(dob);
    }

}
