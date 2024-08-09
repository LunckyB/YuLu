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
    public static Date getDateAddHours(Date date, Integer addNum) {
        return formatDateCalculate(date, addNum, 3, 1);
    }

    /**
     * 日期处理数据
     * @param date 需要操作的日期
     * @param num 操作的数量
     * @param operation 操作类型  1: 秒 2: 分钟 3: 小时 4: 天 5: 星期
     * @param type 操作的类型 1: 加法 2: 减法
     * @return
     */
    public static Date formatDateCalculate(Date date, Integer num, Integer operation, Integer type) {
        Long operationNum = 0L; // 需要操作的数量
        if (operation == 1) { // 秒
            operationNum = (long) num * 1000;
        } else if (operation == 2) { // 分钟
            operationNum = (long) num * 60 * 1000;
        } else if (operation == 3) { // 小时
            operationNum = (long) num * 60 * 60 * 1000;
        } else if (operation == 4) { // 天
            operationNum = (long) num * 24 * 60 * 60 * 1000;
        } else if (operation == 5) { // 星期
            operationNum = (long) num * 7 * 24 * 60 * 60 * 1000;
        }
        long time = date.getTime();
        Date date1 = new Date(time + operationNum);
        return getFormatDate(DEFAULT_FORMAT, date1);
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
