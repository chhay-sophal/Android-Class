<?php

// Database connection
$host = 'localhost';
$db_name = 'android_api';
$username = 'root';
$password = '';
$conn = new mysqli($host, $username, $password, $db_name);


if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$api_key = 'd2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z';


// Check if the API key is provided in the request
if (!isset($_GET['apikey']) || $_GET['apikey'] !== $api_key) {
    // If API key is missing or incorrect, return an error
    echo json_encode(['error' => 'Invalid API key']);
    exit();
}


// Get the request method
$request_method = $_SERVER["REQUEST_METHOD"];


// Function to get input data (JSON or form data)
function get_input_data() {
    $contentType = isset($_SERVER["CONTENT_TYPE"]) ? trim($_SERVER["CONTENT_TYPE"]) : '';

    if (stripos($contentType, 'application/json') !== false) {
        return json_decode(file_get_contents("php://input"), true);
    } else {
        return $_POST;
    }
}


// Function to get all products or a specific product with pagination and search
function get_products($pid = 0, $search = "", $page = 1, $limit = 10) {
    header('Content-Type: application/json');
    global $conn;

    $offset = ($page - 1) * $limit;
    $sql = "SELECT * FROM tbl_product";

    if ($pid != 0) {
        $sql .= " WHERE pid = ?";
    } elseif (!empty($search)) {
        $search = "%" . $conn->real_escape_string($search) . "%";
        $sql .= " WHERE title LIKE ? OR body LIKE ? OR category LIKE ?";
        $sql .= " LIMIT ? OFFSET ?";
    } else {
        $sql .= " LIMIT ? OFFSET ?";
    }

    $stmt = $conn->prepare($sql);

    if (!$stmt) {
        // Output the error if prepare fails
        echo json_encode(["error" => "Prepare failed: " . $conn->error]);
        exit();
    }

    if ($pid != 0) {
        $stmt->bind_param("i", $pid);
    } elseif (!empty($search)) {
        $stmt->bind_param("sssii", $search, $search, $search, $limit, $offset);
    } else {
        $stmt->bind_param("ii", $limit, $offset);
    }

    $stmt->execute();
    $result = $stmt->get_result();
    $products = array();

    while ($row = $result->fetch_assoc()) {
        $products[] = $row;
    }

    echo json_encode(["products" => $products]);
}


// Function to insert a new product
function add_product() {
    header('Content-Type: application/json');
    global $conn;

    $data = get_input_data();

    if (!isset($data["title"], $data["body"], $data["qty"], $data["price"], $data["image"], $data["category"])) {
        echo json_encode(["error" => "Missing required fields"]);
        http_response_code(400);
        return;
    }

    $stmt = $conn->prepare("INSERT INTO tbl_product (title, body, qty, price, image, category) VALUES (?, ?, ?, ?, ?, ?)");

    if (!$stmt) {
        echo json_encode(["error" => "Prepare failed: " . $conn->error]);
        http_response_code(500);
        return;
    }

    $stmt->bind_param("ssiiss", $data["title"], $data["body"], $data["qty"], $data["price"], $data["image"], $data["category"]);

    if ($stmt->execute()) {
        echo json_encode(["message" => "Product added successfully"]);
    } else {
        echo json_encode(["error" => "Error: " . $conn->error]);
        http_response_code(500);
    }
}


// Function to update an existing product
function update_product() {
    header('Content-Type: application/json');
    global $conn;

    $data = get_input_data();

    if (!isset($data["pid"], $data["title"], $data["body"], $data["qty"], $data["price"], $data["image"], $data["category"])) {
        echo json_encode(["error" => "Missing required fields"]);
        http_response_code(400);
        return;
    }

    $stmt = $conn->prepare("UPDATE tbl_product SET title=?, body=?, qty=?, price=?, image=?, category=? WHERE pid=?");

    if (!$stmt) {
        echo json_encode(["error" => "Prepare failed: " . $conn->error]);
        http_response_code(500);
        return;
    }

    $stmt->bind_param("ssiissi", $data["title"], $data["body"], $data["qty"], $data["price"], $data["image"], $data["category"], $data["pid"]);

    if ($stmt->execute()) {
        echo json_encode(["message" => "Product updated successfully"]);
    } else {
        echo json_encode(["error" => "Error: " . $conn->error]);
        http_response_code(500);
    }
}


// Function to delete a product
function delete_product($pid) {
    header('Content-Type: application/json');
    global $conn;

    $stmt = $conn->prepare("DELETE FROM tbl_product WHERE pid=?");

    if (!$stmt) {
        echo json_encode(["error" => "Prepare failed: " . $conn->error]);
        http_response_code(500);
        return;
    }

    $stmt->bind_param("i", $pid);

    if ($stmt->execute()) {
        // Check if any rows were affected
        if ($stmt->affected_rows > 0) {
            echo json_encode(["message" => "Product deleted successfully"]);
        } else {
            echo json_encode(["error" => "No product found with the given ID"]);
            http_response_code(404);
        }
    } else {
        echo json_encode(["error" => "Error: " . $stmt->error]);
        http_response_code(500);
    }
}


// Routing
switch ($request_method) {
    case 'GET':
        $pid = !empty($_GET["pid"]) ? intval($_GET["pid"]) : 0;
        $search = !empty($_GET["search"]) ? $_GET["search"] : "";
        $page = !empty($_GET["page"]) ? intval($_GET["page"]) : 1;
        $limit = !empty($_GET["limit"]) ? intval($_GET["limit"]) : 10;
        get_products($pid, $search, $page, $limit);
        break;

    case 'POST':
        add_product();
        break;

    case 'PUT':
        update_product();
        break;

    case 'DELETE':
        if (!empty($_GET["pid"])) {
            $pid = intval($_GET["pid"]);
            delete_product($pid);
        } else {
            echo json_encode(["error" => "Product ID is required"]);
            http_response_code(400);
        }
        break;

    default:
        header("HTTP/1.0 405 Method Not Allowed");
        echo json_encode(["error" => "Method not allowed"]);
        break;
}


$conn->close();

?>
