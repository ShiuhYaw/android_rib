package com.example.shiuhyawphang.myrib.root;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.shiuhyawphang.myrib.root.logged_in.LoggedInBuilder;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutInteractor;
import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;
import com.example.shiuhyawphang.myrib.R;
import com.example.shiuhyawphang.myrib.root.logged_out.LoggedOutBuilder;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import dagger.Provides;
import dagger.Binds;
import dagger.BindsInstance;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class RootBuilder
    extends ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent> {

  public RootBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link RootRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link RootRouter}.
   */
  public RootRouter build(ViewGroup parentViewGroup) {
    RootView view = createView(parentViewGroup);
    RootInteractor interactor = new RootInteractor();
    Component component = DaggerRootBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.rootRouter();
  }

  @Override
  protected RootView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return (RootView)inflater.inflate(R.layout.activity_main, parentViewGroup,false);
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  public abstract static class Module {

    @RootScope
    @Provides
    static LoggedOutInteractor.Listener loggedOutListener(RootInteractor rootInteractor) {
      return rootInteractor.new LoggedOutListener();
    }

    @RootScope
    @Binds
    abstract RootInteractor.RootPresenter presenter(RootView view);

    @RootScope
    @Provides
    static RootRouter router(
      Component component,
      RootView view,
      RootInteractor interactor) {
      return new RootRouter(
              view,
              interactor,
              component,
              new LoggedOutBuilder(component),
              new LoggedInBuilder(component));
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RootScope
  @dagger.Component(
          modules = Module.class,
       dependencies = ParentComponent.class
  )
  interface Component extends InteractorBaseComponent<RootInteractor>, LoggedOutBuilder.ParentComponent, LoggedInBuilder.ParentComponent,  BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(RootInteractor interactor);
      @BindsInstance
      Builder view(RootView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    RootRouter rootRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface RootScope { }
}
