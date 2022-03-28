package com.example.tryapp

import androidx.room.*

@Entity // Описание таблиц
data class LocalModel(
    val whole1 : String ,
    val denominator1 : String,
    val numerator1: String,
    val whole2 : String ,
    val denominator2 : String,
    val numerator2: String ,
    val wholeRes : String ,
    val denominatorRes : String,
    val numeratorRes: String

    ){
    @PrimaryKey(autoGenerate = true) var id : Int? = null // Первичный ключ id
}

@Dao // Методы бд
interface LocalDao {

    @Insert // Ввод объекта
    fun insert(list : LocalModel)

    @Query("SELECT * FROM LocalModel") // Вытаскивает объекты из бд
    fun getAll() : LocalModel

    @Query("DELETE FROM LocalModel") // Удаление
    fun deleteAll()

}

@Database(entities = [LocalModel::class], version = 1) // Пометка основного класса бд по работе с бд
abstract class DataBase : RoomDatabase() { // Абстрактный класс с помощью которого идет взаимодействие с бд
    abstract fun localDao() : LocalDao
}