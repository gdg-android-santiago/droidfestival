package org.androidchile.droidfestival.feature.schedule.domain

import org.androidchile.droidfestival.feature.schedule.data.ScheduleService
import org.androidchile.droidfestival.fundation.exception.Failure
import org.androidchile.droidfestival.fundation.exception.Failure.NetworkConnection
import org.androidchile.droidfestival.fundation.exception.Failure.ServerError
import org.androidchile.droidfestival.fundation.functional.Either
import org.androidchile.droidfestival.fundation.functional.Either.Left
import org.androidchile.droidfestival.fundation.functional.Either.Right
import org.androidchile.droidfestival.fundation.platform.NetworkHandler

import retrofit2.Call
import javax.inject.Inject

interface ScheduleRepository {
    fun schedules(): Either<Failure, List<Schedule>>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: ScheduleService) : ScheduleRepository {

        override fun schedules(): Either<Failure, List<Schedule>> {
            return when (networkHandler.isConnected) {
                true -> request(service.schedules(), { it.map { it.toSchedule() } }, emptyList())
                false, null -> Left(NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Right(transform((response.body() ?: default)))
                    false -> Left(ServerError())
                }
            } catch (exception: Throwable) {
                Left(ServerError())
            }
        }
    }
}
