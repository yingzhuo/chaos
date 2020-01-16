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

import com.github.yingzhuo.chaos.Gender;
import com.github.yingzhuo.chaos.util.Pick;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 0.0.1
 */
public final class NameBuilder {

    public static NameBuilder instance() {
        return new NameBuilder();
    }

    private final Set<Gender> genders = new HashSet<>();

    public NameBuilder gender(Gender... genders) {
        Collections.addAll(this.genders, genders);
        return this;
    }

    public Name build() {

        if (genders.isEmpty()) {
            genders.addAll(EnumSet.allOf(Gender.class));
        }

        String fn = Pick.from(NamePool.FAMILY_NAME_SET);
        Gender g = Pick.from((genders));
        if (g == Gender.MALE) {
            return new SimpleName(fn, Pick.from((NamePool.GIVEN_NAME_SET_MALE)));
        } else {
            return new SimpleName(fn, Pick.from((NamePool.GIVEN_NAME_SET_FEMALE)));
        }
    }

    private NameBuilder() {
    }

    // ----------------------------------------------------------------------------------------------------------------

    public static class SimpleName implements Name {

        private final String familyName;
        private final String givenName;

        public SimpleName(String familyName, String givenName) {
            this.familyName = familyName;
            this.givenName = givenName;
        }

        @Override
        public String getFamilyName() {
            return familyName;
        }

        @Override
        public String getGivenName() {
            return givenName;
        }

        @Override
        public String toString() {
            return familyName + givenName;
        }
    }

}
