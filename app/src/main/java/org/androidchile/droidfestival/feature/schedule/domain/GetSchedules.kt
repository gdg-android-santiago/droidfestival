package org.androidchile.droidfestival.feature.schedule.domain

import org.androidchile.droidfestival.fundation.interactor.UseCase
import org.androidchile.droidfestival.fundation.interactor.UseCase.None

import javax.inject.Inject

class GetSchedules
@Inject constructor(private val scheduleRepository: ScheduleRepository) : UseCase<List<Schedule>, None>() {

    override suspend fun run(params: None) = scheduleRepository.schedules()
}