package com.github.mohammadsianaki.core.extensions

import android.net.Uri

fun String.toUri(): Uri = Uri.parse(this)