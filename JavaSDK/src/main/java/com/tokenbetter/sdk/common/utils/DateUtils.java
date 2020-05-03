package com.tokenbetter.sdk.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Time Utils  <br/>
 * Created by Cran on 2020/01/03  <br/>
 */
public class DateUtils {

    public static String TIME_STYLE_S1 = "yyyy-MM-dd";
    public static String TIME_STYLE_S2 = "yyyy-MM-dd HH:mm";
    public static String TIME_STYLE_S3 = "yyyy-MM-dd HH:mm:ss";
    public static String TIME_STYLE_S4 = "yyyy-MM-dd HH:mm:ss:S";
    public static String TIME_STYLE_S5 = "yyyy-MM-dd HH:mm:ss:S E zZ";
    public static String TIME_STYLE_S6 = "yyyyMMddHHmmssS";
    public static String TIME_STYLE_S7 = "yyyy年MM月dd日HH时mm分ss秒";
    public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");

    static {
        DateUtils.SDF.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Returns the time string in format. <br/>
     * Styles:
     * <p>
     * <blockquote><pre>
     *         1:  2018-03-01
     *         2:  2018-03-01 15:53
     *         3:  2018-03-01 15:53:43
     *         4:  2018-03-01 15:53:43:288
     *         5:  2018-03-01 15:53:43:288 Thu CST+0800
     *         6:  20180301155343288
     *         7:  2018年03月01日15时53分43秒
     *         8:  2018-03-01T07:53:43.288Z
     *         9:  1519890823.288
     * `default`:  Thu Mar 01 15:53:43 CST 2018
     * </pre></blockquote>
     * </p>
     *
     * @param time  Date object, if time=null, returns the current time.
     * @param style Format number
     * @return String time string in format
     */
    public static String timeToString(Date time, final int style) {
        if (time == null) {
            time = new Date();
        }
        final String timeStyle;
        switch (style) {
            case 1: {
                timeStyle = DateUtils.TIME_STYLE_S1;
                break;
            }
            case 2: {
                timeStyle = DateUtils.TIME_STYLE_S2;
                break;
            }
            case 3: {
                timeStyle = DateUtils.TIME_STYLE_S3;
                break;
            }
            case 4: {
                timeStyle = DateUtils.TIME_STYLE_S4;
                break;
            }
            case 5: {
                timeStyle = DateUtils.TIME_STYLE_S5;
                break;
            }
            case 6: {
                timeStyle = DateUtils.TIME_STYLE_S6;
                break;
            }
            case 7: {
                timeStyle = DateUtils.TIME_STYLE_S7;
                break;
            }
            case 8: {
                final SimpleDateFormat sdf = (SimpleDateFormat) DateUtils.SDF.clone();
                return sdf.format(time);
            }
            case 9: {
                return DateUtils.getEpochTime(time);
            }
            default: {
                return time.toString();
            }
        }
        return new SimpleDateFormat(timeStyle).format(time);
    }

    /**
     * UNIX timestamp {@see https://en.wikipedia.org/wiki/Unix_time}
     */
    public static long getUnixTime() {
        return Instant.now().getEpochSecond();
    }

    /**
     * Return ISO-8601 format time eg: 2018-06-17T11:15:27Z
     * {@see https://en.wikipedia.org/wiki/ISO_8601}
     *
     * @param date current date
     * @return iso8601 format time
     */
    public static String getIsoTime(final Date date) {
        final DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_INSTANT;
        return isoLocalDateTime.format(date.toInstant());
    }

    /**
     * Return ISO-8601 format time eg: 2018-06-17T11:15:27Z
     * {@see https://en.wikipedia.org/wiki/ISO_8601}
     *
     * @param instant current Instant
     * @return iso8601 format time
     */
    public static String getIsoTime(final Instant instant) {
        final DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_INSTANT;
        return isoLocalDateTime.format(instant);
    }

    /**
     * epoch time  eg: 1517662142.557
     */
    public static String getEpochTime(final Date time) {
        return DateUtils.getEpochTime(time.getTime());
    }

    /**
     * epoch time  eg: 1517662142.557
     */
    public static String getEpochTime(final long epochMills) {
        final BigDecimal bd1 = new BigDecimal(epochMills);
        final BigDecimal bd2 = new BigDecimal(1000);
        return bd1.divide(bd2, 3, BigDecimal.ROUND_DOWN).toString();
    }

    /**
     * convert UTC timestamp：2018-02-03T16:56:29.919Z  -> object: Sun Feb 04 00:56:29 CST 2018
     */
    public static Date parseUTCTime(final String utcTime) {
        if (StringUtils.isEmpty(utcTime)) {
            return null;
        }

        try {
            final SimpleDateFormat sdf = (SimpleDateFormat) DateUtils.SDF.clone();
            return sdf.parse(utcTime);
        } catch (final Exception e) {
            return null;
        }
    }

    /**
     * convert decimal timestamp：1517676989.919 ->   -> object: Sun Feb 04 00:56:29 CST 2018
     */
    public static Date parseDecimalTime(final String decimalTime) {
        if (StringUtils.isEmpty(decimalTime)) {
            return null;
        }
        final BigDecimal bd1 = new BigDecimal(decimalTime);
        final BigDecimal bd2 = new BigDecimal(1000);
        return new Date(Instant.ofEpochMilli(bd1.multiply(bd2).longValue()).toEpochMilli());
    }
}
