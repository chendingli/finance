package com.chinasoft.util.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class DateUtil {

    protected static Logger logger = Logger.getLogger(DateUtil.class);

    public static Date addDay(Date date, int days) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static Date addMonth(Date date, int months) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    //获取当天结束时间
    public static Date getDayEnd(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date clearHour(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date clearMinuter(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Integer getWeekOfYear(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getMonthOfYear(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static Integer getYear(Date date) {
        // 空值判断
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static boolean afterDate(Date first, Date second) {
        // 空值判断
        if (first == null || second == null) {
            return false;
        }

        return first.getTime() - second.getTime() < 0 ? true : false;
    }

    // 返回值格式：2017-01-05 17:56:45.801
    public static Timestamp currentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    // 返回值格式：2017-01-05 18:00:34
    public static String currentDatetime() {
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(new Date());
    }

    // 跟据自己传入的格式将时间转换成自己传入的那种格式
    // 举例：传入的格式为"yyyyMMddHHmmssSSS"，返回日期格式为：20170119120835001
    public static String currentDatetime(String timeFormat) {
        // 空值判断
        if (StringUtils.isEmpty(timeFormat)) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(timeFormat);

        return df.format(new Date());
    }

    // 传入的的文件原文件名加上后面的时间构建成一个新的文件名，传入参数不包含后缀名
    // 举例：传入的文件名为“我的照片”及"20170119122409937"，那么构建后的新文件名是“我的照片_20170119122409937”
    public static String fileNameWithCurrentTimestamp(String filePrefix, String timeFormat) {
        String dateTimeStr = DateUtil.currentDatetime(timeFormat);
        String newFileName = filePrefix + "_" + dateTimeStr;

        return newFileName;
    }

    // 传入的的文件原文件名加上后面的时间构建成一个新的文件名，传入参数后缀名
    // 举例：传入的参数为：“我的照片.jpg”及"yyyyMMddHHmmssSSS"，那么构建后的新文件名是“我的照片_20170119122409937.jpg”
    public static String fileNameWithTimestampAndFormat(String fileFullName, String timeFormat) {

        int pointIndex = fileFullName.indexOf("."); // 获得文件名中点的下标
        String filePrefix = fileFullName.substring(0, pointIndex); // 截取文件中“.”之前的字符串。
        String fileSuffix = fileFullName.substring(pointIndex); // 根据点的下标来截取点后面的后缀名。

		/*
         * 通过原始名与当前时间构建出来一个新的文件名
		 * 
		 */
        String fileNameWithTime = DateUtil.fileNameWithCurrentTimestamp(filePrefix, timeFormat);
        // String saveFileName = fileNameWithTime + "." + fileSuffix;
        String saveFullFileName = fileNameWithTime + fileSuffix;

        return saveFullFileName;
    }

    // 传入的的文件原文件名加上后面的时间构建成一个新的文件名
    public static String fileNameWithCurrentDate(String filePrefix) {
        String dateTimeStr = DateUtil.currentDatetime("yyyyMMdd");
        String newFileName = filePrefix + "_" + dateTimeStr;

        return newFileName;
    }

    // 返回值格式：Thu Jan 05 18:00:34 CST 2017
    public static Date currentDate() {
        return new Date();
    }

    // 判断日期是否合法
    public static boolean isValidDate(String srcStrDate) {
        // 空值判断
        if (StringUtils.isEmpty(srcStrDate)) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date srcDate = null;
        Date rangeStartDate = null;
        Date rangeEndDate = null;

        try {
            srcDate = dateFormat.parse(srcStrDate);
            rangeStartDate = dateFormat.parse("1900-01-01");
            rangeEndDate = dateFormat.parse("2100-12-31");
        } catch (ParseException e) {
            logger.error("公共组件：按指定格式解析日期字符串时产生异常", e);
            return false;
        }

        // 日期范围判断：判断日期是否在"1900-01-01"与"2100-12-31"之间
        if (DateUtil.afterDate(srcDate, rangeStartDate) || DateUtil.afterDate(rangeEndDate, srcDate)) {
            return false;
        }

        return true;
    }

    // 根据格式匹配返回日期
    public static Date stringToDate(String pattern, String srcDate) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(srcDate)) {
            return null;
        }

        Date dstDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(srcDate);
        try {
            dstDate = dateFormat.parse(pattern);
        } catch (ParseException e) {
            logger.error("公共组件：按指定格式解析日期字符串时产生异常", e);
            return null;
        }
        return dstDate;
    }
    

    // 根据指定数字返回Date类型的日期
    public static Date longToDate(long mseconds) {

        Date date = new Date(mseconds);

        return date;
    }

    // 根据指定数字返回字符类型的日期
    public static String longToDateString(long mseconds) {
        Date date = DateUtil.longToDate(mseconds);
        return DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", date);
    }

    // 根据查询结束日期，获取第二日的0点0时0分时间，作为SQL查询条件
    // 举例：
    // 输入："yyyy-MM-dd", "2017-09-31"
    // 输出："2017-10-01 00:00:00"
    public static String nextQueryTime(String datePattern, String srcDate) {
        Date tempQueryFinishDate = DateUtil.stringToDate(datePattern, srcDate);
        Date queryFinishDate = DateUtil.addDay(tempQueryFinishDate, 1);
        return DateUtil.dateToString(datePattern, queryFinishDate) + " 00:00:00";
    }

    public static String dateToString(String pattern, Date srcDate) {
        return srcDate == null ? "" : new SimpleDateFormat(pattern).format(srcDate);
    }

    // 返回间隔天数
    public static int getDayInterval(Date date1, Date date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String sDate1 = df.format(date1);
        String sDate2 = df.format(date2);
        Date date3 = new Date();
        Date date4 = new Date();
        try {
            date3 = df.parse(sDate1);
            date4 = df.parse(sDate2);
        } catch (ParseException e) {
            logger.error("公共组件：获取两天之间的间隔时产生异常", e);
            return -1;
        }

        int days = (int) ((date4.getTime() - date3.getTime()) / (24 * 60 * 60 * 1000));
        return days;
    }

    public static int getDateInterval(Timestamp startTimestamp, Timestamp endTimestamp) {

        long interval = endTimestamp.getTime() - startTimestamp.getTime();

        return (int) (interval / (1000 * 60 * 60 * 24));
    }

    public static int getSecondInterval(long startTimeMillis, long endTimeMillis) {
        return (int) (endTimeMillis - startTimeMillis) / 1000;
    }

    public static String formatISODate(Date date) {
        return date == null ? "" : DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(date);
    }

    public static String formatISOTime(Date date) {
        return date == null ? "" : DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(date);
    }

    public static String formatISODateTime(Date date) {
        return date == null ? "" : DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(date);
    }

    public static int getMinutesInterval(long startTimeMillis, long endTimeMillis) {
        return (int) (endTimeMillis - startTimeMillis) / 1000;
    }

    // 获取昨天凌晨24：00毫秒数
    public static Long getYesterdayDateMs() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    // 定时任务中，计算前一天0点时间
    public static Date getStartDayTime(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date time = calendar.getTime();
        return time;
    }

    // 定时任务中，计算前一天结束时间，及今天0点
    public static Date getEndDayTime(Date date) {

        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date time = calendar.getTime();
        return time;
    }

    // 获取月天数
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // 获取月第一天
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    // 获取月最后一天
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    // 获取月天列表
    public static List<Date> getDayListOfMonth(Date date) {
        List<Date> dayList = new ArrayList<>();
        Date curDate = getFirstDayOfMonth(date);
        int size = getDayOfMonth(date);
        for (int i = 0; i < size; i++) {
            dayList.add(curDate);
            curDate = DateUtils.addDays(curDate, 1);
        }
        return dayList;
    }

    // 获取月第一天列表
    public static List<Date> getFirstDayOfMonthListBetween(Date startDate,Date endDate) {
        startDate = DateUtil.getFirstDayOfMonth(startDate);
        endDate = DateUtil.getFirstDayOfMonth(endDate);
        List<Date> dateList = new ArrayList<>();
        Date curDate=startDate;
        while (DateUtils.truncatedCompareTo(curDate,endDate, Calendar.DATE) <= 0) {
            dateList.add(curDate);
            curDate=DateUtils.addMonths(curDate,1);
        }
        return  dateList;
    }

    // 获取天列表
    public static List<Date> getDayListBetween(Date startDate, Date endDate) {
        List<Date> dayList = new ArrayList<>();
        Date curDate = startDate;
        while (DateUtils.truncatedCompareTo(curDate, endDate, Calendar.DATE) <= 0) {
            dayList.add(curDate);
            curDate = DateUtils.addDays(curDate, 1);
        }
        return dayList;
    }

    // 格式化日期列表
    public static List<String> formatDayList(List<Date> dayList, String pattern) {
        List<String> dateList = new ArrayList<>();
        for (Date day : dayList
                ) {
            dateList.add(DateUtil.dateToString(pattern, day));
        }
        return dateList;
    }
    //获取连个时间差
    public static String  dateDiff(Date startTime, Date endTime) {
        String datestr="";
        try {
            //按照传入的格式生成一个simpledateformate对象
            long nd = 1000*24*60*60;//一天的毫秒数
            long nh = 1000*60*60;//一小时的毫秒数
            long nm = 1000*60;//一分钟的毫秒数
            long ns = 1000;//一秒钟的毫秒数long diff;try {
            //获得两个时间的毫秒时间差异
            long diff = endTime.getTime() - startTime.getTime();
            long day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            long min = diff%nd%nh/nm;//计算差多少分钟
            long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
            String hourstr=hour+"";
            if(hourstr.length()==1){
                hourstr="0"+hourstr;
            }
            logger.debug("时间相差："+hourstr+"小时"+min+"分钟"+sec+"秒。");
            datestr=hourstr+":"+min+":"+sec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datestr;
    }
    
    /**
     * 日期相减(返回秒值)
     * @param date Date
     * @param date1 Date
     * @return int
     * @author
     */
    public static Date getDateAdd(Date date, int expire, int idate) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {// 默认当前时间
            calendar.setTime(date);
        }
        calendar.add(idate, expire);
        return calendar.getTime();
    }
    
    /**
     * 获取 指定日期 后 指定毫秒后的 Date
     *
     * @param date
     * @param millSecond
     * @return
     */
    public static Date getDateAddMillSecond(Date date, int millSecond) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {// 没有 就取当前时间
            cal.setTime(date);
        }
        cal.add(Calendar.MILLISECOND, millSecond);
        return cal.getTime();
    }
    
    public static void main(String[] args) {
        int expire=50;
        System.out.println(DateUtil.getDateAddMillSecond(null, expire));
    }
    
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static String getDatestr(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);//获取年份
        int month=cal.get(Calendar.MONTH);//获取月份
        int day=cal.get(Calendar.DATE);//获取日
        int hour=cal.get(Calendar.HOUR);//小时
        int minute=cal.get(Calendar.MINUTE);//分
        int second=cal.get(Calendar.SECOND);//秒
        int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);//一周的第几天
        String datestr=year+"年"+month+"月"+day+"日 "+hour+"时"+minute+"分";
        return datestr;
    }

    public static Date getDate(Long timestamp) {
        return null != timestamp ? new Date(timestamp) : null;
    }
}
