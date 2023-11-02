package racingcar.game;

import java.util.List;
import racingcar.domain.RacerRegistry;
import racingcar.domain.Round;
import racingcar.domain.racer.Racer;
import racingcar.game.vo.RacingCarNamesInput;
import racingcar.game.vo.TotalRoundInput;
import racingcar.util.Random;

public class RacingGameManager {

    private final Random random;
    private final RacingGameScreen racingGameScreen;
    private final RacerRegistry<Racer> racerRegistry = new RacerRegistry<>();

    public RacingGameManager(Random random, RacingGameScreen racingGameScreen) {
        this.random = random;
        this.racingGameScreen = racingGameScreen;
    }

    public void run() {
        try {
            registerRacingCar();
            Round totalRound = getTotalTurn();
            showFinalWinners(race(totalRound));
        } catch (Exception e) {
            racingGameScreen.showError(e.getMessage());
            throw e;
        } finally {
            racingGameScreen.close();
        }
    }

    private void registerRacingCar() {
        RacingCarNamesInput racingCarNamesInput = racingGameScreen.inputRacingCarNames();
        racerRegistry.addAll(racingCarNamesInput.toRacingCarList());
    }

    private Round getTotalTurn() {
        TotalRoundInput totalRoundInput = racingGameScreen.inputTotalRound();
        return totalRoundInput.toRacingTurn();
    }

    private List<String> race(Round totalRound) {
        racingGameScreen.startShowGameResult();

        RacingRoundProcessor<Racer> racingRoundProcessor = new RacingRoundProcessor<>(random, racerRegistry);
        for (int i = 0; i < totalRound.getCount(); i++) {
            racingRoundProcessor.progressRound();
            racingGameScreen.showRoundResult(racingRoundProcessor.getRacerPositions());
        }

        return racingRoundProcessor.getWinners();
    }

    private void showFinalWinners(List<String> winnerNames) {
        racingGameScreen.showFinalWinner(winnerNames);
    }
}
