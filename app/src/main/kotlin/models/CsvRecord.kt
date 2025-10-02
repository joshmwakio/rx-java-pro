package org.rx.app.models

data class CsvRecord(
    val id: Int,
    val name: String,
    val email: String,
    val department: Int,
    val salary: Double
)
