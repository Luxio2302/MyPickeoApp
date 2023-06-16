<?php

include 'databaseConfig.php';

$employee = $_POST['username'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("SELECT id_pickeo,employee, Nombre, planta, requisicion, material_number, qty_to_pick, zone_ma, rack_location,
                        po_task_id, spq_bom_unit FROM picking,workin,empleados,std_pack WHERE workin.employee='$employee' AND 
                        workin.employee=empleados.codigo_persona AND std_pack.material_no = picking.material_number AND left(rack_location,1) 
                        = workin.plant AND status = '' AND date(picking.date_register) = date(NOW()) AND date(workin.date_asign) = date(NOW()) 
                        ORDER BY picking.rack_location ASC");

$stmt->execute();

$stmt->store_result();

    //executing the query
$stmt->bind_result($id,$aso, $name, $planta, $requisicion, $material, $qty, $zone, $rack, $po, $stdpack);

$products = array();

//traversing through all the result
while($stmt->fetch()){
    $temp = array();
    $temp['id_pickeo'] = $id;
    $temp['employee'] = $aso;
    $temp['Nombre'] = $name;
    $temp['planta'] = $planta;
    $temp['requisicion'] =$requisicion;
    $temp['material_number'] =$material;
    $temp['qty_to_pick'] =$qty;
    $temp['zone_ma'] = $zone;
    $temp['rack_location'] = $rack;
    $temp['po_task_id'] = $po;
    $temp['spq_bom_unit'] = $stdpack;
    $temp ['box'] = round($qty/$stdpack,0);
    array_push($products, $temp);
}

echo json_encode($products);

?>

