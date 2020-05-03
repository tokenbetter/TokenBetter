package com.tokenbetter.sdk.common.enums;

/**
 * @author wcs-team
 * @date 2018-05-05
 */
public enum SupportedLocaleEnum {
    EN_US("en-US", "English"),
    ZH_CN("zh-CN", "Simplified chinese"),
    ZH_TW("zh-TW", "Traditional chinese");

    private final String name;
    private final String comment;

    SupportedLocaleEnum(final String name, final String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
