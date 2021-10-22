package com.android.market.data

import java.security.acl.Owner

data class Product(
    val productName: String,
    val category: String,
    val url: String,
    val stock: Int,
    val id: String,
    val detail: String,
    val price: String
)

