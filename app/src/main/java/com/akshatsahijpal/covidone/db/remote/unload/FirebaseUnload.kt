package com.akshatsahijpal.covidone.db.remote.unload

import android.util.Log
import com.akshatsahijpal.covidone.data.CovidData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FirebaseUnload {
    private var db = FirebaseFirestore.getInstance()
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

    suspend fun generateDataSet(): ArrayList<CovidData> {
        var st = GlobalScope.async {
            var snap: QuerySnapshot? = getDataSnapshot()
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