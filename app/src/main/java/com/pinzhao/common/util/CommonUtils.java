package com.pinzhao.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.widget.ScrollView;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ${sheldon} on 2016/12/30.
 */
public class CommonUtils {
    /**
     * 获取版本名
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;

    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int version = packInfo.versionCode;
        return version;

    }

    /**
     * 获取随机数
     *
     * @param n
     * @return
     */
    public static int getRandomNumber(int n) {

        return (int) (Math.random() * n);

    }

    /**
     * 获取小图的路径
     *
     * @param url
     * @return
     */
    public static String getLittlePictureUrl(String url) {
        String littleUrl = null;
        int pointIndex = url.lastIndexOf(".");
        // 判断是否有图片
        if (pointIndex > 0) {
            littleUrl = url.substring(0, pointIndex) + "_little"
                    + url.substring(pointIndex, url.length());
        }

        return littleUrl;

    }

    /**
     * 过滤文本内容,截取数字
     *
     * @param body
     * @return
     */
    public static String filterStringAndGetDigit(String body) {
        // 将字符串转化为字符数组
        char[] array = body.toCharArray();
        boolean isFirstDigitFlag = true;
        int startIndex = 0;
        int count = 0;
        // 遍历数组
        for (int i = 0; i < array.length; i++) {
            // 判断是否为数字字符
            if (Character.isDigit(array[i])) {
                // 判断是否为第一次出现数字字符
                if (isFirstDigitFlag) {
                    // 记录此时的索引位置
                    startIndex = i;
                    // 将标记置为假
                    isFirstDigitFlag = false;
                }
                // 判断是否为连续的数字
                if (i - startIndex - count == 0) {
                    // 计数个数
                    count++;
                } else {
                    // 若不是则直接跳出循环
                    break;
                }
            }
        }
        // 截取文本的数字部分
        return body.substring(startIndex, startIndex + count);
    }

    /**
     * 转化时间格式
     *
     * @param time
     * @return
     */
    public static String changeTimeFormat(String time) {
        String str = null;
        str = time.replace("年", "-").replace("月", "-").replace("日", "")
                .replaceAll(" 周[一二三四五六日]", "").replace(" 周", "");
        str = str + ":00";
        return str;
    }

    /**
     * 转化时间格式
     *
     * @param time
     * @return
     */
    public static String changeTimeFormatForShow(String time) {
        String str = null;
        str = time.replace("-", "月").replace(" ", "日 ");
        return str;
    }


    /**
     * 转化性别
     *
     * @param sex
     * @return
     */
    public static String changeGender(String sex) {
        int gender = Integer.parseInt(sex);
        return (1 == gender) ? "男" : "女";
    }

    /**
     * 转化性别
     *
     * @param sex
     * @return
     */
    public static String changeGender(int sex) {
        return (1 == sex) ? "男" : "女";
    }

    /**
     * 转换话题类型
     *
     * @param topicType
     *            1：生活，2：商务，3：旅游，4：其他
     * @return
     */
    public static String changeTopicType(String topicType) {
        String topic = null;
        int type = Integer.parseInt(topicType);
        switch (type) {

            case 1:
                topic = "生活";
                break;

            case 2:
                topic = "商务";
                break;

            case 3:
                topic = "旅游";
                break;

            case 4:
                topic = "其他";
                break;
        }
        return topic;
    }

