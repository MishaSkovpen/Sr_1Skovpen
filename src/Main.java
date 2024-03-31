//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    public Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }


    public void addToCart(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        if (items.containsKey(product)) {
            int currentQuantity = items.get(product);
            if (currentQuantity - quantity <= 0) {
                items.remove(product);
            } else {
                items.put(product, currentQuantity - quantity);
            }
        }
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Cart:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                System.out.println(entry.getKey().getName() + " - Quantity: " + entry.getValue());
            }
        }
    }

    public void clearCart() {
        items.clear();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }
}

class Order {
    private int id;
    private List<Product> products;

    public Order(int id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

class OnlineStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        Cart cart = new Cart();
        List<Order> orders = new ArrayList<>();
        int orderId = 1;

        products.add(new Product(1, "Lenovo legion 5", 1200.0, "High-performance laptop"));
        products.add(new Product(2, "Iphone 15 pro (Titanium)", 800.0, "Latest smartphone model"));
        products.add(new Product(3, "Sony WH-1000 xm4", 150.0, "Noise-canceling headphones"));

        while (true) {
            System.out.println("\nMain Page:");
            System.out.println("1. View menu");
            System.out.println("2. Add Product to your Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Place Order <3");
            System.out.println("5. Exit prog");
            System.out.print("Enter your choice(num): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts(products);
                    break;
                case 2:
                    addToCart(scanner, products, cart);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    placeOrder(cart, orders, orderId++);
                    break;
                case 5:
                    System.out.println("Exiting, thx for yur purchase <3. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void viewProducts(List<Product> products) {
        System.out.println("\nProducts:");
        for (Product product : products) {
            System.out.println(product.getName() + " - Price: $" + product.getPrice());
        }
    }

    private static void addToCart(Scanner scanner, List<Product> products, Cart cart) {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName());
        }
        System.out.print("Enter the ID of the product you want to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            cart.addToCart(selectedProduct, quantity);
            System.out.println("Product added to cart successfully.");
        } else {
            System.out.println("Invalid product ID. Please try again.");
        }
    }

    private static void placeOrder(Cart cart, List<Order> orders, int orderId) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add some items to place an order.");
            return;
        }

        List<Product> cartItems = new ArrayList<>(cart.getItems().keySet());
        Order order = new Order(orderId, cartItems);
        orders.add(order);
        cart.clearCart();
        System.out.println("Order placed successfully. Order ID: " + orderId);
    }
}

