<?php
// edit.php
header('Content-Type: application/json'); // Set content type to JSON
require_once 'config.php';
require_once 'db.php';

// Check if the API key is provided in the request
if (!isset($_GET['apikey']) || $_GET['apikey'] !== API_KEY) {
    // If API key is missing or incorrect, return an error
    echo json_encode(['error' => 'Invalid API key']);
    exit();
}

// Get the request method
$method = $_SERVER['REQUEST_METHOD'];

// Get database connection
$mysqli = getDbConnection();

switch ($method) {
    case 'POST':
        // Create a new product
        if (!isset($_POST['title'], $_POST['body'], $_POST['qty'], $_POST['price'], $_POST['image'], $_POST['category'])) {
            echo json_encode(['error' => 'Missing required fields']);
            exit();
        }

        $stmt = $mysqli->prepare("INSERT INTO tbl_product (title, body, qty, price, image, category) VALUES (?, ?, ?, ?, ?, ?)");
        if (!$stmt) {
            echo json_encode(['error' => 'Failed to prepare statement']);
            exit();
        }

        $stmt->bind_param('ssidss', $_POST['title'], $_POST['body'], $_POST['qty'], $_POST['price'], $_POST['image'], $_POST['category']);
        
        if ($stmt->execute()) {
            echo json_encode(['success' => TRUE, 'product_id' => $stmt->insert_id]);
        } else {
            echo json_encode(['error' => 'Failed to create product', 'details' => $stmt->error]);
        }        
        
        $stmt->close();
        break;

    case 'PUT':
        // Update an existing product
        parse_str(file_get_contents("php://input"), $_PUT);
        
        if (!isset($_PUT['pid'])) {
            echo json_encode(['error' => 'Product ID is required']);
            exit();
        }

        $pid = (int)$_PUT['pid'];
        
        $updates = [];
        $params = [];
        
        if (isset($_PUT['title'])) {
            $updates[] = "title = ?";
            $params[] = $_PUT['title'];
        }
        if (isset($_PUT['body'])) {
            $updates[] = "body = ?";
            $params[] = $_PUT['body'];
        }
        if (isset($_PUT['qty'])) {
            $updates[] = "qty = ?";
            $params[] = (int)$_PUT['qty'];
        }
        if (isset($_PUT['price'])) {
            $updates[] = "price = ?";
            $params[] = (float)$_PUT['price'];
        }
        if (isset($_PUT['image'])) {
            $updates[] = "image = ?";
            $params[] = $_PUT['image'];
        }
        if (isset($_PUT['category'])) {
            $updates[] = "category = ?";
            $params[] = $_PUT['category'];
        }

        if (empty($updates)) {
            echo json_encode(['error' => 'No fields to update']);
            exit();
        }

        $params[] = $pid;
        $sql = "UPDATE tbl_product SET " . implode(', ', $updates) . " WHERE pid = ?";
        
        $stmt = $mysqli->prepare($sql);
        if (!$stmt) {
            echo json_encode(['error' => 'Failed to prepare statement']);
            exit();
        }
        
        $types = str_repeat('s', count($params) - 1) . 'i';
        $stmt->bind_param($types, ...$params);

        if ($stmt->execute()) {
            echo json_encode(['success' => 'Product updated successfully']);
        } else {
            echo json_encode(['error' => 'Failed to update product']);
        }
        
        $stmt->close();
        break;

    case 'DELETE':
        // Delete a product
        parse_str(file_get_contents("php://input"), $_DELETE);

        if (!isset($_DELETE['pid'])) {
            echo json_encode(['error' => 'Product ID is required']);
            exit();
        }

        $pid = (int)$_DELETE['pid'];
        
        $stmt = $mysqli->prepare("DELETE FROM tbl_product WHERE pid = ?");
        if (!$stmt) {
            echo json_encode(['error' => 'Failed to prepare statement']);
            exit();
        }

        $stmt->bind_param('i', $pid);
        
        if ($stmt->execute()) {
            echo json_encode(['success' => 'Product deleted successfully']);
        } else {
            echo json_encode(['error' => 'Failed to delete product']);
        }
        
        $stmt->close();
        break;

    default:
        echo json_encode(['error' => 'Unsupported request method']);
        break;
}

// Close the database connection
$mysqli->close();
?>
