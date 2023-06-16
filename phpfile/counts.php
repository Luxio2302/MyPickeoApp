<?php

include 'databaseConfig.php';

//$userName = $_POST['username'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("SELECT picking.status, COUNT(*) FROM picking,workin,empleados WHERE workin.employee='115774' AND workin.employee = 
                        empleados.codigo_persona AND left(rack_location,1) = workin.plant AND date(picking.date_register) = date(NOW()) 
                        AND date(workin.date_asign) = date(NOW()) GROUP BY picking.status ASC");

$stmt->execute();

$stmt->store_result();

    //executing the query
    
    $stmt->bind_result($status,$count);

    $cou = array();
    
    //traversing through all the result
    
    while($stmt->fetch()){

        $temp = array();
        $temp['status'] = $status;
        $temp['COUNT(*)'] = $count;
        array_push($cou, $temp);

    }

echo json_encode($cou);

?>
