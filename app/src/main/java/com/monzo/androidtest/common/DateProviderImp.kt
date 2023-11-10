package com.monzo.androidtest.common

import java.util.Calendar

class DateProviderImp : DateProvider {
    override fun getCurrentDate(): Calendar {
        return Calendar.getInstance()
    }
}