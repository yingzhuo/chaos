/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *   ____ _
 *  / ___| |__   __ _  ___  ___
 * | |   | '_ \ / _` |/ _ \/ __|
 * | |___| | | | (_| | (_) \__ \
 *  \____|_| |_|\__,_|\___/|___/
 *
 *  https://github.com/yingzhuo/chaos
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.chaos.util;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

/**
 * @author 应卓
 * @since 0.0.1
 */
public final class Pick {

    private static final Random RANDOM = new Random();

    public static <E> E from(Collection<E> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        } else if (collection.size() == 1) {
            return collection.iterator().next();
        } else {
            return collection.stream().skip(RANDOM.nextInt(collection.size())).findFirst().orElse(null);
        }
    }

    public static <K, V> K fromMapKeys(Map<K, V> map) {
        return from(map.keySet());
    }

    public static <K, V> V fromMapValues(Map<K, V> map) {
        return from(map.values());
    }

    private Pick() {
    }
}
