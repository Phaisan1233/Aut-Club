 <?php
 define ( 'DB_HOST','localhost' );
define ( 'DB_USER', 'id9336220_stp2019');
define ( 'DB_PASSWORD','software');
define ( 'DB_NAME','d9336220_autclub');

$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
if(!$con){
echo "Database Connection Failed";
}else{
echo "DatabaseConnected Successfully";

 $name = $_POST["name"];

    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];
    $statement = mysqli_prepare($con, "INSERT INTO AppUser (name, username, password, email) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $name, $username, $password, $email);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
}
?>