package org.androidchile.droidfestival.feature.schedule.domain

import org.androidchile.droidfestival.fundation.extension.empty

data class Schedule(val id: Int, val title: String, val description: String) {

    companion object {
        fun empty() = Schedule(0, String.empty(), String.empty())
    }
}