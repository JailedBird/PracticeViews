package com.hencoder.hencoderpracticedraw4.practice

import android.graphics.Canvas


inline fun Canvas.ok(block: () -> Unit) {
    save()
    block.invoke()
    restore()
}