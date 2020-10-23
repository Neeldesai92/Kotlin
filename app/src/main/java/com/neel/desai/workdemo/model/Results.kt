package com.neel.desai.workdemo.model

import androidx.annotation.Nullable
import androidx.room.*
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity
public data class Results(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PersonId")
    @SerializedName("PersonId") val PersonId: Int,

    @ColumnInfo(name = "gender")
    @SerializedName("gender") val gender: String,


    @Embedded
    @SerializedName("name") val name: Name,

    @Embedded
    @SerializedName("location") val location: Location,


    @ColumnInfo(name = "email")
    @SerializedName("email") val email: String,

    @Embedded
    @SerializedName("login") val login: Login,

    @Embedded
    @SerializedName("dob") val dob: Dob,
    @Embedded
    @SerializedName("registered") val registered: Registered,


    @ColumnInfo(name = "phone")
    @SerializedName("phone") val phone: String,


    @ColumnInfo(name = "cell")
    @SerializedName("cell") val cell: String,

    @Embedded
    @SerializedName("id") val id: Id,


    @Embedded
    @SerializedName("picture") val picture: Picture,


    @SerializedName("nat") val nat: String,


    @ColumnInfo(name = "RequestFlg")
    @SerializedName("RequestFlg") var RequestFlg: String = ""


)