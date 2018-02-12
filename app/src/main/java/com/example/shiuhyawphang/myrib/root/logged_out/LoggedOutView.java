package com.example.shiuhyawphang.myrib.root.logged_out;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shiuhyawphang.myrib.R;
import com.jakewharton.rxbinding2.view.RxView;

import org.w3c.dom.Text;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
public class LoggedOutView extends LinearLayout implements LoggedOutInteractor.LoggedOutPresenter {

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
  public Observable<String> loginName() {
    return RxView.clicks(findViewById(R.id.login_button))
            .map(new Function<Object, String>() {
              @Override
              public String apply(Object o) throws Exception {
                TextView textView = (TextView) findViewById(R.id.edit_text);
                return textView.getText().toString();
              }
            });
  }
}
