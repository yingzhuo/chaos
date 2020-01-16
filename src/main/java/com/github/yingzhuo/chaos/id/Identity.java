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

import java.io.Serializable;

/**
 * 身份证号码
 *
 * @author 应卓
 * @since 0.0.1
 */
public interface Identity extends Serializable {

    public static IdentityBuilder builder() {
        return IdentityBuilder.instance();
    }

    public String getValue();

    public int getCurrentAge();

    public Gender getGender();

    public Area getArea();

    public default boolean isMale() {
        return getGender() == Gender.MALE;
    }

    public default boolean isFemale() {
        return getGender() == Gender.FEMALE;
    }

}
