package com.cky.community.component.enums;

public enum CommentTypeEnum {
    ARTICLE(1),
    COMMENT(2);
    private Integer commentType;


    public Integer getType() {
        return commentType;
    }

    CommentTypeEnum(Integer commentType) {
        this.commentType = commentType;
    }

    public static boolean isExist(Integer commentType) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == commentType) {
                return true;
            }
        }
        return false;
    }
}
