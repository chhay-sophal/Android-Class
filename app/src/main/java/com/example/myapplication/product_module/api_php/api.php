<?php
// http://localhost/product/api.php?apikey=d2f8e6d5c9b0a1e2f7d3c4b5a6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z

// api.php
header('Content-Type: application/json'); // Set content type to JSON
require_once 'config.php';
require_once 'pagination.php';

// Check if the API key is provided in the request
if (!isset($_GET['apikey']) || $_GET['apikey'] !== API_KEY) {
    // If API key is missing or incorrect, return an error
    echo json_encode(['error' => 'Invalid API key']);
    exit();
}

// Get pagination parameters
$page = isset($_GET['page']) ? (int)$_GET['page'] : 1; // Default to page 1
$limit = isset($_GET['limit']) ? (int)$_GET['limit'] : 10; // Default to 10 items per page

// Validate pagination parameters
if ($page < 1) $page = 1;
if ($limit < 1) $limit = 1;

// Get paginated products
$response = getPaginatedProducts($page, $limit);

// Output JSON
echo json_encode($response);
?>
