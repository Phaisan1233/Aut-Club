<?php
    //error_reporting(E_ERROR | E_PARSE);

    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];
    $userID = $_POST["userID"];
    $message = $_POST["message"];

    //$connect = mysqli_connect("localhost","id9336220_autclubdb","software","id9336220_autclubdb");
    //$userID = 8;
    //$message = "hello world";

    $connect = mysqli_connect($db_host,$db_user,$db_password,$db_name) or die('{"success":false}');

    $sql = "INSERT INTO `general_chat` (`userID`, `message`) VALUES (?, ?)";

    $statement = mysqli_prepare($connect, $sql);
    mysqli_stmt_bind_param($statement,"ss", $userID,$message);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_close($statement);
?>

