<?php

include 'databaseConfig.php';

$employee = $_POST['username'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("SELECT id_pickeo,employee, Nombre, planta, requisicion, material_number, qty_to_pick, zone_ma, rack_location, po_task_id FROM picking,workin,empleados 
                        WHERE workin.employee='$employee' AND workin.employee=empleados.codigo_persona AND left(rack_location,1) = workin.plant AND date(date_register) = date(NOW()) 
                        AND date(date_asign) = date(NOW()) ORDER BY picking.rack_location ASC");

$stmt->execute();

//executing the query
$stmt->bind_result($id,$aso, $name, $planta, $requisicion, $material, $qty, $zone, $rack, $po);

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
    array_push($products, $temp);
}

echo json_encode($products);

?>

