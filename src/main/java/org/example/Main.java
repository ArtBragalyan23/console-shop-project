package org.example;
import dao.*;
import model.*;
import service.*;
import service.UserService;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    ===SHOP===
                    1. Users
                    2. Products
                    3. Orders
                    0. Exit
                    """);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    productMenu();
                    break;
                case 3:
                    orderMenu();
                    break;
                case 0: {
                    return;
                }
            }

        }
    }

    private static void userMenu() throws SQLException {
        System.out.println("""
                ===USERS===
                1. Find user by ID
                2. Show all users
                3. Create user
                4. Update user
                5. Delete user
                0. Back
                """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        UserService userService = new UserService();
        switch (choice) {
            case 1: {
                System.out.println("Enter user ID: ");
                int id = scanner.nextInt();
                Optional<User> user = userService.findUserById(id);
                if (user.isPresent()) {
                    System.out.println(user.get());
                } else {
                    System.out.println("User not found");
                }
                return;
            }
            case 2: {
                List<User> users = userService.getAllUsers();
                if (users.isEmpty()) {
                    System.out.println("Users not found");
                } else {
                    users.forEach(System.out::println);
                }
                return;
            }
            case 3: {
                System.out.println("Enter users name: ");
                String name = scanner.nextLine();
                System.out.println("Enter users email: ");
                String email = scanner.nextLine();
                System.out.println("Enter users city: ");
                String city = scanner.nextLine();
                User user = new User(0, name, email, city);
                userService.createUser(user);
                return;
            }
            case 4: {
                System.out.println("Enter user id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                System.out.println("Enter new email ");
                String email = scanner.nextLine();
                System.out.println("Enter new city: ");
                String city = scanner.nextLine();
                User user = new User(id, name, email, city);
                userService.updateUser(user);
                return;
            }
            case 5: {
                System.out.println("Enter user id");
                int id = scanner.nextInt();
                userService.deleteUser(id);
                return;
            }
            case 0: {
                return;
            }

        }
    }

    private static void productMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ===Products===
                1. Find product by ID
                2. Show all products
                3. Create product
                4. Update product
                5. Delete product
                0. Back
                """);
        ProductService productService = new ProductService();
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter product ID: ");
                    int id = scanner.nextInt();
                    Optional<Product> product = productService.getProductById(id);
                    if (product.isPresent()) {
                        System.out.println(product.get());
                    } else {
                        System.out.println("Product not found");
                    }
                    return;
                }
                case 2: {
                    List<Product> products = productService.getAllProducts();
                    if (products.isEmpty()) {
                        System.out.println("Products not found");
                    } else {
                        products.forEach(System.out::println);
                    }
                    return;
                }
                case 3: {
                    System.out.println("Enter products name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter price: ");
                    Double price = scanner.nextDouble();
                    System.out.println("Enter users stock: ");
                    int stock = scanner.nextInt();
                    Product product = new Product(0, name, price, stock);
                    productService.createProduct(product);
                    return;
                }
                case 4: {
                    System.out.println("Enter product id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter new price ");
                    Double price = scanner.nextDouble();
                    System.out.println("Enter new stock: ");
                    int stock = scanner.nextInt();
                    Product product = new Product(id, name, price, stock);
                    productService.updateProduct(product);
                    return;
                }
                case 5: {
                    System.out.println("Enter product id");
                    int id = scanner.nextInt();
                    productService.deleteProduct(id);
                    return;
                }
                case 0: {
                    return;
                }
            }
        }

    }

    private static void orderMenu() throws SQLException {

        System.out.println("""
                
                ===== ORDERS =====
                1. Find order by ID
                2. Show all orders
                3. Create order
                4. Update order
                5. Delete order
                0. Back
                """);
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: {
                OrderItemService orderItemService = new OrderItemService();
                System.out.print("Enter order ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                Optional<Order> order = orderService.getOrderById(id);
                if (order.isPresent()) {
                    System.out.println(order.get());
                    List<OrderItem> items = orderItemService.getAllOrderItems();

                    items.stream()
                            .filter(item -> item.getOrderId() == order.get().getId())
                            .forEach(item -> System.out.println(
                                    "Product ID: " + item.getProductId() +
                                            "\nQuantity: " + item.getQuantity()
                            ));
                } else {
                    System.out.println("Order not found");
                }
                return;
            }
            case 2: {
                OrderItemService orderItemService = new OrderItemService();
                List<Order> orders = orderService.getAllOrders();
                List<OrderItem> items = orderItemService.getAllOrderItems();
                orders.forEach(order -> {
                    System.out.println(order);
                    items.stream()
                            .filter(item -> item.getOrderId() == order.getId())
                            .forEach(item -> System.out.println("Product ID: " + item.getProductId() + "\n" + "Quantity: " + item.getQuantity()));
                });
                return;
            }
            case 3: {
                System.out.print("Enter user ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine();
                UserService userService = new UserService();
                OrderItemService orderItemService = new OrderItemService();
                Optional<User> user = userService.findUserById(userId);
                if (user.isEmpty()) {
                    System.out.println("User not found");
                    break;
                }
                HashMap<Integer, Integer> items = new HashMap<>();
                while (true) {
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    items.put(productId, quantity);
                    System.out.println("Add another product? 1-yes, 0-no ");
                    int prodChoice = scanner.nextInt();

                    if (prodChoice == 0) {
                        break;
                    }
                }
                Order order = new Order(0, userId, OrderStatus.NEW, LocalDateTime.now());
                int orderId = orderService.createOrder(order);
                orderItemService.createOrderItem(orderId, items);
                System.out.println("Order created");
                return;
            }


            case 4: {
                System.out.print("Enter order ID: ");
                int id = scanner.nextInt();

                System.out.print("Enter user ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine();

                System.out.println("""
                        
                        Choose status:
                        1. NEW
                        2. PROCESSING
                        3. SHIPPED
                        4. DELIVERED
                        5. CANCELLED
                        """);

                int statusChoice = scanner.nextInt();
                scanner.nextLine();
                OrderStatus status = switch (statusChoice) {
                    case 1 -> OrderStatus.NEW;
                    case 2 -> OrderStatus.PROCESSING;
                    case 3 -> OrderStatus.SHIPPED;
                    case 4 -> OrderStatus.DELIVERED;
                    case 5 -> OrderStatus.CANCELLED;
                    default -> {
                        System.out.println("Invalid status");
                        yield OrderStatus.NEW;
                    }
                };
                Order order = new Order(id, userId, status, LocalDateTime.now());
                orderService.updateOrder(order);
                System.out.println("Order updated");
                return;
            }
            case 5: {
                System.out.println("Enter order id");
                int id = scanner.nextInt();
                orderService.deleteOrder(id);
                return;
            }
                case 0: {
                    return;
                }
            }
        }
    }

