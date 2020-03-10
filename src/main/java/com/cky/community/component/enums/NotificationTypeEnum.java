package com.cky.community.component.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private int commentType;
    private String name;


    public int getType() {
        return commentType;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name) {
        this.commentType = status;
        this.name = name;
    }

    public static String nameOfType(int commentType) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType() == commentType) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
