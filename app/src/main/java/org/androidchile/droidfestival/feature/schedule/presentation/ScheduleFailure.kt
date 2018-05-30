package org.androidchile.droidfestival.feature.schedule.presentation

import org.androidchile.droidfestival.fundation.exception.Failure.FeatureFailure

class ScheduleFailure {
    class ListNotAvailable: FeatureFailure()
    class NonExistentMovie: FeatureFailure()
}