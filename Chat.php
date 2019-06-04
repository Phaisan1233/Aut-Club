<?php 

    error_reporting(E_ERROR | E_PARSE);

    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];
    
 	//$con = mysqli_connect("localhost","id9336220_autclubdb","software","id9336220_autclubdb")or die('{"success":false}'); 
 	$con = mysqli_connect($db_host,$db_user,$db_password,$db_name)or die('{"success":false}'); 
	
	mysqli_set_charset($con,"utf8");
 
	$sql = "SELECT c.userID ,CONCAT(u.firstName, ' ', u.lastName) AS name, c.message
	        FROM general_chat c LEFT JOIN USER u on c.userID = u.user_ID";
	        //FROM general_chat c ,USER u 
	        //WHERE c.userID = u.user_ID";
 
 	$r = mysqli_query($con,$sql);
 
 	$result = array();
 	$result["success"] = false; 
 	$chatlist = array();

    while($row = mysqli_fetch_array($r))
    {
        $result["success"] = true;
        $chat = array();
        $chat["userID"] = $row['userID'];
	   	$chat["name"] = $row['name'];
	   	$chat["message"] = $row['message'];
	   	
	   	if($row['name'] == null){
	   	    $chat["name"] = "Guest User";
	   	}
	   
       
	   	array_push($chatlist,$chat);
	}

    $result["chatList"] = $chatlist;
 	echo json_encode($result);
 	mysqli_close($con);
 ?>
