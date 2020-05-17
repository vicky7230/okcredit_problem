package com.vicky7230.okcredit_problem.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long,

    @ColumnInfo(name = "source")
    var source: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "abstractx")
    var abstractx: String,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "cover_image_url")
    var coverImageUrl: String,

    @ColumnInfo(name = "thumbnail_url")
    var thumbnailImageUrl: String
) : Parcelable