package com.yulu.common.tools;

import com.yulu.common.exceptions.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期处理类
 */
public class DateTool {
    // 日期处理类支持的格式
    private static final List<String> DATE_FORMAT = new ArrayList<>();
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        DATE_FORMAT.add("yyyy-MM-dd");
        DATE_FORMAT.add("HH:mm:ss");
        DATE_FORMAT.add("yyyy-MM-dd HH:mm:ss");
    }

    // 获取当前时间 默认格式化方式 yyyy-MM-dd HH:mm:ss
    public static Date getDate() {
        return getFormatDate(DEFAULT_FORMAT, new Date());
    }

    // 指定格式化方式, 如果格式化方式不在内置的方式中, 那么默认为 yyyy-MM-dd HH:mm:ss
    public static Date getDate(String format) {
        return getFormatDate(format, new Date());
    }

    // 指定格式化方式和日期
    public static Date getDate(String format, Date date) {
        return getFormatDate(format, date);
    }

    // 指定日期
    public static Date getDate(Date date) {
        return getFormatDate(DEFAULT_FORMAT, date);
    }

    // 格式化方法
    public static Date getFormatDate(String format, Date date) {
        try {
            format = getFormat(format);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            String formatDate = simpleDateFormat.format(date);
            return simpleDateFormat.parse(formatDate);
        } catch (ParseException e) {
            throw new ServiceException();
        }
    }

    // 指定时间添加小时
    public static Date getDateAddHours(Integer addNum) {
        Date date = getDate();
//        LocalDateTime.
        return null;
    }

    // 获取格式化方式
    public static String getFormat(String format) {
        for (int i = 0; i < DATE_FORMAT.size(); i++) {
            if (DATE_FORMAT.get(i) == format) {
                break;
            }
            if (i == DATE_FORMAT.size() - 1) {
                format = DEFAULT_FORMAT;
                break;
            }
        }
        return format;
    }

}
