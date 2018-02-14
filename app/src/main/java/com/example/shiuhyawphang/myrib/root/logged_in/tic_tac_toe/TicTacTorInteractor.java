package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import android.support.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.Board.MarkerType;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link TicTacTorScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class TicTacTorInteractor
    extends Interactor<TicTacTorInteractor.TicTacTorPresenter, TicTacTorRouter> {

  @Inject Board board;
  @Inject TicTacTorPresenter presenter;

  @Inject @Named("player_one") String playerOne;
  @Inject @Named("player_two") String playerTwo;

  private MarkerType currentPlayer = MarkerType.CROSS;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();
    presenter
            .squareClicks()
            .subscribe(new Consumer<BoardCoordinate>() {
              @Override
              public void accept(BoardCoordinate xy) throws Exception {
                  if (board.cells[xy.getX()][xy.getY()] == null) {
                    if (currentPlayer == MarkerType.CROSS) {
                        board.cells[xy.getX()][xy.getY()] = MarkerType.CROSS;
                        board.currentRow = xy.getX();
                        board.currentCol = xy.getY();
                        presenter.addCross(xy);
                        currentPlayer = MarkerType.NOUGHT;
                    } else {
                        board.cells[xy.getX()][xy.getY()] = MarkerType.NOUGHT;
                        board.currentRow = xy.getX();
                        board.currentCol = xy.getY();
                        presenter.addNought(xy);
                        currentPlayer = MarkerType.CROSS;
                    }
                  }
                  if (board.hasWon(MarkerType.CROSS)) {
                    presenter.setPlayerWon(playerOne);
                  } else if (board.hasWon(MarkerType.NOUGHT)) {
                    presenter.setPlayerWon(playerTwo);
                  } else if (board.isDraw()) {
                    presenter.setPlayerTie();
                  } else {
                    updateCurrentPlayer();
                  }
              }
            });
    updateCurrentPlayer();
  }

  private void updateCurrentPlayer() {
      if (currentPlayer == MarkerType.CROSS) {
            presenter.setCurrentPlayerName(playerOne);
      } else {
          presenter.setCurrentPlayerName(playerTwo);
      }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface TicTacTorPresenter {

    Observable<BoardCoordinate> squareClicks();
    void setCurrentPlayerName(String playerName);
    void setPlayerWon(String playerName);
    void setPlayerTie();
    void addCross(BoardCoordinate xy);
    void addNought(BoardCoordinate xy);
  }

  public interface Listener {

      void gameWon(@Nullable String winner);
  }
}
