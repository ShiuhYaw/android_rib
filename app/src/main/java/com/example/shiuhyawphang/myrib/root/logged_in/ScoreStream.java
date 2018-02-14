package com.example.shiuhyawphang.myrib.root.logged_in;

/**
 * Created by shiuhyawphang on 14/2/18.
 */
import com.google.common.collect.ImmutableMap;
import io.reactivex.Observable;

public interface ScoreStream {
    Observable<ImmutableMap<String, Integer>> scores();
}
