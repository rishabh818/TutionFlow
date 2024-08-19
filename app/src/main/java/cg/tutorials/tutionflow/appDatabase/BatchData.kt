package cg.tutorials.tutionflow.appDatabase

import java.sql.Time

data class BatchData(
    var id: Int,
    var batchName: String,
    var batchClass: String,
    var batchTiming: Time,
)
