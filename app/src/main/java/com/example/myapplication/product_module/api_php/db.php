<?php
// db.php
require_once 'config.php';

function getDbConnection() {
    $mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);
    if ($mysqli->connect_error) {
        die(json_encode(['error' => 'Database connection failed']));
    }
    return $mysqli;
}
?>
