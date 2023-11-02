package racingcar.mock;

import java.util.List;
import racingcar.game.RacingGameScreen;
import racingcar.game.vo.RacerPositionVo;
import racingcar.game.vo.RacingCarNamesInputVo;
import racingcar.game.vo.TotalRoundInputVo;
import racingcar.io.reader.Reader;
import racingcar.io.writer.Writer;

public class MockRacingGameScreen extends RacingGameScreen {
    private String racingCarNames;
    private String totalRound;
    private List<String> winnerNames;

    public MockRacingGameScreen(Reader reader, Writer writer) {
        super(reader, writer);
    }

    @Override
    public RacingCarNamesInputVo inputRacingCarNames() {
        return new RacingCarNamesInputVo(racingCarNames);
    }

    @Override
    public TotalRoundInputVo inputTotalRound() {
        return new TotalRoundInputVo(totalRound);
    }

    @Override
    public void startShowGameResult() {
    }

    @Override
    public void showRoundResult(List<RacerPositionVo> turnResult) {
    }

    @Override
    public void showError(String message) {
    }

    @Override
    public void showFinalWinner(List<String> winnerNames) {
        this.winnerNames = winnerNames;
    }

    @Override
    public void close() {
    }

    public void setRacingCarNames(String racingCarNames) {
        this.racingCarNames = racingCarNames;
    }

    public void setTotalRound(String totalRound) {
        this.totalRound = totalRound;
    }

    public List<String> getWinnerNames() {
        return this.winnerNames;
    }
}