<?php

function getDBConnection()
{
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "LeaveManagementSystem";

    return $conn = new mysqli($servername, $username, $password, $dbname);
}

function checkDBConnection($connection)
{
    if ($connection->connect_error) {
        //echo("Connection failed: " . $connection->connect_error);
        return false;
    } else {
        //echo("Connection Ok: " . $connection->connect_error);
        return true;
    }
}