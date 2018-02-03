<?php
include('config.php');
$userid = $_POST['user_id'];
$title = $_POST['title'];
$first = $_POST['first'];
$last = $_POST['last'];
$phone = $_POST['phone'];
$address = $_POST['address'];
$city = $_POST['city'];
$state = $_POST['state'];
$zip = $_POST['zip'];
$country = $_POST['country'];
$email = $_POST['email'];

//$registration_no = $_POST['registration_no'];

$jsonResponse = array("register_doctor"=>array());



$query = "INSERT INTO `address`(`user_id`, `title`, `first`, `last`, `phone`, `address`, `city`, `state`, `zip`, `country`, `email`) 
VALUES ('$userid','$title','$first','$last','$phone','$address','$city','$state','$zip','$country','$email')";


if(mysql_query(&query))
{
	echo "success";
}
else
{
	echo "failure";	
}




?>