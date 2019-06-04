<?php
    error_reporting(E_ERROR | E_PARSE);

    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];

    //$connect = mysqli_connect("localhost","id9336220_autclubdb","software","id9336220_autclubdb");

    $connect = mysqli_connect($db_host,$db_user,$db_password,$db_name)or die('{"success":false}');

    $username = $_POST["username"];
    $password = $_POST["password"];

    $sql = "SELECT u.user_ID, u.userName, u.password, u.firstName, u.lastName, u.email,u.timeStamp
    FROM USER u
    WHERE u.userName = ?";
    
    $statement = mysqli_prepare($connect, $sql);
    mysqli_stmt_bind_param($statement,"s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_bind_result($statement, $user_ID, $userName, $passwordHash, $firstName, $lastName , $email, $timeStamp);
    
    $response = array();
    $response["success"] = false;  

    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $passwordHash)) {
            $response["success"] = true;  
            $response["userID"] = $user_ID;
            $response["userName"] = $userName;
            $response["firstName"] = $firstName;
            $response["lastName"] = $lastName;
            $response["email"] = $email;
            $response["time"] = $timeStamp;
        }
    }
    if($user_ID == null){
        $user_ID = 0;
    }
    $sql2 = "SELECT f.connectID,c.club_ID,c.tokens,c.name,c.Description,f.followStatus,f.joinStatus 
            FROM CLUB c LEFT JOIN following f on c.club_ID = f.club_ID AND f.user_ID =".$user_ID;
    
    $statement2 = mysqli_prepare($connect, $sql2);
    mysqli_stmt_execute($statement2);
    
    mysqli_stmt_bind_result($statement2,$connectID, $club_ID ,$tokens ,$name,$Description,$followStatus,$joinStatus);
    
    $clubList = array();
    while(mysqli_stmt_fetch($statement2)){
        $club = array();
        $club["connectID"]=$connectID;
        $club["clubID"]=$club_ID;
        $club["name"]=$name;
	   	$club["tokens"]=$tokens;
	   	$club["description"]=$Description;
		$club["followStatus"]=true;
		$club["joinStatus"]=true;

        if($followStatus == null || $followStatus == 0)
		{
		    $club["followStatus"] = false;
	   	}
	   	if ($joinStatus == null || $joinStatus == 0)
	   	{
	   	    $club["joinStatus"] = false;
	    }
	   	array_push($clubList,$club);
    }
    $response['clubList'] = $clubList;
    mysqli_stmt_close($statement);
    mysqli_stmt_close($statement2);
    echo json_encode($response);
?>