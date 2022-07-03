package com.example.mytodolist.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList
import kotlin.random.Random

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object Tasks {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<TaskItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */

    private val COUNT = 10

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addTask(createPlaceholderItem(i))
        }
    }

    fun addTask(item: TaskItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(position: Int): TaskItem {
        val rand: Int = Random.nextInt(AVATAR.values().size)
        return TaskItem(position.toString(), position.toString() + " Contact", " name", "777777777", "01", "01", "2000", AVATAR.values()[rand])
    }

//    private fun makeDetails(position: Int): String {
//        val builder = StringBuilder()
//        builder.append("Details about Item: ").append(position)
//        for (i in 0..position - 1) {
//            builder.append("\nMore details information here.")
//        }
//        return builder.toString()
//    }

    fun updateTask(taskToEdit: TaskItem?, newTask: TaskItem) {
        taskToEdit?.let { oldTask ->
            val indexOfOldTask = ITEMS.indexOf(oldTask)
            ITEMS.add(indexOfOldTask, newTask)
            ITEMS.removeAt(indexOfOldTask+1)
        }
    }

    /**
     * A placeholder item representing a piece of content.
     */
}

data class TaskItem(
    val id: String, val name: String, val surname: String, val phonenumber: String, val birthdateday: String, val birthdatemonth: String, val birthdateyear: String, val avatarid: AVATAR = AVATAR.av1): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        AVATAR.values()[parcel.readInt()]
    ) {

    }

    override fun toString(): String = name
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(phonenumber)
        parcel.writeString(birthdateday)
        parcel.writeString(birthdatemonth)
        parcel.writeString(birthdateyear)
        parcel.writeInt(avatarid.ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskItem> {
        override fun createFromParcel(parcel: Parcel): TaskItem {
            return TaskItem(parcel)
        }

        override fun newArray(size: Int): Array<TaskItem?> {
            return arrayOfNulls(size)
        }
    }
}

enum class AVATAR{
    av1, av2, av3, av4, av5, av6, av7, av8, av9, av10, av11, av12, av13, av14, av15, av16
}
