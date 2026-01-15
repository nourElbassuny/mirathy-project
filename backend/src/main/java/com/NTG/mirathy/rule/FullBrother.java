package com.NTG.mirathy.rule;

import com.NTG.mirathy.DTOs.InheritanceShareDto;
import com.NTG.mirathy.Entity.Enum.FixedShare;
import com.NTG.mirathy.Entity.Enum.HeirType;
import com.NTG.mirathy.Entity.Enum.ShareType;
import com.NTG.mirathy.Entity.Enum.TaaasibRule;
import com.NTG.mirathy.util.InheritanceCase;
import org.springframework.stereotype.Component;

@Component
public class FullBrother implements InheritanceRule {


    @Override
    public boolean canApply(InheritanceCase c) {
        return c.has(HeirType.FULL_BROTHER);
    }

    @Override
    public InheritanceShareDto calculate(InheritanceCase c) {
        HeirType heirType = HeirType.FULL_BROTHER;
        String reason=null;
        FixedShare fixedShare=null;
        ShareType shareType=null;
        TaaasibRule taaasibRule = null;

        if (c.hasMaleChild()||c.has(HeirType.FATHER)||c.has(HeirType.GRANDFATHER)){
            shareType=ShareType.Mahgub;
            reason="رأى الأحناف  - أنه لا يرث الأخوة أو الأخوات فى حالة وجود الجد ، ودليلهم أنه ورد فى القرآن الكريم استعمال لفظ الأب فى مكان الجد ، ولأن الجد هو أولى رجل ذكر فهو أقرب من الأخ. أما باقى المذاهب فإنها تشرك الجد والأخوة فى التركة على اعتبار أن درجة قرابة الجد والأخوة للميت متساوية.";
        }else if (c.has(HeirType.FULL_SISTER)){
            shareType=ShareType.TAASIB;
            taaasibRule=(c.totalNumberOfHeirs()==2)?TaaasibRule.MALE_TWICE_FEMALE_ALL:TaaasibRule.MALE_TWICE_FEMALE_REMAINDER;
            reason="لأخوة الأشقاء رجالاً ونساء يرثون معاً بالتعصيب للذكر مثل حظ الأنثيين .قال تعالى ( وَإِن كَانُواْ إِخْوَةً رِّجَالاً وَنِسَاء فَلِلذَّكَرِ مِثْلُ حَظِّ الأُنثَيَيْنِ) ، ويشترط لذلك أن تكون المسألة كلالة أى لا يكون هناك ولد - مثل الإبن الصلبى وابن الإبن وإن نزل - ولا والد - الأب فقط عند الجمهور - وإلا حجبوا بهم";
        }else if (c.totalNumberOfHeirs()==1){
            shareType=ShareType.TAASIB;
            taaasibRule=TaaasibRule.ALL_ESTATE_ONLY;
            reason="يرث الأخ الشقيق كل التركة تعصيبا لأنه أولى رجل ذكر .قال ﷺ ( ألحقوا الفرائض بأهلها فما بقى فهو لأولى رجل ذكر)";
        }else {
            shareType=ShareType.TAASIB;
            taaasibRule=TaaasibRule.REMAINDER_ONLY;
            reason="يرث الأخ الشقيق باقي التركة تعصيبا لأنه أولى رجل ذكر .قال ﷺ ( ألحقوا الفرائض بأهلها فما بقى فهو لأولى رجل ذكر)";
        }

        return new InheritanceShareDto(heirType, shareType, fixedShare, taaasibRule, reason);
    }
}
