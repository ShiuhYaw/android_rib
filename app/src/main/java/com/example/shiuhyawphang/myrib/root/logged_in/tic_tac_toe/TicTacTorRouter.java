package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link TicTacTorBuilder.TicTacTorScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class TicTacTorRouter extends
    ViewRouter<TicTacTorView, TicTacTorInteractor, TicTacTorBuilder.Component> {

  public TicTacTorRouter(
      TicTacTorView view,
      TicTacTorInteractor interactor,
      TicTacTorBuilder.Component component) {
    super(view, interactor, component);
  }
}
