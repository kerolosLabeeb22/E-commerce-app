package com.example.domain.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.slidermodel.SliderModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BannerRepository @Inject constructor(
    @ApplicationContext private val context: Context // هتحتاج Inject من Hilt
) {

    fun loadBanner(): LiveData<MutableList<SliderModel>> {

        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        }

        val firebaseDatabase = FirebaseDatabase.getInstance()
        val listData = MutableLiveData<MutableList<SliderModel>>()
        val ref = firebaseDatabase.getReference("Banner")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(SliderModel::class.java)
                    item?.let { lists.add(it) }
                }
                listData.value = lists
                Log.d("DEBUG_FIREBASE", "Fetched ${lists.size} banners from Firebase")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DEBUG_FIREBASE", "Firebase load error: ${error.message}")
            }
        })

        return listData
    }
}
