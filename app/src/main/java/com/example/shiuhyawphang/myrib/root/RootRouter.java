package com.example.shiuhyawphang.myrib.root;

import android.support.annotation.Nullable;

import com.uber.rib.core.ViewRouter;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutBuilder;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutRouter;
/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class RootRouter extends
        ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

  private final LoggedOutBuilder loggedOutBuilder;
  @Nullable private LoggedOutRouter loggedOutRouter;

  public RootRouter(
      RootView view,
      RootInteractor interactor,
      RootBuilder.Component component,
      LoggedOutBuilder loggedOutBuilder) {
    super(view, interactor, component);
    this.loggedOutBuilder = loggedOutBuilder;
  }

  void attachLoggedOut() {
    loggedOutRouter = loggedOutBuilder.build(getView());
    attachChild(loggedOutRouter);
    getView().addView(loggedOutRouter.getView());
  }

  public void attachLoggedIn() {

  }

  public void detachLoggedOut() {

  }
}
