<?php
    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];

    $connect=mysqli_connect($db_host,$db_user,$db_password,$db_name)or die('{"success":false}');

    $userName = $_POST["username"];
    $password = $_POST["password"];
   
    function checkUsername() {
        global $connect, $userName;
        $statement = mysqli_prepare($connect, "SELECT * FROM USER WHERE userName = ?"); 
        mysqli_stmt_bind_param($statement, "s", $userName);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return false; 
        }else {
            return true; 
        }
    }
    
    function resetPassword() {
        global $connect, $userName, $password;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $sql = "UPDATE USER SET password = ? WHERE userName = ?";
        $statement = mysqli_prepare($connect, $sql);
        mysqli_stmt_bind_param($statement, "ss", $passwordHash, $userName);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
    
    $response = array();
    $response["success"] = false;  
    if (checkUsername()){
        resetPassword();
        $response["success"] = true;  
    }
    echo json_encode($response);
?>