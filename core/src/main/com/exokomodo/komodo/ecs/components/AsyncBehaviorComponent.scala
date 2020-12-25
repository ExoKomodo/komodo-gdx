package com.exokomodo.komodo.ecs.components

import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.utilities.ExecutionState
import com.badlogic.gdx.Gdx

object AsyncBehaviorComponent {
  final val typeId = "AsyncBehaviorComponent"
}

@TypeId(id = AsyncBehaviorComponent.typeId)
abstract class AsyncBehaviorComponent(
  override val parent: Option[Entity],
) extends BaseComponent {
  protected final var _state: ExecutionState = ExecutionState.NotStarted
  final def state: ExecutionState = _state

  override def initialize(): Unit = {
    if (isInitialized) return

    super.initialize()
  }

  final def execute(): Unit = {
    _state = ExecutionState.Running
    new Thread(() => {
      _produce()
      Gdx.app.postRunnable(() => {
        _consume()
        _state = ExecutionState.Finished
      })
    }).start()
  }

  override def isReady: Boolean = {
    (super.isReady, _state) match {
      case (true, ExecutionState.NotStarted) => true
      case _ => false
    }
  }

  protected def _consume(): Unit

  protected def _produce(): Unit
}
