<?php
define ( 'DB_HOST','localhost' );
define ( 'DB_USER', 'id9336220_stp2019');
define ( 'DB_PASSWORD','software');
define ( 'DB_NAME','id9336220_autclub');
//mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);
if(!$con){
echo "Database Connection Failed";
}
else{
// if(mysqli_connect_errno()){
//      printf("Connect failed: %s\n", mysqli_connect_error());
//     exit();
// //}
echo "DatabaseConnected Successfully";

if(isset($_POST['name'])){ $name = $_POST['name']; }
if(isset($_POST['username'])){ $username = $_POST['username']; }
if(isset($_POST['password'])){ $password = $_POST['password']; }
if(isset($_POST['email'])){ $email = $_POST['email']; }

$query="INSERT INTO AppUser (name,username,password,email) VALUES (?,?,?,?)";


$statement = mysqli_prepare($con,$query);



 mysqli_stmt_bind_param($statement,'ssss',$name,$username,$password,$email);
 mysqli_stmt_execute($statement);

 mysqli_stmt_close($statement);


mysqli_close($con);
}


?>
