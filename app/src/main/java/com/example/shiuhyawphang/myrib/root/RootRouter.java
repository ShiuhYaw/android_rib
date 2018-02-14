package com.example.shiuhyawphang.myrib.root;

import android.support.annotation.Nullable;

import com.uber.rib.core.ViewRouter;
import com.example.shiuhyawphang.myrib.root.logged_in.LoggedInBuilder;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutBuilder;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutRouter;
/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class RootRouter extends
        ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

  private final LoggedInBuilder loggedInBuilder;
  private final LoggedOutBuilder loggedOutBuilder;
  @Nullable private LoggedOutRouter loggedOutRouter;

  public RootRouter(
      RootView view,
      RootInteractor interactor,
      RootBuilder.Component component,
      LoggedOutBuilder loggedOutBuilder,
      LoggedInBuilder loggedInBuilder) {
    super(view, interactor, component);
    this.loggedOutBuilder = loggedOutBuilder;
    this.loggedInBuilder = loggedInBuilder;
  }

  void attachLoggedOut() {
    loggedOutRouter = loggedOutBuilder.build(getView());
    attachChild(loggedOutRouter);
    getView().addView(loggedOutRouter.getView());
  }

  public void detachLoggedOut() {
    if (loggedOutRouter != null) {
      detachChild(loggedOutRouter);
      getView().removeView(loggedOutRouter.getView());
      loggedOutRouter = null;
    }
  }

  public void attachLoggedIn(String playerOne, String playerTwo) {
    attachChild(loggedInBuilder.build(playerOne, playerTwo));
  }
}
