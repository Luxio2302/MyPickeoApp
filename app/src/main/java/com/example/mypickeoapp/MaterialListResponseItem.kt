package com.example.mypickeoapp

data class MaterialListResponseItem(
    val Nombre: String,
    val employee: Int,
    val material_number: String,
    val planta: String,
    val po_task_id: Int,
    val qty_to_pick: Int,
    val rack_location: String,
    val requisicion: String,
    val zone_ma: String
)