package lotto.domain;

import java.util.List;

public class LottoResultJudge {
    public static final LottoResultJudge getInstance = new LottoResultJudge();

    public LottoResult judgeLotto(List<Integer> userLotto, List<Integer> lotto, int bonus) {
        int correctNum = correspondNumber(userLotto, lotto);
        boolean correspondBonus = correspondBonus(userLotto, bonus);

        LottoResult result = LottoResult.getResult(correctNum, correspondBonus);

        return result;
    }

    private int correspondNumber(List<Integer> userLotto, List<Integer> lotto) {
        int correctNumber = 0;
        for (int userLottoIndex = 0; userLottoIndex < userLotto.size(); userLottoIndex++) {
            if (lotto.contains(userLotto.get(userLottoIndex))) {
                correctNumber++;
            }
        }
        return correctNumber;
    }

    private boolean correspondBonus(List<Integer> userLotto, int bonus) {
        if (userLotto.contains(bonus)) {
            return true;
        }
        return false;
    }
}
