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
 * @author 应卓
 * @since 0.0.1
 */
public interface Identity extends Serializable {

    public String getValue();

    public int getCurrentAge();

    public Gender getGender();

}
