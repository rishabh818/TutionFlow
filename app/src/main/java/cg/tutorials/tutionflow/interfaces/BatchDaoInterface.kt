package cg.tutorials.tutionflow.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cg.tutorials.tutionflow.BatchData

@Dao
interface BatchDaoInterface {

    @Query("Select * from BatchData")
    fun getBatches():List<BatchData>

    @Insert
    fun addBatch(batchData: BatchData)

    @Delete
    fun deleteBatch(batchData: BatchData)

    @Update
    fun updateBatch(batchData: BatchData)

}