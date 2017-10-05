<?php


include_once 'RegistrationController.php';

$json = file_get_contents('php://input');
$obj = json_decode($json, true);
error_reporting(0);

insertNewLeave($obj);

?>