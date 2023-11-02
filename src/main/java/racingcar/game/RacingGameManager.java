package racingcar.game;

import java.util.List;
import racingcar.domain.RacerRegistry;
import racingcar.domain.Round;
import racingcar.domain.race.strategy.MovementStrategy;
import racingcar.domain.race.strategy.RandomNumberBasedMovementStrategy;
import racingcar.factory.RacingCarFactory;
import racingcar.game.vo.RacingCarNamesInputVo;
import racingcar.game.vo.TotalRoundInputVo;
import racingcar.util.Random;

public class RacingGameManager {

    private final Random random;
    private final RacingGameScreen racingGameScreen;
    private final RacerRegistry racerRegistry = new RacerRegistry();

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
        RacingCarNamesInputVo racingCarNamesInputVo = racingGameScreen.inputRacingCarNames();
        racerRegistry.addAll(racingCarNamesInputVo.toList());
    }

    private Round getTotalTurn() {
        TotalRoundInputVo totalRoundInputVo = racingGameScreen.inputTotalRound();
        return totalRoundInputVo.toRacingTurn();
    }

    private List<String> race(Round totalRound) {
        racingGameScreen.startShowGameResult();
        MovementStrategy movementStrategy = new RandomNumberBasedMovementStrategy(random);
        RacingCarFactory racingCarFactory = new RacingCarFactory(movementStrategy);
        RacingRoundProcessor racingRoundProcessor = new RacingRoundProcessor(racingCarFactory, racerRegistry);
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
