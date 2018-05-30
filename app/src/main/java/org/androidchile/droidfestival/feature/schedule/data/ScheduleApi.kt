package org.androidchile.droidfestival.feature.schedule.data

import retrofit2.Call
import retrofit2.http.GET

internal interface ScheduleApi {
    companion object {
        private const val PARAM_ID = "Id"
        private const val SCHEDULES = "SCHEDULES.json"
    }

    @GET(SCHEDULES) fun schedules(): Call<List<ScheduleEntity>>
}
