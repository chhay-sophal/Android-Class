<?php
// pagination.php
require_once 'db.php';

function getPaginatedProducts($page, $limit) {
    $mysqli = getDbConnection();
    
    // Calculate the offset
    $offset = ($page - 1) * $limit;
    
    // Create a SQL query with pagination
    $sql = "SELECT * FROM tbl_product LIMIT $limit OFFSET $offset";
    $result = $mysqli->query($sql);
    
    // Check for errors
    if (!$result) {
        die(json_encode(['error' => 'Query failed']));
    }
    
    // Fetch all rows
    $products = $result->fetch_all(MYSQLI_ASSOC);
    
    // Get total count for pagination info
    $countResult = $mysqli->query("SELECT COUNT(*) as total FROM tbl_product");
    $count = $countResult->fetch_assoc();
    $totalItems = $count['total'];
    
    // Prepare pagination info
    $pagination = [
        'current_page' => $page,
        'per_page' => $limit,
        'total_items' => $totalItems,
        'total_pages' => ceil($totalItems / $limit),
    ];
    
    // Close the database connection
    $mysqli->close();
    
    return ['pagination' => $pagination, 'products' => $products];
}
?>
