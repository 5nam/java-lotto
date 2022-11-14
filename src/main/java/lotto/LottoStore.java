package lotto;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    private static int money;
    private static int pages;
    private static List<Lotto> userLotto;
    private static List<Integer> winningNumber;
    private static int bonus;
    private static List<LottoResult> lottoResult;
    private static int[] count = new int[5];
    private static int profit = 0;


    public void buyLotto() {
        System.out.println("구입금액을 입력해 주세요.");
        this.money = LottoBuyer.getInstance.getPay();


        pages = LottoSeller.getInstance.getSellPages(money);
        System.out.println("\n" + pages + "개를 구매했습니다.");

        this.userLotto = LottoGenerator.getInstance.issueLotto(pages);
        userLotto.stream()
                .forEach(user -> System.out.println(user.getLotto()));
    }

    public void determiningWinningNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");

        this.winningNumber = LottoDesignator.getInstance.inputLotto();

        System.out.println("\n보너스 번호를 입력해 주세요.");

        this.bonus = LottoDesignator.getInstance.inputBonus();
    }

    public void judgeLottoNumber() {
        this.lottoResult = new ArrayList<>();
        for(Lotto userLotto: userLotto) {
            lottoResult.add(LottoResultJudge.getInstance.judgeLotto(userLotto.getLotto(), winningNumber, bonus));
        }

        for (LottoResult lottoResult: lottoResult) {
            if (lottoResult == LottoResult.FIRST) {
                count[0]++;
                profit += LottoResult.FIRST.getPrize();
            }
            if (lottoResult == LottoResult.SECOND) {
                count[1]++;
                profit += LottoResult.SECOND.getPrize();
            }
            if (lottoResult == LottoResult.THIRD) {
                count[2]++;
                profit += LottoResult.THIRD.getPrize();
            }
            if (lottoResult == LottoResult.FOURTH) {
                count[3]++;
                profit += LottoResult.FOURTH.getPrize();
            }
            if (lottoResult == LottoResult.FIFTH) {
                count[4]++;
                profit += LottoResult.FIFTH.getPrize();
            }
        }
    }


    public void statsLotto() {
        System.out.println("\n당첨통계\n---");
        System.out.println(LottoResult.FIFTH.getName() + count[4] + "개");
        System.out.println(LottoResult.FOURTH.getName() + count[3] + "개");
        System.out.println(LottoResult.THIRD.getName() + count[2] + "개");
        System.out.println(LottoResult.SECOND.getName() + count[1] + "개");
        System.out.println(LottoResult.FIRST.getName() + count[0] + "개");

        System.out.println("총 수익률은 " + ProfitCalculator.getInstance.profitPercent(money, profit) + "%입니다.");
    }
}
