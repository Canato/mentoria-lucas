package com.monzo.androidtest.common

import java.util.Calendar

interface DateProvider {
    fun getCurrentDate(): Calendar
}
