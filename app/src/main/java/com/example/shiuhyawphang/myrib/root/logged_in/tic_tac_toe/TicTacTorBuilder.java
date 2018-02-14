package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;
import com.example.shiuhyawphang.myrib.R;

import java.lang.annotation.Retention;

import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Qualifier;

import dagger.Provides;
import dagger.Binds;
import dagger.BindsInstance;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Builder for the {@link TicTacTorScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class TicTacTorBuilder
    extends ViewBuilder<TicTacTorView, TicTacTorRouter, TicTacTorBuilder.ParentComponent> {

  public TicTacTorBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link TicTacTorRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link TicTacTorRouter}.
   */
  public TicTacTorRouter build(ViewGroup parentViewGroup) {
    TicTacTorView view = createView(parentViewGroup);
    TicTacTorInteractor interactor = new TicTacTorInteractor();
    Component component = DaggerTicTacTorBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.tictactorRouter();
  }

  @Override
  protected TicTacTorView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return (TicTacTorView) inflater.inflate(R.layout.tic_tac_toe_rib, parentViewGroup, false);
  }

  public interface ParentComponent {

    TicTacTorInteractor.Listener ticTacTorListener();
    @Named("player_one") String playerOne();
    @Named("player_two") String playerTwo();
  }

  @dagger.Module
  public abstract static class Module {

    @TicTacTorScope
    @Binds
    abstract TicTacTorInteractor.TicTacTorPresenter presenter(TicTacTorView view);

    @TicTacTorScope
    @Provides
    static TicTacTorRouter router(
      Component component,
      TicTacTorView view,
      TicTacTorInteractor interactor) {
      return new TicTacTorRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @TicTacTorScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<TicTacTorInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(TicTacTorInteractor interactor);
      @BindsInstance
      Builder view(TicTacTorView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    TicTacTorRouter tictactorRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface TicTacTorScope { }

  @Qualifier
  @Retention(CLASS)
  @interface TicTacTorInternal { }
}
