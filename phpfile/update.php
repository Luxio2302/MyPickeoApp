<?php

include 'databaseConfig.php';

//$idReg = $_POST['idreg'];
//$Status = $_POST['status'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("UPDATE picking SET status = 'Encontrado' 
                        WHERE id_pickeo = '27'");

$stmt->execute();

$stmt->store_result();

$rows = $stmt->num_rows;

if($rows == 0 ) {
    echo json_encode("error");
} else {
    echo json_encode("ok");
}