<?php

include 'databaseConfig.php';

$userName = $_POST['username'];

$conn = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$stmt = $conn->prepare("SELECT * FROM empleados WHERE codigo_persona='$userName'");

$stmt->execute();

$stmt->store_result();

$rows = $stmt->num_rows;

if($rows == 0) {
    echo json_encode("error");
} else {
    echo json_encode("ok");
}