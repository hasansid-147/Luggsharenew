package com.android.luggshare.utils;

import android.util.Log;

import com.android.luggshare.BuildConfig;


/**
 * Helper methods that make logging more consistent throughout the app.
 */
public class LogUtils {
    private static final String PREFIX = "MBBANKING:";
    private static final String DEBUG_PREFIX = "DEBUG_" + PREFIX;
    private static final String ERROR_PREFIX = "ERROR_" + PREFIX;
    private static final int LOG_PREFIX_LENGTH = PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static final boolean FORCE_DEBUG = false;

    private LogUtils() {
    }

    private static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return PREFIX + str;
    }

    /**
     * WARNING: Don't use this when obfuscating class names with Proguard!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (FORCE_DEBUG || BuildConfig.DEBUG) {
            Log.d(tag, DEBUG_PREFIX + message);
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if (FORCE_DEBUG || BuildConfig.DEBUG) {
            Log.d(tag, DEBUG_PREFIX + message, cause);
        }
    }

    public static void LOGV(final String tag, String message) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        //noinspection PointlessBooleanExpression,ConstantConditions
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.v(tag, message, cause);
        }
    }

    public static void LOGI(final String tag, String message) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.i(tag, message, cause);
        }
    }

    public static void LOGW(final String tag, String message) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.w(tag, message, cause);
        }
    }

    public static void LOGE(final String tag, String message) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.e(tag, ERROR_PREFIX + message, null);
        }
    }

    public static void LOGE(final String tag, String message, Throwable cause) {
        if ((FORCE_DEBUG) || BuildConfig.DEBUG) {
            Log.e(tag, ERROR_PREFIX + message, cause);
        }
    }

    public static void LOGStackTrace(final Exception e) {
        if (e != null && (FORCE_DEBUG || BuildConfig.DEBUG)) {
            e.printStackTrace();
        }
    }

    public static void LOGStackTrace(final OutOfMemoryError e) {
        if (e != null && (FORCE_DEBUG || BuildConfig.DEBUG)) {
            e.printStackTrace();
        }
    }

    public static void LOGStackTrace(final Throwable e) {
        if (e != null && (FORCE_DEBUG || BuildConfig.DEBUG)) {
            e.printStackTrace();
        }
    }
}

