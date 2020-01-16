/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   ____ _
 *  / ___| |__   __ _  ___  ___
 * | |   | '_ \ / _` |/ _ \/ __|
 * | |___| | | | (_| | (_) \__ \
 *  \____|_| |_|\__,_|\___/|___/
 *
 *  https://github.com/yingzhuo/chaos
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.chaos.name;

import java.io.Serializable;

/**
 * @author 应卓
 * @since 0.0.1
 */
public interface Name extends Serializable, Comparable<Name> {

    public static NameBuilder builder() {
        return NameBuilder.instance();
    }

    public String getFamilyName();

    public String getGivenName();

    @Override
    default int compareTo(Name o) {
        return this.toString().compareTo(o.toString());
    }

}
