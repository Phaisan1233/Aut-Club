<?php
define ( 'DB_HOST','localhost' );
define ( 'DB_USER', 'id9336220_stp2019');
define ( 'DB_PASSWORD','software');
define ( 'DB_NAME','id9336220_autclub');

$con=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);

if(isset($_POST['username'])){ $username = $_POST['username']; }
if(isset($_POST['password'])){ $password = $_POST['password']; }

$statement = mysqli_prepare($con,"SELECT * FROM AppUser WHERE username = ? AND password = ?");
mysqli_stmt_bind_param($statement,"ss",$username,$password);
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$name,$username,$password,$email);
$user = array();
while(mysqli_stmt_fetch($statement)){
$user[name] = $name;
$user[username]=$username;
$user[password]=$password;
$user[email]=$email;

}
echo json_encode($user);
mysqli_stmt_close($statement);


mysqli_close($con);
?>
