package com.exokomodo.komodo.desktop.behaviors

import com.exokomodo.komodo.ecs.components.TypeId
import com.exokomodo.komodo.ecs.entities.Entity
import com.exokomodo.komodo.ecs.components.AsyncBehaviorComponent

object AsyncTestBehavior {
    def apply(parent: Entity, isEnabled: Boolean = true): AsyncTestBehavior = {
        new AsyncTestBehavior(
            Some(parent),
            isEnabled=isEnabled,
        )
    }
}

@TypeId(id = AsyncBehaviorComponent.typeId)
class AsyncTestBehavior(
    override val parent: Option[Entity],
    override var isEnabled: Boolean = true,
) extends AsyncBehaviorComponent(parent) {
    private var _result: List[Int] = List.empty[Int]

    override protected def _consume(): Unit = {
        println(_result)
    }
    
    override protected def _produce(): Unit = {
        println("Producing...")
        _result = List(1, 2, 3)
        Thread.sleep(5000)
    }
}