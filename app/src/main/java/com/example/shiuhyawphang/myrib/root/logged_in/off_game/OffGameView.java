package com.example.shiuhyawphang.myrib.root.logged_in.off_game;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.example.shiuhyawphang.myrib.R;

import java.util.Locale;

import io.reactivex.Observable;

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView extends LinearLayout implements OffGameInteractor.OffGamePresenter {

  private Button button;
  private TextView playerOneName;
  private TextView playerTwoName;
  private TextView playerOneScore;
  private TextView playerTwoScore;


  public OffGameView(Context context) {
    this(context, null);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    button = (Button) findViewById(R.id.start_game_button);
  }

  @Override
  public void setPlayerName(String playerOne, String playerTwo) {
    playerOneName.setText(playerOne);
    playerTwoName.setText(playerTwo);
  }

  @Override
  public void setScores(Integer playerOneScore, Integer playerTwoScore) {
    this.playerOneScore.setText(
            String.format(Locale.getDefault(), "Win Count: %d", playerOneScore));
    this.playerTwoScore.setText(
            String.format(Locale.getDefault(), "Win Count: %d", playerTwoScore));

  }

  @Override
  public Observable<Object> startGameRequest() {
    return RxView.clicks(button);
  }
}
