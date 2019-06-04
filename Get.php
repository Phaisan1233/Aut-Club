<?php 

    error_reporting(E_ERROR | E_PARSE);

    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];
    $sql = $_POST["sql"];

 	$con = mysqli_connect($db_host,$db_user,$db_password,$db_name)or die('{"success":false}'); 
    //$con = mysqli_connect("localhost","id9336220_autclubdb","software","id9336220_autclubdb")or die('{"success":false}'); 
	
	mysqli_set_charset($con,"utf8");
 
 	$r = mysqli_query($con,$sql);
 
 	$result = array();
 	$result["success"] = false; 
 	$clubList = array();

    while($row = mysqli_fetch_array($r))
    {
        $result["success"] = true;
        $club = array();
        $club["requestID"] = $row['requestID'];
	   	$club["userID"] = $row['userID'];
	   	$club["Fullname"] = $row['Fullname'];
	   	$club["email"] = $row['email'];
	   	array_push($clubList,$club);
	}

    $result["chatList"] = $clubList;
 	echo json_encode($result);
 	mysqli_close($con);
 ?>
