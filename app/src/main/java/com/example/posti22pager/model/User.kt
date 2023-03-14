package com.example.posti22pager.model

import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val uid: String = "",
    val userName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
  //  val mobile: Long = 0,
    val gender: String = "",
    val moto: String = "החיים זה מה שזה ...",
    val time: Timestamp?=null,
    val profileCompleted: Int = 0,
    val password:String="",
    val gradeArray:ArrayList<Int> = arrayListOf<Int>()
) : Parcelable

