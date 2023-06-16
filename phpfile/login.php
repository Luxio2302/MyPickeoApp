<?php

include 'databaseConfig.php';

$userName = $_POST['username'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("SELECT codigo_persona, employee, status, date_asign FROM empleados,workin,picking WHERE empleados.codigo_persona = '$userName' 
                        AND workin.employee = '$userName' AND picking.status = '' AND date(workin.date_asign) = date(NOW()) AND left(rack_location,1) 
                        = workin.plant AND date(picking.date_register) = date(NOW())");

$stmt->execute();

$stmt->store_result();

$rows = $stmt->num_rows;

if($rows == 0) {
    echo json_encode("error");
} else {
    echo json_encode("ok");
}