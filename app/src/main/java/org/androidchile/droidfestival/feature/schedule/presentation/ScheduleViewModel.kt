package org.androidchile.droidfestival.feature.schedule.presentation

import android.arch.lifecycle.MutableLiveData
import org.androidchile.droidfestival.feature.schedule.domain.GetSchedules
import org.androidchile.droidfestival.feature.schedule.domain.Schedule
import org.androidchile.droidfestival.fundation.interactor.UseCase.None
import org.androidchile.droidfestival.fundation.platform.BaseViewModel

import javax.inject.Inject

class ScheduleViewModel
@Inject constructor(private val getSchedules: GetSchedules) : BaseViewModel() {

    var schedules: MutableLiveData<List<ScheduleView>> = MutableLiveData()

    fun loadSchedules() = getSchedules.execute({ it.either(::handleFailure, ::handleScheduleList) }, None())

    private fun handleScheduleList(schedules: List<Schedule>) {
        this.schedules.value = schedules.map { ScheduleView(it.id, it.title, it.description) }
    }
}