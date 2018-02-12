package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Top level view for {@link TicTacTorBuilder.TicTacTorScope}.
 */
class TicTacTorView extends View implements TicTacTorInteractor.TicTacTorPresenter {

  public TicTacTorView(Context context) {
    this(context, null);
  }

  public TicTacTorView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TicTacTorView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}
