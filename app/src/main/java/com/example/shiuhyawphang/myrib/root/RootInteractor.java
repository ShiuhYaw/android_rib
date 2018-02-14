package com.example.shiuhyawphang.myrib.root;

import android.support.annotation.Nullable;

import com.example.shiuhyawphang.myrib.root.logged_in.LoggedInInteractor;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutInteractor;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.core.Presenter;
import com.uber.rib.core.Router;

import javax.inject.Inject;

/**
 * Coordinates Business Logic for {@link RootScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class RootInteractor
    extends Interactor<RootInteractor.RootPresenter, RootRouter> {

  @Inject RootPresenter presenter;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    getRouter().attachLoggedOut();
    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  class LoggedOutListener implements LoggedOutInteractor.Listener {

    @Override
    public void requestLogin(String playerOne, String playerTwo) {
      getRouter().detachLoggedOut();
      getRouter().attachLoggedIn(playerOne, playerTwo);
    }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface RootPresenter { }
}
