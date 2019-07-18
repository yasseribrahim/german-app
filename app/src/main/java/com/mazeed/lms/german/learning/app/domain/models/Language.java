package com.mazeed.lms.german.learning.app.domain.models;

import java.util.Locale;

/**
 * Created by interactive on 7/23/18.
 */

public enum Language {
    ENGLISH(1, Locale.ENGLISH),
    ARABIC(2, new Locale("ar"));

    private int code;
    private Locale locale;

    Language(int code, Locale locale) {
        this.code = code;
        this.locale = locale;
    }

    public int getCode() {
        return code;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return "Code: " + code + ", Locale: " + locale;
    }

    public static Language getDefaultLanguage() {
        if (Locale.getDefault().getLanguage().equals(ARABIC.getLocale().getLanguage()))
            return ARABIC;
        else
            return ENGLISH;
    }

    public static Language parse(int code) {
        if (ARABIC.code == code)
            return ARABIC;

        return ENGLISH;
    }

    public static Language change(Language language) {
        if (language != null && Language.ENGLISH.code == language.getCode()) {
            return Language.ARABIC;
        } else {
            return Language.ENGLISH;
        }
    }
}
