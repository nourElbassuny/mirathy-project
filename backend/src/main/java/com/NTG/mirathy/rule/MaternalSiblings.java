package com.NTG.mirathy.rule;

import com.NTG.mirathy.DTOs.InheritanceShareDto;
import com.NTG.mirathy.Entity.Enum.FixedShare;
import com.NTG.mirathy.Entity.Enum.HeirType;
import com.NTG.mirathy.Entity.Enum.ShareType;
import com.NTG.mirathy.Entity.Enum.TaaasibRule;
import com.NTG.mirathy.util.InheritanceCase;
import org.springframework.stereotype.Component;

@Component
public class MaternalSiblings implements InheritanceRule {
    @Override
    public boolean canApply(InheritanceCase c) {
        return c.has(HeirType.MATERNAL_BROTHER) || c.has(HeirType.MATERNAL_SISTER)||c.has(HeirType.MATERNAL_SIBLING);
    }

    @Override
    public InheritanceShareDto calculate(InheritanceCase c) {
        HeirType heirType = HeirType.MATERNAL_BROTHER;
        ShareType shareType = null;
        FixedShare fixedShare = null;
        TaaasibRule taaasibRule = null;
        String reason = null;
        int totalMembers = c.count(HeirType.MATERNAL_BROTHER) + c.count(HeirType.MATERNAL_SISTER);

        if (c.hasDescendant() || c.has(HeirType.FATHER) || c.has(HeirType.GRANDFATHER)) {
            shareType = ShareType.Mahgub;
            reason = "لا يرث الإخوه لأم عند وجود الفرع الوارث المذكر - مثل الإبن وابن الإبن - أو المؤنث - مثل البنت وبنت الإبن - أو عند وجود الأصل الوارث المذكر - مثل الأب و اب الأب .";
        } else if (totalMembers == 1) {
            shareType = ShareType.FIXED;
            fixedShare = FixedShare.SIXTH;
            reason = "فرض الواحد - المنفرد - من الأخوة لأم هو السدس سواء أكان ذكرا أو أنثى ، وشرط ميراثهم هو عدم وجود الفرع الوارث المذكر - مثل الإبن وابن الإبن - أو المؤنث - مثل البنت وبنت الإبن - وعدم وجود الأصل الوارث المذكر - مثل الأب و أب الأب.";
        }else if (c.isHajariyyahCase()) {
            shareType=ShareType.FIXED;
            fixedShare=FixedShare.THIRD;
            reason="يتم تشريك الأشقاء مع الأخوة لأم ويشترك الجميع فى الثلث بالسوية لافرق بين ذكر وأنثى لأن الجميع سيرث على اعتبار كونهم إخوة لأم فقط ويتم إسقاط قرابة الأب.";
        }
        else {
            shareType = ShareType.FIXED;
            fixedShare = FixedShare.THIRD;
            reason = "فرض الأكثر من واحد من الأخوة لأم هو الثلث سواء أكانوا ذكورا أو إناثا ويقسم الثلث بينهم بالسوية لافرق بين ذكر وأنثى ، وشرط ميراثهم هو عدم وجود الفرع الوارث المذكر - مثل الإبن وابن الإبن - أو المؤنث - مثل البنت وبنت الإبن - وعدم وجود الأصل الوارث المذكر - مثل الأب و أب الأب.";
        }

        if (totalMembers == 1) {
            heirType = c.has(HeirType.MATERNAL_BROTHER) ? HeirType.MATERNAL_BROTHER : HeirType.MATERNAL_SISTER;
        } else if (c.has(HeirType.MATERNAL_BROTHER) && c.has(HeirType.MATERNAL_SISTER)) {
            heirType = HeirType.MATERNAL_SIBLING;
        } else if (c.has(HeirType.MATERNAL_SISTER)) {
            heirType = HeirType.MATERNAL_SISTER;
        }

        return new InheritanceShareDto(heirType, shareType, fixedShare, taaasibRule, reason);

    }
}
