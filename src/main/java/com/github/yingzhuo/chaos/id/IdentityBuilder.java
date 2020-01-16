/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   ____ _
 *  / ___| |__   __ _  ___  ___
 * | |   | '_ \ / _` |/ _ \/ __|
 * | |___| | | | (_| | (_) \__ \
 *  \____|_| |_|\__,_|\___/|___/
 *
 *  https://github.com/yingzhuo/chaos
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.chaos.id;

import com.github.yingzhuo.chaos.Gender;
import com.github.yingzhuo.chaos.util.Pick;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author 应卓
 * @since 0.0.1
 */
public final class IdentityBuilder {

    private static final Random RANDOM = new Random();

    public static IdentityBuilder instance() {
        return new IdentityBuilder();
    }

    private final Set<Area> areas = new HashSet<>();
    private final Set<Gender> genders = new HashSet<>();
    private LocalDate dobStart;
    private LocalDate dobEnd;

    public IdentityBuilder area(Area... areas) {
        Collections.addAll(this.areas, areas);
        return this;
    }

    public IdentityBuilder dobStartInclude(int year, int month, int dayOfMonth) {
        this.dobStart = LocalDate.of(year, month, dayOfMonth);
        return this;
    }

    public IdentityBuilder dobEndExclude(int year, int month, int dayOfMonth) {
        this.dobEnd = LocalDate.of(year, month, dayOfMonth);
        return this;
    }

    public IdentityBuilder gender(Gender... genders) {
        Collections.addAll(this.genders, genders);
        return this;
    }

    public Identity build() {
        this.init();

        // 地区
        final Area pickedArea = Pick.from(this.areas);

        // 生日
        int min = (int) dobStart.toEpochDay();
        int max = (int) dobEnd.toEpochDay();
        long randomDay = min + RANDOM.nextInt(max - min);
        final LocalDate dob = LocalDate.ofEpochDay(randomDay);

        // 性别
        final Gender gender = Pick.from(this.genders);

        // 序列号
        final String serial = genSerial(gender);

        final StringBuilder sb = new StringBuilder();
        sb.append(Pick.fromMapValues(pickedArea.getAreaDict()));
        sb.append(dob.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        sb.append(serial);

        // 验证码
        final char checkCode = genCheckCode(sb);
        sb.append(checkCode);

        return new SimpleIdentity(sb.toString(), gender, dob, pickedArea);
    }

    private String genSerial(Gender gender) {
        if (gender == Gender.MALE) {
            return String.format("%02d1", RANDOM.nextInt(21));
        } else {
            return String.format("%02d2", RANDOM.nextInt(21));
        }
    }

    private char genCheckCode(StringBuilder chars) {
        int[] c = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] r = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars.charAt(i) + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    private void init() {

        if (this.dobStart == null) {
            this.dobStart = LocalDate.of(1970, 1, 1);
        }

        if (this.dobEnd == null) {
            this.dobEnd = LocalDate.now();
        }

        if (this.dobStart.compareTo(this.dobEnd) >= 0) {
            throw new IllegalArgumentException("start >= end");
        }

        if (this.genders.isEmpty()) {
            this.genders.addAll(EnumSet.allOf(Gender.class));
        }

        if (this.areas.isEmpty()) {
            this.areas.addAll(EnumSet.allOf(Area.class));
        }
    }

    private IdentityBuilder() {
    }

    // ---------------------------------------------------------------------------------------------------------------

    public static class SimpleIdentity implements Identity {

        private final String stringValue;
        private final Gender gender;
        private final LocalDate dob;
        private final Area area;

        public SimpleIdentity(String stringValue, Gender gender, LocalDate dob, Area area) {
            this.stringValue = stringValue;
            this.gender = gender;
            this.dob = dob;
            this.area = area;
        }

        @Override
        public String getValue() {
            return this.stringValue;
        }

        @Override
        public int getCurrentAge() {
            return Period.between(dob, LocalDate.now()).getYears();
        }

        @Override
        public Gender getGender() {
            return this.gender;
        }

        @Override
        public Area getArea() {
            return this.area;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

}
