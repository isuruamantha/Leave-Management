<?php
/**
 * Created by PhpStorm.
 * User: Sachith
 * Date: 2/23/17
 * Time: 9:52 PM
 */

include_once 'DBConnection.php';

function validateUserLogin($userDetail){
    $userId = $userDetail["userId"];
    $userPassword = $userDetail["password"];

    
    
    
    $sqlQuery = "SELECT * FROM employee WHERE nic ='".$userId."' AND password = '".$userPassword."'";

    $connection = getDBConnection();
    if(checkDBConnection(getDBConnection())){
        $result = $connection->query($sqlQuery);

        if ($result->num_rows > 0) {

            while ($row = $result->fetch_row()) {
                /*$nic = $row[0];
                $first_name = $row[1];
                $last_name = $row[2];
                $telephone = $row[3];
                $email   = $row[4];
                $dob = $row[5];
                $password = $row[6];
                $total_leaves = $row[7];
                $is_hr = $row[8];*/

                $dataArr[] = [

                    "nic" => $row[0],
                    "first_name" => $row[1],
                    "last_name" => $row[2],
                    "telephone" => $row[3],
                    "email" => $row[4],
                    "dob" => $row[5],
                    "password" => $row[6],
                    "total_leaves" => $row[7],
                    "is_hr" => $row[8]

                ];

                // array_push($detailsArr,$dataArr);
            }
            $data[] = [
                "response" => "success",
                "responseObj" => $dataArr
            ];
            /*$data =  array();
            echo "Success";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);*/
            $jsonString = json_encode($data);
            echo $jsonString;

        }else{


            $data[] = [

                "response" => "Incorrect username or password"

            ];
            /*$data =  array();
            echo "failed";

            $customer = array(
                'response' => "failed"
            ); 
            array_push($data,$customer);*/
            $jsonString = json_encode($data);
            echo $jsonString;

        }
        
    }else{
        $data[] = [

            "response" => "Failed"

        ];
        /*$data =  array();
        echo "failed";

        $customer = array(
            'response' => "failed"
        );
        array_push($data,$customer);*/
        $jsonString = json_encode($data);
        echo $jsonString;
        
    }
    $connection -> close();
}

function registerNewUser($userDetail){
    $firstName = $userDetail["firstName"];
    $lastName = $userDetail["lastName"];
    $addressOne = $userDetail["addressOne"];
    $addressTwo = $userDetail["addressTwo"];
    $dob        = $userDetail["dob"];
    $contactNo = $userDetail["contactNo"];
    $email      = $userDetail["email"];
    $password   = $userDetail["password"];

    $connection = getDBConnection();
    $sqlQuery = "INSERT INTO REGISTRATION (firstName,lastName,addressOne,addressTwo,dob,email,contactNo,password) VALUES('$firstName','$lastName','$addressOne','$addressTwo','$dob','$email','$contactNo','$password')";
    if(checkDBConnection(getDBConnection())){

        if($connection -> query($sqlQuery) === TRUE){
            $data =  array();
            //echo "Successfully Added";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;


        }else{
            //echo "Failed\n";
            echo $connection->error;
            $data =  array();

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;
        }

    }else{
        echo "Failed";
    }

}

