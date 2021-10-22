package com.android.market.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val productName:String,val category:String,val url:String,val stock:Int,val id:String,val detail:String,val price:String):Parcelable
