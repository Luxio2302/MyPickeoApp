package com.example.mypickeoapp

data class MaterialListResponseItem(
    val box: Int,
    val Nombre: String,
    val employee: Int,
    val id_pickeo: Int,
    val material_number: String,
    val planta: String,
    val po_task_id: Int,
    val qty_to_pick: Int,
    val rack_location: String,
    val requisicion: String,
    val spq_bom_unit: Int,
    val zone_ma: String
)