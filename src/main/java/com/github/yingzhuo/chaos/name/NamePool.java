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

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 应卓
 * @since 0.0.1
 */
final class NamePool {

    public static final Set<String> FAMILY_NAME_SET;

    public static final Set<String> GIVEN_NAME_SET_MALE;

    public static final Set<String> GIVEN_NAME_SET_FEMALE;

    static {
        try {
            final Set<String> familyNameSet = new HashSet<>();
            final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/family-name.txt");

            if (inputStream == null) {
                throw new IOException();
            }

            final InputStreamReader isReader = new InputStreamReader(inputStream);
            final BufferedReader reader = new BufferedReader(isReader);

            String str;
            while ((str = reader.readLine()) != null) {
                familyNameSet.addAll(Arrays.asList(str.split(",")));
            }
            FAMILY_NAME_SET = Collections.unmodifiableSet(familyNameSet);

            close(reader);
            close(isReader);
            close(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static {
        try {
            final Set<String> givenNameSet = new HashSet<>();
            final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/given-name-male.txt");

            if (inputStream == null) {
                throw new IOException();
            }

            final InputStreamReader isReader = new InputStreamReader(inputStream);
            final BufferedReader reader = new BufferedReader(isReader);

            String str;
            while ((str = reader.readLine()) != null) {
                givenNameSet.addAll(Arrays.asList(str.split(",")));
            }

            GIVEN_NAME_SET_MALE = Collections.unmodifiableSet(givenNameSet);

            close(reader);
            close(isReader);
            close(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static {
        try {
            final Set<String> givenNameSet = new HashSet<>();
            final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/given-name-female.txt");

            if (inputStream == null) {
                throw new IOException();
            }

            final InputStreamReader isReader = new InputStreamReader(inputStream);
            final BufferedReader reader = new BufferedReader(isReader);

            String str;
            while ((str = reader.readLine()) != null) {
                givenNameSet.addAll(Arrays.asList(str.split(",")));
            }

            GIVEN_NAME_SET_FEMALE = Collections.unmodifiableSet(givenNameSet);

            close(reader);
            close(isReader);
            close(inputStream);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

}
