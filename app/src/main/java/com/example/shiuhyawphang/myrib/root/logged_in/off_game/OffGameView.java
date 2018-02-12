package com.example.shiuhyawphang.myrib.root.logged_in.off_game;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
class OffGameView extends View implements OffGameInteractor.OffGamePresenter {

  public OffGameView(Context context) {
    this(context, null);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}
