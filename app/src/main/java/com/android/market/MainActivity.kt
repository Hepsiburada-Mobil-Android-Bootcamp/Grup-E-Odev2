package com.android.market

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.market.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.market.api.RetrofitService
import com.android.market.data.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}

