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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author 应卓
 * @since 0.0.1
 */
public interface DateOfBirth extends Serializable, Comparable<DateOfBirth> {

    public boolean isLeapYear();

    public Date asDate();

    public LocalDate asLocalDate();

    public long asTime();

    public String toString(String pattern);

}
