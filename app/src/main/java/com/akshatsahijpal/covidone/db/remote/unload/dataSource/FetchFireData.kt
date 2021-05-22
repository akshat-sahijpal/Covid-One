package com.akshatsahijpal.covidone.db.remote.unload.dataSource

import android.util.Log
import com.akshatsahijpal.covidone.data.CovidData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FetchFireData @Inject constructor(private var db: FirebaseFirestore) {
    private var dataSetD = arrayListOf<CovidData>()
    private suspend fun getDataSnapshot(): QuerySnapshot? {
        return try {
            val data = db.collection("covid-store")
                .get()
                .await()
            data
        } catch (e: Exception) {
            null
        }
    }
    // returns the data
    suspend fun generateDataSet(): ArrayList<CovidData> {
        val st = GlobalScope.async {
            val snap: QuerySnapshot? = getDataSnapshot()
            for (i in snap?.documents!!) {
                val myObject = i.toObject(CovidData::class.java)
                if (myObject != null) {
                    dataSetD.add(myObject)
                }
                Log.d("Check This Result->", "${i.toObject(CovidData::class.java)}")
            }
            return@async dataSetD
        }
        return st.await()
    }
}