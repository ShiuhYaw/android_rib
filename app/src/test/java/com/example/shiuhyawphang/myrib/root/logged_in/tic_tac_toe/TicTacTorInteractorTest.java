package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.InteractorHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TicTacTorInteractorTest extends RibTestBasePlaceholder {

  @Mock TicTacTorInteractor.TicTacTorPresenter presenter;
  @Mock TicTacTorRouter router;

  private TicTacTorInteractor interactor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    interactor = TestTicTacTorInteractor.create(presenter);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    InteractorHelper.detach(interactor);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
