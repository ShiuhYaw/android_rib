package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.RouterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TicTacTorRouterTest extends RibTestBasePlaceholder {

  @Mock TicTacTorBuilder.Component component;
  @Mock TicTacTorInteractor interactor;
  @Mock TicTacTorView view;

  private TicTacTorRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new TicTacTorRouter(view, interactor, component);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router);
    RouterHelper.detach(router);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
