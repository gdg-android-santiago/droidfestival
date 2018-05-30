package org.androidchile.droidfestival.feature.schedule.presentation

import android.os.Parcel
import org.androidchile.droidfestival.fundation.platform.KParcelable
import org.androidchile.droidfestival.fundation.platform.parcelableCreator

data class ScheduleView(val id: Int, val title: String, val description: String) : KParcelable {
    companion object {
        @JvmField val CREATOR = parcelableCreator(::ScheduleView)
    }

    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(title)
            writeString(description)
        }
    }
}