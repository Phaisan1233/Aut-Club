<?php
    error_reporting(E_ERROR | E_PARSE);

    $db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];
    

    $connect=mysqli_connect($db_host,$db_user,$db_password,$db_name);

    $userName = $_POST["username"];
    $password = $_POST["password"];
    $firstName = $_POST["firstName"];
    $lastName = $_POST["lastName"];
    $email = $_POST["email"];
    
    function usernameAvailable() {
        global $connect, $userName;
        $sql = "SELECT * FROM USER WHERE userName = ?";
        $statement = mysqli_prepare($connect, $sql); 
        mysqli_stmt_bind_param($statement, "s", $userName);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    
    function registerUser() {
        global $connect, $userName, $password, $firstName, $lastName, $email;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $sql = "INSERT INTO USER (userName, password, firstName, lastName, email) VALUES (?, ?, ?, ?, ?)";
        $statement = mysqli_prepare($connect, $sql);
        mysqli_stmt_bind_param($statement, "sssss", $userName, $passwordHash, $firstName, $lastName, $email);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
        
    $response = array();
    $response["success"] = false;  
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>