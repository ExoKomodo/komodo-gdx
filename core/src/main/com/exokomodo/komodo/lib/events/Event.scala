package com.exokomodo.komodo.lib.events

import java.util.UUID

abstract class Event {
    val id: EventId = getNextId()
}