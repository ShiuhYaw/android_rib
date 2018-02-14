package com.example.shiuhyawphang.myrib.root.logged_out;

import android.support.annotation.Nullable;
import android.util.Log;
import android.support.v4.util.Pair;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutBuilder.LoggedOutScope;
import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link LoggedOutScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class LoggedOutInteractor
    extends Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter> {

    @Inject Listener listener;
    @Inject LoggedOutPresenter presenter;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    presenter
            .playerNames()
            .subscribe(new Consumer<Pair<String, String>>() {
                @Override
                public void accept(Pair<String, String> names) throws Exception {
                    if (!isEmpty(names.first) && !isEmpty(names.second)) {
                        listener.requestLogin(names.first, names.second);
                    }
                }
            });
    // TODO: Add attachment logic here (RxSubscriptions, etc.).
  }

  private boolean isEmpty(@Nullable String string) {
      return string == null || string.length() == 0;
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedOutPresenter {
    Observable<Pair<String, String>> playerNames();
  }

  public interface Listener {

      void requestLogin(String playerOne, String playerTwo);
  }
}
