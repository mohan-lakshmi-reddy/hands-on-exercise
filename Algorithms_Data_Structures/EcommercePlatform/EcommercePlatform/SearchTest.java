package EcommercePlatform;
public class SearchTest{
    static class Product {
        int productId;
        String productName;
        String category;

        public Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return "Product ID: " + productId +
                    ", Product Name: " + productName +
                    ", Category: " + category;
        }
    }

    // Linear Search
    public static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.productId == targetId) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public static Product binarySearch(Product[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (products[mid].productId == targetId) {
                return products[mid];
            } else if (products[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mobile", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Bag", "Fashion")
        };

        int searchId = 104;

        // Linear Search
        Product linearResult = linearSearch(products, searchId);
        System.out.println("Linear Search Result:");
        if (linearResult != null) {
            System.out.println(linearResult);
        } else {
            System.out.println("Product not found");
        }

        // Binary Search
        Product binaryResult = binarySearch(products, searchId);
        System.out.println("\nBinary Search Result:");
        if (binaryResult != null) {
            System.out.println(binaryResult);
        } else {
            System.out.println("Product not found");
        }

        // Time Complexity Analysis
        System.out.println("\nTime Complexity Analysis:");
        System.out.println("Linear Search - Best: O(1), Average: O(n), Worst: O(n)");
        System.out.println("Binary Search - Best: O(1), Average: O(log n), Worst: O(log n)");

        System.out.println("\nConclusion:");
        System.out.println("Binary Search is more suitable for large e-commerce platforms because it is much faster than Linear Search.");
    }
}