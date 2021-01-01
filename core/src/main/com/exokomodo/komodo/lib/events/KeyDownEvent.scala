package com.exokomodo.komodo.lib.events

abstract class KeyDownEvent extends Event {
    def handle(keyCode: Int): Boolean
}