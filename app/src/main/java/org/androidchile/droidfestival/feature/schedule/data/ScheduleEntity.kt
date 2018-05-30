package org.androidchile.droidfestival.feature.schedule.data

import org.androidchile.droidfestival.feature.schedule.domain.Schedule

data class ScheduleEntity(val id: Int, val title: String, val description: String) {
    fun toSchedule() = Schedule(id, title, description)
}