function insertNewLeave($leaveObj){
    $emp_id = $leaveObj["emp_id"];
    $from_date = $leaveObj["from_date"];
    $to_date = $leaveObj["to_date"];
    $reason = $leaveObj["reason"];
    $status = $leaveObj["status"];


    $connection = getDBConnection();
    $sqlQuery = "INSERT INTO LEAVES (emp_id,from_date,to_date,reason,status) VALUES('$emp_id','$from_date','$to_date','$reason','$status')";
    if(checkDBConnection(getDBConnection())){

        if($connection -> query($sqlQuery) === TRUE){
            $data =  array();
            //echo "Successfully Added";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;


        }else{
            //echo "Failed\n";
            echo $connection->error;
            $data =  array();

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;
        }

    }else{
        echo "Failed";
    }

}
function getAllLeavesFromUserId($userId){
    $emp_id = $userId["userId"];

    $sqlQuery = "select * from leaves where emp_id ='". $emp_id ."' ";

    $connection = getDBConnection();
    if(checkDBConnection(getDBConnection())){
        $result = $connection->query($sqlQuery);

        $detailsArr = array();

        if ($result->num_rows > 0) {



            while ($row = $result->fetch_row()) {
                $leave_id = $row[0];
                $emp_id = $row[1];
                $from_date = $row[2];
                $to_date = $row[3];
                $reason = $row[4];
                $status = $row[5];

                $dataArr[] = [

                    "leave_id" => $row[0],
                    "emp_id" => $row[1],
                    "from_date" => $row[2],
                    "to_date" => $row[3],
                    "reason" => $row[4],
                    "status" => $row[5]

                ];

               // array_push($detailsArr,$dataArr);
            }


            /*$data =  array();
            echo "Success";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);*/
            /*$jsonString = json_encode($data);
            echo $jsonString;*/
            $data[] = [
                "response" => "success",
                "responseObj" => $dataArr
            ];
            /*$data =  array();
            echo "Success";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);*/

            $jsonString = json_encode($data);
            echo $jsonString;

        }else{


            $data[] = [

                "response" => "No such record found"

            ];
            /*$data =  array();
            echo "failed";

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);*/
            $jsonString = json_encode($data);
            echo $jsonString;

        }
    }else{
        echo "Failed**";

    }


}
/*if(isset($_GET['validateUserLogin'])){
    validateUserLogin();

}*/


function getAllLeaves(){

    $sqlQuery = "select * from leaves";

    $connection = getDBConnection();
    if(checkDBConnection(getDBConnection())){
        $result = $connection->query($sqlQuery);

        $detailsArr = array();

        if ($result->num_rows > 0) {



            while ($row = $result->fetch_row()) {
                $leave_id = $row[0];
                $emp_id = $row[1];
                $from_date = $row[2];
                $to_date = $row[3];
                $reason = $row[4];
                $status = $row[5];

                $dataArr[] = [

                    "leave_id" => $row[0],
                    "emp_id" => $row[1],
                    "from_date" => $row[2],
                    "to_date" => $row[3],
                    "reason" => $row[4],
                    "status" => $row[5]

                ];

                // array_push($detailsArr,$dataArr);
            }


            /*$data =  array();
            echo "Success";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);*/
            /*$jsonString = json_encode($data);
            echo $jsonString;*/
            $data[] = [
                "response" => "success",
                "responseObj" => $dataArr
            ];
            /*$data =  array();
            echo "Success";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);*/

            $jsonString = json_encode($data);
            echo $jsonString;

        }else{


            $data[] = [

                "response" => "No such record found"

            ];
            /*$data =  array();
            echo "failed";

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);*/
            $jsonString = json_encode($data);
            echo $jsonString;

        }
    }else{
        echo "Failed**";

    }


}

function deleteLeaveFromLeaveId($leaveObj){
    $leave_id = $leaveObj["leave_id"];

    $connection = getDBConnection();

    $sqlQuery = "Delete from leaves where leave_id = '". $leave_id ."'";
    if(checkDBConnection(getDBConnection())){

        $result = $connection->query($sqlQuery);
        echo $result->num_rows;
        if ($connection -> query($sqlQuery) === TRUE) {

            $data =  array();
            //echo "Successfully Added";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;
        }else{
            echo $connection->error;
            $data =  array();

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;

        }

    }else{
        echo "Failed";
    }

}
function updateLeave($leaveObj){
    $leave_id = $leaveObj["leave_id"];
    $emp_id = $leaveObj["emp_id"];
    $from_date = $leaveObj["from_date"];
    $to_date = $leaveObj["to_date"];
    $reason = $leaveObj["reason"];
    $status = $leaveObj["status"];


    $connection = getDBConnection();

    $sqlQuery = "update leaves set  emp_id ='". $emp_id ."',status ='". $status ."',from_date='". $from_date ."',to_date='". $to_date ."',reason='". $reason ."' where leave_id='". $leave_id ."' ";
    if(checkDBConnection(getDBConnection())){

        if($connection -> query($sqlQuery) === TRUE){
            $data =  array();
            //echo "Successfully Added";

            $customer = array(
                'response' => "success"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;


        }else{
            //echo "Failed\n";
            echo $connection->error;
            $data =  array();

            $customer = array(
                'response' => "failed"
            );
            array_push($data,$customer);
            $jsonString = json_encode($data);
            echo $jsonString;
        }

    }else{
        echo "Failed";
    }

}




?>