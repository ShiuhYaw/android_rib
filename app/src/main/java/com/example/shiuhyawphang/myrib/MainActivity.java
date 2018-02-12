package com.example.shiuhyawphang.myrib;

import android.os.Bundle;

import android.view.ViewGroup;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;
import com.example.shiuhyawphang.myrib.root.RootBuilder;

public class MainActivity extends RibActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {
        RootBuilder rootBuilder = new RootBuilder(new RootBuilder.ParentComponent() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        return rootBuilder.build(parentViewGroup);
    }
}
