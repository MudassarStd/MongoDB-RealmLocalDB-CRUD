package com.android.mongodbcrud.repository

import android.util.Log
import com.android.mongodbcrud.model.BasicInfo
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepository @Inject constructor(private val realm : Realm) {

    fun getData(): List<BasicInfo> {
        return realm.query<BasicInfo>().find()
    }
    suspend fun insertBasicInfo(info : BasicInfo) {
        realm.write { copyToRealm(info) }
        Log.d("MongoRealmCRUDTesting", "Inserted Successfully")
    }

    suspend fun updateBasicInfo(info : BasicInfo) {
        realm.write {
            val querifiedRecord = query<BasicInfo>(query = "id == $0", info.id).first().find()
            querifiedRecord?.let {
                it.name = info.name
            } ?: Log.d("MongoRealmCRUDTesting", "Record not found")
        }
    }

    suspend fun deleteBasicInfo(info : BasicInfo) {
        realm.write {
            val foundRecord = query<BasicInfo>(query = "id == $0", info.id).first().find()
            try {
                foundRecord?.let {
                    delete(it)
                }
            } catch (e : Exception) {
                Log.e("MongoRealmCRUDTesting", "${e.message}")
            }
        }
    }

    suspend fun deleteAllBasicInfo() {
        realm.write {
            delete(query<BasicInfo>())
        }
    }
}