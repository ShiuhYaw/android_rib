package com.example.shiuhyawphang.myrib.root.logged_in.tic_tac_toe

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TicTacTorInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: TicTacTorInteractor.TicTacTorPresenter
  @Mock internal lateinit var router: TicTacTorRouter

  private var interactor: TicTacTorInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestTicTacTorInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<TicTacTorInteractor.TicTacTorPresenter, TicTacTorRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}