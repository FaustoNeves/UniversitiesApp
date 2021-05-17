package br.com.fausto.institutions_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.fausto.institutions_app.model.University

@Dao
interface UniversityDao {

    @Insert(onConflict = REPLACE)
    fun save(university: University)

    @Query("SELECT * FROM University LIMIT 1000")
    fun loadAll(): MutableList<University>

    @Query("DELETE FROM University")
    fun clearTable()

}