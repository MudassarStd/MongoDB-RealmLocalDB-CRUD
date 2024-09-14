package com.android.mongodbcrud.model

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class BasicInfo : RealmObject {
    @PrimaryKey
    var id : ObjectId = ObjectId.invoke()
    var name : String = ""
    var age : Int = 0
    var registrationTime : RealmInstant = RealmInstant.now()
}