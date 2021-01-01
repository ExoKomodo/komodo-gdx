package com.exokomodo.komodo.lib

import java.util.UUID

package object events {
  type EventId = UUID

  def getNextId(): EventId = UUID.randomUUID()
}
