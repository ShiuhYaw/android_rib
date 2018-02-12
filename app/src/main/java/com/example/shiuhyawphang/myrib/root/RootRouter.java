package com.example.shiuhyawphang.myrib.root;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class RootRouter extends
    ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

  public RootRouter(
      RootView view,
      RootInteractor interactor,
      RootBuilder.Component component) {
    super(view, interactor, component);
  }
}
