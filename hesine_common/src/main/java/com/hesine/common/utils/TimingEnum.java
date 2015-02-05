package com.hesine.common.utils;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-19
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public enum TimingEnum {
    FIVE(1, "5分钟", -5), ONEDAY(2, "一天", -1*24*60);
    // 成员变量
    private int index;
    private String name;
    private int minutes;


    // 构造方法
    private TimingEnum( int index, String name, int minutes) {
        this.index = index;
        this.name = name;
        this.minutes = minutes;
    }

    // 普通方法
    public static String getName(int index) {
        for (TimingEnum timingEnum : TimingEnum.values()) {
            if (timingEnum.getIndex() == index) {
                return timingEnum.name;
            }
        }
        return null;
    }

    // 普通方法
    public static int getMinutes(int index) {
        for (TimingEnum timingEnum : TimingEnum.values()) {
            if (timingEnum.getIndex() == index) {
                return timingEnum.minutes;
            }
        }
        return 0;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getIndex() {
        return index;

    }

    public void setIndex(int index) {
        this.index = index;
    }

}
