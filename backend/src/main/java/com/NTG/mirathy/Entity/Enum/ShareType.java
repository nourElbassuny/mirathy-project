package com.NTG.mirathy.Entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShareType {
    FIXED("فرضًا"),
    TAASIB("تعصيبًا"),
    RADD("ردًا"),
    Mahgub("محجوب"),
    MALE_DOUBLE_FEMALE("للذكر مثل حظ الأنثيين"),
    FULL_AND_MATERNAL_SIBLING("يتشارك الاجوة الاشقاء و لام فى الثلث ويقسم بينهم بالتساوي")
    ;


    private final String text;


}
