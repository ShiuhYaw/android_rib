package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.example.shiuhyawphang.myrib.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link TicTacTorBuilder.TicTacTorScope}.
 */
class TicTacTorView extends PercentRelativeLayout implements TicTacTorInteractor.TicTacTorPresenter {

    private TextView[][] imageButtons;
    private TextView titleView;

    public TicTacTorView(Context context) {
    this(context, null);
  }

    public TicTacTorView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

    public TicTacTorView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Initializer
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageButtons = new TextView[3][];
        imageButtons[0] =
                new TextView[] {
                        (TextView) findViewById(R.id.button11),
                        (TextView) findViewById(R.id.button12),
                        (TextView) findViewById(R.id.button13)
                };
        imageButtons[1] =
                new TextView[] {
                        (TextView) findViewById(R.id.button21),
                        (TextView) findViewById(R.id.button22),
                        (TextView) findViewById(R.id.button23)
                };
        imageButtons[2] =
                 new TextView[] {
                         (TextView) findViewById(R.id.button31),
                         (TextView) findViewById(R.id.button32),
                         (TextView) findViewById(R.id.button33)
                 };
        titleView = (TextView) findViewById(R.id.title);
    }

    @Override
    public Observable<BoardCoordinate> squareClicks() {
        ArrayList<Observable<BoardCoordinate>> observables = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                observables.add(
                        RxView.clicks(imageButtons[i][j]).map(
                                new Function<Object, BoardCoordinate>() {
                                    @Override
                                    public BoardCoordinate apply(Object o) throws Exception {
                                        return new BoardCoordinate(finalI, finalJ);
                                    }
                                }
                        )
                );
            }
        }

        return Observable.merge(observables);
    }

    @Override
    public void addCross(BoardCoordinate xy) {
        TextView textView = imageButtons[xy.getX()][xy.getY()];
        textView.setText("x");
        textView.setClickable(false);
    }

    @Override
    public void addNought(BoardCoordinate xy) {
        TextView textView = imageButtons[xy.getX()][xy.getY()];
        textView.setText("0");
        textView.setClickable(false);
    }

    @Override
    public void setCurrentPlayerName(String playerName) {
        titleView.setText("Current Player: " + playerName);
    }

    @Override
    public void setPlayerWon(String playerName) {
        titleView.setText("Player won: " + playerName + "!!!");
    }

    @Override
    public void setPlayerTie() {
        titleView.setText("Tie game!");
    }
}
