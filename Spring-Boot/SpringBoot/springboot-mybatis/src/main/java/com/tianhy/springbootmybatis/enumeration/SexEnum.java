package com.tianhy.springbootmybatis.enumeration;

/**
 * {@link}
 *
 * @Desc: 性别枚举类
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SexEnum getSexEnumById(int id) {
        for (SexEnum value : SexEnum.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
