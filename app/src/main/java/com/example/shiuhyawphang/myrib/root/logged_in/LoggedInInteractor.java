package com.example.shiuhyawphang.myrib.root.logged_in;

import android.support.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.core.EmptyPresenter;
import com.example.shiuhyawphang.myrib.root.logged_in.off_game.OffGameInteractor;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.TicTacTorInteractor;

import javax.inject.Inject;

/**
 * Coordinates Business Logic for {@link LoggedInScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class LoggedInInteractor extends Interactor<EmptyPresenter, LoggedInRouter> {

  @Inject @LoggedInBuilder.LoggedInInternal MutableScoreStream scoreStream;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    getRouter().attachOffGame();
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  class OffGameListener implements  OffGameInteractor.Listener {

    @Override
    public void onStartGame() {
      getRouter().detachOffGame();
      getRouter().attachTicTacTor();
    }
  }

  class TicTacTorListener implements TicTacTorInteractor.Listener {

    @Override
    public void gameWon(@Nullable String winner) {
      if (winner != null) {
        scoreStream.addVictory(winner);
      }
      getRouter().detachTicTacTor();
      getRouter().attachOffGame();
    }
  }
  /**
   * Presenter interface implemented by this RIB's view.
   */
}
