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
}

?>