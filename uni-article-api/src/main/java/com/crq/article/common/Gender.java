package com.crq.article.common;

/**
 * @description: 性别枚举：性别:0男 1女 2保密
 * @author: crq
 * @create: 2022-03-22 16:14
 **/
public enum Gender {
    /**
     * 默认
     */
    DEFAULT(-1, "默认"),
    /**
     * 男
     */
    MALE(0, "保密"),
    /**
     * 女
     */
    FEMALE(1, "男"),
    /**
     * 保密
     */
    SECRET(2, "女");
    /**
     * key
     */
    private int key;
    /**
     * value
     */
    private String value;

    Gender(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Gender getValue(int key) {
        for (Gender gender : Gender.values()) {
            if (gender.getKey() == key) {
                return gender;
            }
        }
        return DEFAULT;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
