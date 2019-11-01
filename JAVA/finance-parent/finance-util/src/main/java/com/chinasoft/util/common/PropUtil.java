package com.chinasoft.util.common;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Auther: 汪毅
 * @Date: 2018/7/2 09:28
 * @Description:
 */
public class PropUtil {

    private static Props props;

    static {
        try {
            props = new Props("application-prod.properties");
        } catch (Exception e) {
            try {
                props = new Props("application-test-ci.properties");
            } catch (Exception e1) {
                try {
                    props = new Props("application-test.properties");
                } catch (Exception e2) {
                    try {
                        props = new Props("application-dev.properties");
                    } catch (Exception e3) {
                        throw new RuntimeException(e3);
                    }
                }
            }
        }
    }

    public static Object getObj(String key, Object defaultValue) {
        return props.getStr(key, null == defaultValue ? null : defaultValue.toString());
    }

    public static Object getObj(String key) {
        return props.getObj(key, null);
    }

    public static String getStr(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static String getStr(String key) {
        return props.getProperty(key);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return Convert.toInt(getStr(key), defaultValue);
    }

    public static Integer getInt(String key) {
        return getInt(key, null);
    }

    public static Boolean getBool(String key, Boolean defaultValue) {
        return Convert.toBool(getStr(key), defaultValue);
    }

    public static Boolean getBool(String key) {
        return getBool(key, null);
    }

    public static Long getLong(String key, Long defaultValue) {
        return Convert.toLong(getStr(key), defaultValue);
    }

    public static Long getLong(String key) {
        return getLong(key, null);
    }

    public static Character getChar(String key, Character defaultValue) {
        final String value = getStr(key);
        if (StrUtil.isBlank(value)) {
            return defaultValue;
        }
        return value.charAt(0);
    }

    public static Character getChar(String key) {
        return getChar(key, null);
    }

    public static Float getFloat(String key) {
        return getFloat(key, null);
    }

    public static Float getFloat(String key, Float defaultValue) {
        return Convert.toFloat(getStr(key), defaultValue);
    }

    public static Double getDouble(String key, Double defaultValue) throws NumberFormatException {
        return Convert.toDouble(getStr(key), defaultValue);
    }

    public static Double getDouble(String key) throws NumberFormatException {
        return getDouble(key, null);
    }

    public static Short getShort(String key, Short defaultValue) {
        return Convert.toShort(getStr(key), defaultValue);
    }

    public static Short getShort(String key) {
        return getShort(key, null);
    }

    public static Byte getByte(String key, Byte defaultValue) {
        return Convert.toByte(getStr(key), defaultValue);
    }

    public static Byte getByte(String key) {
        return getByte(key, null);
    }

    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue) {
        final String valueStr = getStr(key);
        if (StrUtil.isBlank(valueStr)) {
            return defaultValue;
        }

        try {
            return new BigDecimal(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static BigDecimal getBigDecimal(String key) {
        return getBigDecimal(key, null);
    }

    public static BigInteger getBigInteger(String key, BigInteger defaultValue) {
        final String valueStr = getStr(key);
        if (StrUtil.isBlank(valueStr)) {
            return defaultValue;
        }

        try {
            return new BigInteger(valueStr);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static BigInteger getBigInteger(String key) {
        return getBigInteger(key, null);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> clazz, String key, E defaultValue) {
        return Convert.toEnum(clazz, getStr(key), defaultValue);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
        return getEnum(clazz, key, null);
    }

    // ----------------------------------------------------------------------- Get end

    // ----------------------------------------------------------------------- Set start
    /**
     * 设置值，无给定键创建之。设置后未持久化
     *
     * @param key 属性键
     * @param value 属性值
     */
    public static void setProperty(String key, Object value) {
        props.setProperty(key, value.toString());
    }

    /**
     * 持久化当前设置，会覆盖掉之前的设置
     *
     * @param absolutePath 设置文件的绝对路径
     * @throws IORuntimeException IO异常，可能为文件未找到
     */
    public static void store(String absolutePath) throws IORuntimeException{
        Writer writer = null;
        try {
            writer = FileUtil.getWriter(absolutePath, "UTF-8", false);
            props.store(writer, null);
        } catch (IOException e) {
            throw new IORuntimeException(e, "Store properties to [{}] error!", absolutePath);
        } finally {
            IoUtil.close(writer);
        }
    }

    /**
     * 存储当前设置，会覆盖掉以前的设置
     *
     * @param path 相对路径
     * @param clazz 相对的类
     */
    public static void store(String path, Class<?> clazz) {
        props.store(FileUtil.getAbsolutePath(path, clazz));
    }
    // ----------------------------------------------------------------------- Set end

}
