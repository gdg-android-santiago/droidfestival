package org.androidchile.droidfestival.feature.schedule.presentation

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.fragment_schedule.*
import org.androidchile.droidfestival.R
import org.androidchile.droidfestival.feature.schedule.presentation.ScheduleFailure.ListNotAvailable
import org.androidchile.droidfestival.fundation.exception.Failure
import org.androidchile.droidfestival.fundation.exception.Failure.ServerError
import org.androidchile.droidfestival.fundation.exception.Failure.NetworkConnection
import org.androidchile.droidfestival.fundation.extension.*
import org.androidchile.droidfestival.fundation.platform.BaseFragment
import javax.inject.Inject

class ScheduleFragment : BaseFragment() {

    @Inject lateinit var scheduleAdapter: ScheduleAdapter

    private lateinit var schedulesViewModel: ScheduleViewModel

    override fun layoutId() = R.layout.fragment_schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        schedulesViewModel = viewModel(viewModelFactory) {
            observe(schedules, ::renderScheduleList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadScheduleList()
    }

    private fun initializeView() {
        scheduleList.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        scheduleList.adapter = scheduleAdapter
    }

    private fun loadScheduleList() {
        emptyView.invisible()
        scheduleList.visible()
        showProgress()
        schedulesViewModel.loadSchedules()
    }

    private fun renderScheduleList(schedules: List<ScheduleView>?) {
        scheduleAdapter.collection = schedules.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is ListNotAvailable -> renderFailure(R.string.failure_schedules_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        scheduleList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadScheduleList)
    }
}