package com.github.yingzhuo.chaos.id;

import com.github.yingzhuo.chaos.Gender;
import lombok.val;
import org.junit.Test;

public class IdTestCases {

    @Test
    public void test1() {
        val id = Identity.builder()
                .area(Area.上海市, Area.北京市)
                .gender(Gender.MALE, Gender.FEMALE)
                .build();
        System.out.println(id);
        System.out.println(id.getName());
        System.out.println(id.getArea());
        System.out.println(id.getCurrentAge());
    }

}
