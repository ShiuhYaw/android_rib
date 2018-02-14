package com.example.shiuhyawphang.myrib.root.logged_in.off_game;

import android.support.annotation.Nullable;

import com.example.shiuhyawphang.myrib.root.logged_in.ScoreStream;
import com.google.common.collect.ImmutableMap;
import com.uber.autodispose.ObservableScoper;
import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link OffGameScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class OffGameInteractor
    extends Interactor<OffGameInteractor.OffGamePresenter, OffGameRouter> {

  @Inject @Named("player_one") String playerOne;
  @Inject @Named("player_two") String playerTwo;
  @Inject Listener listener;
  @Inject OffGamePresenter presenter;
  @Inject ScoreStream scoreStream;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);
    presenter.setPlayerName(playerOne, playerTwo);
    presenter
            .startGameRequest()
            .subscribe(new Consumer<Object>() {
              @Override
              public void accept(Object object) throws Exception {
                listener.onStartGame();
              }
            });
    scoreStream.scores()
            .to(new ObservableScoper<ImmutableMap<String, Integer>>(this))
            .subscribe(new Consumer<ImmutableMap<String, Integer>>() {
              @Override
              public void accept(ImmutableMap<String, Integer> scores) throws Exception {
                Integer playerOneScore = scores.get(playerOne);
                Integer playerTwoScore = scores.get(playerTwo);
                presenter.setScores(playerOneScore, playerTwoScore);
              }
            });
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  public interface Listener {

    void onStartGame();
  }
  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface OffGamePresenter {

    void setPlayerName(String playerOne, String playerTwo);
    void setScores(Integer playerOneScore, Integer playerTwoScore);
    Observable<Object> startGameRequest();
  }
}
