package com.ibtikar.myfirstmvvmapplication.model.pojos

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class Item(
                @PrimaryKey @SerializedName("itemName") @Expose var itemName: String = "",
                @SerializedName("category") @Expose var category: String = "",
                @SerializedName("description") @Expose var description: String = "",
                @SerializedName("sort") @Expose var sort: String = "",
                @SerializedName("price") @Expose var price: String = "",
                @SerializedName("image") @Expose var image: String = "") {

    companion object {
        const val TABLE_NAME = "item"
        const val ID = "itemId"
    }
}
