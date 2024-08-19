package cg.tutorials.tutionflow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class BatchData(
    @PrimaryKey(autoGenerate = true)
    var batchId:Int=0,
    var batchName:String,
    var batchClass:String,
    var startTime:String,
    var endTime:String
)
