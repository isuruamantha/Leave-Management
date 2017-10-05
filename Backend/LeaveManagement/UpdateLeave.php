<?php
/**
 * Created by PhpStorm.
 * User: Sachith
 * Date: 3/6/17
 * Time: 9:20 PM
 */

include_once 'RegistrationController.php';

$json = file_get_contents('php://input');
$obj = json_decode($json,true);

updateLeave($obj);

?>