package com.example.shiuhyawphang.myrib.root.logged_in;

import static java.lang.annotation.RetentionPolicy.CLASS;

import com.uber.rib.core.Builder;
import com.uber.rib.core.EmptyPresenter;
import com.uber.rib.core.InteractorBaseComponent;
import com.example.shiuhyawphang.myrib.root.RootView;
import com.example.shiuhyawphang.myrib.root.logged_in.off_game.OffGameBuilder;
import com.example.shiuhyawphang.myrib.root.logged_in.off_game.OffGameInteractor;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.TicTacTorBuilder;
import com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe.TicTacTorInteractor;

import dagger.Provides;
import dagger.Binds;
import dagger.BindsInstance;

import java.lang.annotation.Retention;
import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Qualifier;

/**
 * Builder for the {@link LoggedInScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class LoggedInBuilder
    extends Builder<LoggedInRouter, LoggedInBuilder.ParentComponent> {

  public LoggedInBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link LoggedInRouter}.
   *
   * @return a new {@link LoggedInRouter}.
   */
  public LoggedInRouter build(String playerOne, String playerTwo) {
    LoggedInInteractor interactor = new LoggedInInteractor();
    Component component = DaggerLoggedInBuilder_Component.builder()
            .parentComponent(getDependency())
            .interactor(interactor)
            .playerOne(playerOne)
            .playerTwo(playerTwo)
            .build();
    return component.loggedinRouter();
  }

  public interface ParentComponent {

    RootView rootView();
  }

  @dagger.Module
  public abstract static class Module {

    @LoggedInScope
    @Provides
    static EmptyPresenter presenter() {
      return new EmptyPresenter();
    }

    @LoggedInScope
    @Provides
    static LoggedInRouter router(
      Component component,
      LoggedInInteractor interactor,
      RootView rootView) {
      return new LoggedInRouter(
              interactor,
              component,
              rootView,
              new OffGameBuilder(component),
              new TicTacTorBuilder(component));
    }

    @LoggedInScope
    @LoggedInInternal
    @Provides
    static MutableScoreStream mutableScoreStream(
            @Named("player_one") String playerOne,
            @Named("player_two") String playerTwo) {
      return new MutableScoreStream(playerOne, playerTwo);
    }

    @LoggedInScope
    @Provides
    static OffGameInteractor.Listener listener(LoggedInInteractor interactor) {
      return interactor.new OffGameListener();
    }

    @LoggedInScope
    @Provides
    static TicTacTorInteractor.Listener ticTacTorListener(LoggedInInteractor interactor) {
      return interactor.new TicTacTorListener();
    }

    @LoggedInScope
    @Binds
    abstract ScoreStream scoreStream(@LoggedInInternal MutableScoreStream mutableScoreStream);
  }

  @LoggedInScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  public interface Component
          extends InteractorBaseComponent<LoggedInInteractor>,
          BuilderComponent,
          OffGameBuilder.ParentComponent,
          TicTacTorBuilder.ParentComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(LoggedInInteractor interactor);
      Builder parentComponent(ParentComponent component);
      Component build();
      @BindsInstance
      Builder playerOne(@Named("player_one") String playerOne);
      @BindsInstance
      Builder playerTwo(@Named("player_two") String playerTwo);
    }
  }

  interface BuilderComponent  {
    LoggedInRouter loggedinRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface LoggedInScope { }

  @Qualifier
  @Retention(CLASS)
  @interface LoggedInInternal { }
}
