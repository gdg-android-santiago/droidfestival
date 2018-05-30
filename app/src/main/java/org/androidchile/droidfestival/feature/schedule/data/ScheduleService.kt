package org.androidchile.droidfestival.feature.schedule.data

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleService
@Inject constructor(retrofit: Retrofit) : ScheduleApi {
    private val scheduleApi by lazy { retrofit.create(ScheduleApi::class.java) }

    override fun schedules() = scheduleApi.schedules()
}