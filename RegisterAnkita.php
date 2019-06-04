<?php
    /*$db_host = $_POST["DB_HOST"];
    $db_user = $_POST["DB_USER"];
    $db_password = $_POST["DB_PASSWORD"];
    $db_name = $_POST["DB_NAME"];*/
    
    $db_host = "localhost";
    $db_user = "id9336220_autclubdb";
    $db_password = "software";
    $db_name = "id9336220_autclubdb";
    

    $connect=mysqli_connect($db_host,$db_user,$db_password,$db_name);

    if(isset($_POST['name'])){ $name = $_POST['name']; }
if(isset($_POST['username'])){ $userName = $_POST['username']; }
if(isset($_POST['password'])){ $password = $_POST['password']; }
if(isset($_POST['email'])){ $email = $_POST['email']; }
if(isset($_POST['firstName'])){ $firstName = $_POST['firstName']; }
if(isset($_POST['LastName'])){ $lastName = $_POST['lastName']; }
    
    /*
   $userName = "admin5";
    $password = "admin3";
    $firstName = "admin3";
    $lastName = "admin3";
    $email = "admin3";
    */
    
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