    /**
     * 转换评价等级 rank=3 表示好评 rank=2为中评，rank=1为差评
     *
     * @param rank
     * @return
     */
    public static String changeEvaluateRank(String rank) {
        String evaluateRank = null;
        int type = 3;
        try {
            type = Integer.parseInt(rank);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        switch (type) {

            case 1:
                evaluateRank = "差评";
                break;

            case 2:
                evaluateRank = "中评";
                break;

            case 3:
                evaluateRank = "好评";
                break;

            default:
                evaluateRank = "";
                break;
        }

        return evaluateRank;
    }

    /**
     * 转换等级
     *
     * @param text
     * @return
     */
    public static String changeLevel(String text) {
        String level = null;
        if ("优秀".equals(text)) {
            level = "3";
        } else if ("较好".equals(text)) {
            level = "2";
        } else {
            level = "1";
        }
        return level;
    }

    /**
     * 余额数值转化
     *
     * @param balance
     * @return
     */
    public static double changeBalance(String balance) {
        return Double.parseDouble(balance);
    }

    /**
     * 用uuid产生一个唯一的id串
     *
     * @return
     */
    public static String createUniqueId() {
        return "pic_" + UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 清空用户的登录信息
     */
    public static void cleanUserLoginInfo() {
        Context context = UIUtils.getContext();
        SharedPreferencesUtils.putBoolean(context,
                PinzhaoConstants.ALREADY_LOGIN_FLAG, false);
        SharedPreferencesUtils.putInt(context, PinzhaoConstants.USER_ID, 0);
        SharedPreferencesUtils.putString(context, PinzhaoConstants.TOKEN, "");
        SharedPreferencesUtils.putString(context, PinzhaoConstants.USER_TYPE, "");
        // 调用此 API 来设置标签。
        Set<String> tagSet = new LinkedHashSet<String>();
        tagSet.add("0" + "");
    }

    /**
     * 获取用户是否登录标记
     *
     * @return
     */
    public static boolean getUserIsLoginFlag() {
        return SharedPreferencesUtils.getBoolean(UIUtils.getContext(),
                PinzhaoConstants.ALREADY_LOGIN_FLAG);
    }

    /**
     * 计算消息的距离当前的时间畴
     *
     * @param startDate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getDayString(Date startDate) {
        int month = startDate.getMonth() + 1;
        int date = startDate.getDate();
        // int hour = startDate.getHours();
        // int min = startDate.getMinutes();

        int offSet = Calendar.getInstance().getTimeZone().getRawOffset();
        long today = (System.currentTimeMillis() + offSet) / 86400000;
        long start = (startDate.getTime() + offSet) / 86400000;
        String rtnString = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        calendar.setTime(new Date());
        int weekofyear2 = calendar.get(Calendar.WEEK_OF_YEAR);
        int year2 = calendar.get(Calendar.YEAR);
        // System.out.println(weekofyear - weekofyear2);
        // System.out.println(year - year2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        String HH_mm = sdf.format(startDate);
        rtnString = month + "-" + date + " " + HH_mm;
        if ((year - year2 == 0) && (weekofyear - weekofyear2 == 0)) {// 本周
            String weekStr = "";
            switch (week) {
                case 1:
                    weekStr = "周日";
                    break;
                case 2:
                    weekStr = "周一";
                    break;
                case 3:
                    weekStr = "周二";
                    break;
                case 4:
                    weekStr = "周三";
                    break;
                case 5:
                    weekStr = "周四";
                    break;
                case 6:
                    weekStr = "周五";
                    break;
                case 7:
                    weekStr = "周六";
                    break;

                default:
                    break;
            }
            rtnString = weekStr + " " + HH_mm;
        }
        switch ((int) (start - today)) {
            case -2:
                rtnString = "前天 " + HH_mm;
                break;
            case -1:
                rtnString = "昨天 " + HH_mm;
                break;
            case 0:
                rtnString = "今天 " + HH_mm;
                break;
            case 1:
                rtnString = "明天 " + HH_mm;
                break;
        }
        return rtnString;
    }

    /**
     * 判断是否为输入法的表情字符
     *
     * @param codePoint
     * @return
     */
    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 是否附加ScrollView
     *
     * @param scrollView
     * @return
     */
    public static boolean isScrollViewAttach(ScrollView scrollView) {
        if (scrollView != null) {
            if (scrollView.getScrollY() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置为默认字体
     */
    public static void setTextSizeDefault() {
        Resources res = UIUtils.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 保留两位小数点
     *
     * @param value
     * @return
     */
    public static String keepTwoDecimalPoint(double value) {
        DecimalFormat df = new DecimalFormat("###.00");
        return df.format(value);
    }

    /**
     * 以指定格式格式化时间
     *
     * @param time
     * @param format
     *            yyyy-MM-dd HH:mm默认格式
     * @return
     */
    public static String formatTime(long time, String format) {
        String pattern = "";
        if ("".equals(format)) {
            pattern = "yyyy-MM-dd HH:mm";
        } else {
            pattern = format;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        return sdf.format(new Date(time));
    }

    /**
     * 生成发货编码
     *
     * @param time
     * @return
     */
    public static String produceSendGoodsCode(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        return sdf.format(new Date(time)) + "-" + (time + "").substring(4, 10);
    }



    /**
     * 校验不为空
     *
     * @param reference
     * @return
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 图片转字节数组
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp,
                                        final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 创建一个唯一事务标记
     *
     * @param type
     * @return
     */
    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                : type + System.currentTimeMillis();
    }

    /**
     * 插空法格式化手机号码
     *
     * @param phone
     * @return
     */
    public static String formatPhoneNumber(String phone) {
        StringBuilder sb = new StringBuilder();
        if (11 == phone.length()) {
            char[] arrays = phone.toCharArray();
            for (int i = 0; i < arrays.length; i++) {
                sb.append(arrays[i]);
            }

            sb.insert(3, " ");
            sb.insert(8, " ");
        }
        return sb.toString();
    }
    /**
     * 判断用户是否是卖家
     *
     * 使用时最好先判断是否登录
     * @return
     */
    public static boolean isSellerRole(){
        boolean isSellerRole = false;
        String userType = SharedPreferencesUtils.getString(UIUtils.getContext(),PinzhaoConstants.USER_TYPE);
        if (PinzhaoConstants.SELLER_ROLE.equals(userType)) {
            isSellerRole = true;
        }
        return isSellerRole;
    }

    /**
     * 得到用户id
     * @return
     */
    public static int getUserId(){
        return SharedPreferencesUtils.getInt(UIUtils.getContext(),PinzhaoConstants.USER_ID);
    }

    /**
     * 得到token
     * @return
     */
    public static String  getToken(){
        return SharedPreferencesUtils.getString(UIUtils.getContext(),PinzhaoConstants.TOKEN);
    }

    /**
     * 得到指定日期几个月后的时间  ,精确到年月日
     * @param date 传null的话默认为当前日期
     * @param month
     * @return 返回String类型 格式为yyyy-MM-dd
     */
    public static String getAfterMonth(Date date , int month){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        String formatString = dateFormat.format(calendar.getTime());
        return formatString;

    }

    /**
     * 格式化日期  yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatDateWithYMD(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化日期  yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatDateWithYMDHMS(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化日期  yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String formatDateWithYMDHM(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (date == null) {
            date = new Date();
        }
        return dateFormat.format(date);
    }
}
