import java.util.*;

class Customer {
    int id;
    String name;
    double purchase;

    Customer(int id, String name, double purchase) {
        this.id = id;
        this.name = name;
        this.purchase = purchase;
    }
}

public class BusinessSystem {

    static Scanner sc = new Scanner(System.in);

    // HashMap → Fast Customer Lookup
    static HashMap<Integer, Customer> customers = new HashMap<>();

    // LinkedList → Order History
    // use java.util.LinkedList explicitly to avoid conflict with custom LinkedList class
    static java.util.LinkedList<String> orders = new java.util.LinkedList<String>();

    // Stack → Discount Calculation
    static Stack<Double> stack = new Stack<>();

    // Queue → Customer Service
    // queue implementation uses java.util.LinkedList to avoid custom class collision
    static Queue<String> queue = new java.util.LinkedList<>();

    // PriorityQueue → High Value Customers
    static PriorityQueue<Customer> pq =
            new PriorityQueue<>((a, b) -> Double.compare(b.purchase, a.purchase));

    // ================= ADD CUSTOMER =================
    static void addCustomer() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Purchase Amount: ");
        double amount = sc.nextDouble();

        Customer c = new Customer(id, name, amount);

        customers.put(id, c);
        pq.add(c);

        System.out.println("Customer Added");
    }

    // ================= SEARCH CUSTOMER =================
    static void searchCustomer() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        if (customers.containsKey(id)) {
            Customer c = customers.get(id);
            System.out.println("Found: " + c.name + " " + c.purchase);
        } else {
            System.out.println("Customer Not Found");
        }
    }

    // ================= SORT SALES =================
    static void sortSales() {

        ArrayList<Customer> list = new ArrayList<>(customers.values());

        Collections.sort(list, (a, b) -> Double.compare(a.purchase, b.purchase));

        System.out.println("Customers Sorted by Sales");

        for (Customer c : list) {
            System.out.println(c.id + " " + c.name + " " + c.purchase);
        }
    }

    // ================= ADD ORDER =================
    static void addOrder() {

        sc.nextLine();
        System.out.print("Enter Order: ");
        String order = sc.nextLine();

        orders.add(order);

        System.out.println("Order Added");
    }

    // ================= SHOW ORDERS =================
    static void showOrders() {

        System.out.println("Order History");

        for (String o : orders) {
            System.out.println(o);
        }
    }

    // ================= STACK DISCOUNT =================
    static void discount() {

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Discount %: ");
        double d = sc.nextDouble();

        stack.push(price);
        stack.push(d);

        double dis = stack.pop();
        double p = stack.pop();

        double finalPrice = p - (p * dis / 100);

        System.out.println("Final Price = " + finalPrice);
    }

    // ================= QUEUE =================
    static void addQueue() {

        sc.nextLine();
        System.out.print("Customer Name: ");
        String name = sc.nextLine();

        queue.add(name);

        System.out.println("Added to Queue");
    }

    static void serveQueue() {

        if (queue.isEmpty()) {
            System.out.println("No Customers");
        } else {
            System.out.println("Serving: " + queue.poll());
        }
    }

    // ================= PRIORITY CUSTOMERS =================
    static void showPriority() {

        System.out.println("High Value Customers");

        for (Customer c : pq) {
            System.out.println(c.name + " " + c.purchase);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n1 Add Customer");
            System.out.println("2 Search Customer");
            System.out.println("3 Sort Sales");
            System.out.println("4 Add Order");
            System.out.println("5 Show Orders");
            System.out.println("6 Discount");
            System.out.println("7 Add Queue");
            System.out.println("8 Serve Queue");
            System.out.println("9 High Value Customers");
            System.out.println("10 Exit");

            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1: addCustomer(); break;
                case 2: searchCustomer(); break;
                case 3: sortSales(); break;
                case 4: addOrder(); break;
                case 5: showOrders(); break;
                case 6: discount(); break;
                case 7: addQueue(); break;
                case 8: serveQueue(); break;
                case 9: showPriority(); break;
                case 10: System.exit(0);
            }
        }
    }
}