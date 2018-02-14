package com.example.shiuhyawphang.myrib.root.logged_in;

import android.view.ViewGroup;
import com.uber.rib.core.Router;
import com.example.shiuhyawphang.myrib.root.logged_in.off_game.OffGameBuilder;
import com.example.shiuhyawphang.myrib.root.logged_in.off_game.OffGameRouter;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.TicTacTorBuilder;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.TicTacTorRouter;

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class LoggedInRouter extends
    Router< LoggedInInteractor, LoggedInBuilder.Component> {

  private final ViewGroup parentView;
  private final OffGameBuilder offGameBuilder;
  private final TicTacTorBuilder ticTacTorBuilder;
  private OffGameRouter offGameRouter;
  private TicTacTorRouter ticTacTorRouter;

  public LoggedInRouter(
      LoggedInInteractor interactor,
      LoggedInBuilder.Component component,
      ViewGroup parentView,
      OffGameBuilder offGameBuilder,
      TicTacTorBuilder ticTacTorBuilder) {
    super(interactor, component);
    this.parentView = parentView;
    this.offGameBuilder = offGameBuilder;
    this.ticTacTorBuilder = ticTacTorBuilder;
  }

  @Override
  protected void willDetach() {
    super.willDetach();
    detachOffGame();
    detachTicTacTor();
  }

  void attachOffGame() {
    offGameRouter = offGameBuilder.build(parentView);
    attachChild(offGameRouter);
    parentView.addView(offGameRouter.getView());
  }

  void detachOffGame() {
    if (offGameRouter != null) {
      detachChild(offGameRouter);
      parentView.removeView(offGameRouter.getView());
      offGameRouter = null;
    }
  }

  void attachTicTacTor() {
    ticTacTorRouter = ticTacTorBuilder.build(parentView);
    attachChild(ticTacTorRouter);
    parentView.addView(ticTacTorRouter.getView());
  }

  void detachTicTacTor() {
    if (ticTacTorRouter != null) {
      detachChild(ticTacTorRouter);
      parentView.removeView(ticTacTorRouter.getView());
      ticTacTorRouter = null;
    }
  }
}