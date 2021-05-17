package br.com.fausto.institutions_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class University(
        var alpha_two_code: String?,
        var country: String?,
        //var domains: List<String>?,
        @PrimaryKey
        var name: String,
        //var state_province: Any? = null,
        var web_pages: List<String>?
) : Serializable