package com.example.shiuhyawphang.myrib.root.logged_out;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.example.shiuhyawphang.myrib.R;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
public class LoggedOutView extends LinearLayout implements LoggedOutInteractor.LoggedOutPresenter {

    private Button loginButton;
    private EditText playerOneEditText;
    private EditText playerTwoEditText;

  public LoggedOutView(Context context) {
    this(context, null);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        playerOneEditText = (EditText) findViewById(R.id.player_one_name);
        playerTwoEditText = (EditText) findViewById(R.id.player_two_name);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    @Override
    public Observable<Pair<String, String>> playerNames() {

      return RxView.clicks(findViewById(R.id.login_button))
              .map(new Function<Object, Pair<String,String>>() {
                  @Override
                  public Pair<String, String> apply(Object o) throws Exception {
                      return new Pair<>(
                              playerOneEditText.getText().toString(), playerTwoEditText.getText().toString()
                      );
                  }
              });
    }
}
