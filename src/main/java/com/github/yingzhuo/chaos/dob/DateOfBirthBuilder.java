/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   ____ _
 *  / ___| |__   __ _  ___  ___
 * | |   | '_ \ / _` |/ _ \/ __|
 * | |___| | | | (_| | (_) \__ \
 *  \____|_| |_|\__,_|\___/|___/
 *
 *  https://github.com/yingzhuo/chaos
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.chaos.dob;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author 应卓
 * @since 0.0.1
 */
public final class DateOfBirthBuilder {

    private final Random random = new Random();

    private LocalDate start;
    private LocalDate end;

    public static DateOfBirthBuilder newInstance() {
        return new DateOfBirthBuilder();
    }

    public DateOfBirthBuilder startInclude(int year, int month, int dayOfMonth) {
        this.start = LocalDate.of(year, month, dayOfMonth);
        return this;
    }

    public DateOfBirthBuilder endExclude(int year, int month, int dayOfMonth) {
        this.end = LocalDate.of(year, month, dayOfMonth);
        return this;
    }

    public DateOfBirth build() {

        if (this.start == null) {
            this.start = LocalDate.of(1970, 1, 1);
        }

        if (this.end == null) {
            this.end = LocalDate.now();
        }

        if (this.start.compareTo(this.end) >= 0) {
            throw new IllegalArgumentException("start >= end");
        }

        int min = (int) start.toEpochDay();
        int max = (int) end.toEpochDay();
        long randomDay = min + random.nextInt(max - min);
        LocalDate date = LocalDate.ofEpochDay(randomDay);
        return new LocalDateDateOfBirth(date);
    }

    private DateOfBirthBuilder() {
    }

    // ----------------------------------------------------------------------------------------------------------------

    public static class LocalDateDateOfBirth implements DateOfBirth {

        private final LocalDate date;

        public LocalDateDateOfBirth(LocalDate date) {
            if (date == null) {
                throw new NullPointerException();
            }
            this.date = date;
        }

        @Override
        public boolean isLeapYear() {
            return date.isLeapYear();
        }

        @Override
        public Date asDate() {
            final ZoneId defaultZoneId = ZoneId.systemDefault();
            return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
        }

        @Override
        public LocalDate asLocalDate() {
            return this.date;
        }

        @Override
        public long asTime() {
            return asDate().getTime();
        }

        @Override
        public Calendar asCalendar() {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(asDate());
            return calendar;
        }

        @Override
        public int compareTo(DateOfBirth o) {
            return date.compareTo(o.asLocalDate());
        }

        @Override
        public String toString(String pattern) {
            return date.format(DateTimeFormatter.ofPattern(pattern));
        }

        @Override
        public String toString() {
            return date.toString();
        }
    }

}
