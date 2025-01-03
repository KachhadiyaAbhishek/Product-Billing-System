import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

class ProductBillingSystem {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Product product = new Product();
        System.out.println("================================================================");
        System.out.print("Enter customer name : ");
        product.customer = sc.nextLine();
        int choice = 0;
        do {
            System.out.println();
            System.out.println("================================================================");
            System.out.println("""
                    1. Add Product.
                    2. Display Product Detail.
                    3. Update Product.
                    4. Remove Product.
                    5. Search Product.
                    6. Create Bill.
                    7. Exit To System.""");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Clear the newline character
                switch (choice) {
                    case 1:
                        System.out.println("----------------------------------------------------------------");
                        System.out.print("Enter product ID :\t");
                        int ProductId = sc.nextInt();
                        sc.nextLine(); // Clear the newline
                        System.out.print("Enter product name :\t");
                        String ProductName = sc.nextLine();
                        System.out.print("Enter product Qty :\t");
                        int ProductQty = sc.nextInt();
                        System.out.print("Enter product price :\t");
                        double price = sc.nextDouble();
                        sc.nextLine(); // Clear the newline
                        product.addProduct(ProductId, ProductName, ProductQty, price);
                        break;

                    case 2:
                        product.displayProductDetail();
                        break;

                    case 3:
                        // sc.nextLine();
                        System.out.println("----------------------------------------------------------------");
                        System.out.print("Enter product name: ");
                        String Name = sc.nextLine();
                        product.updateProduct(Name);
                        break;

                    case 4:
                        product.removeProduct();
                        break;

                    case 5:
                        // sc.nextLine();
                        System.out.println("----------------------------------------------------------------");
                        System.out.print("Enter product name: ");
                        String Name2 = sc.nextLine();
                        product.searchProduct(Name2);
                        break;

                    case 6:
                        product.createBill();
                        break;

                    case 7:
                        System.out.println("--------------------- Thanks , Visit Again ---------------------");
                        break;

                    default:
                        System.out.println("Invalid Input..!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("ERROR : " + e.getMessage() + ".");
                System.out.println("Please Try Again !!");
            }
        } while (choice != 7);
    }
}

class Product{
    String customer;
    Scanner sc = new Scanner(System.in);

    class Node{
        Node next;
        int ProductId;
        String ProductName;
        int ProductQty;
        double price;
        double total_Price;

        /**
         * Creates a newNode of Linked-List to add in Product-List
         * @param ProductId ID of Product
         * @param ProductName Name of Product
         * @param ProductQty Quantity of Product
         * @param price Price of product
         */
        Node(int ProductId,String ProductName,int ProductQty,double price){
            this.ProductName = ProductName;
            this.ProductId = ProductId;
            this.ProductQty = ProductQty;
            this.price = price;
            next = null;
        }

        @Override
        public String toString() {
            return "Node [ProductId=" + ProductId + ", ProductName=" + ProductName + ", ProductQty=" + ProductQty + ", price=" + price + "]";
        }

    }

    Node first = null;

    /**
     * To add new ProductNode in the Linked-list of Product
     * @param ProductName
     */
    public void addProduct(int ProductId,String ProductName,int ProductQty,double price){
        Node n = new Node(ProductId, ProductName, ProductQty, price);
        if(first == null){
            first = n;
            System.out.println("Product inserted Successfully...");
            return;
        }
        else{
             Node temp = first;
             while(temp.next != null){
                temp = temp.next;
             }
             temp.next = n;
             System.out.println("Product inserted Successfully...");
        }
    }
    /* 
     * To update products in the linked-list by name
     * @param ProductName
     */
    public void updateProduct(String Name) throws Exception{
        
        if(first == null){
             System.out.println("Product list is Empty...");
             return;
        }
        else{
            Node temp = first;
            boolean found = false;
            while(temp != null){
                if(temp.ProductName.equalsIgnoreCase(Name))
                { 
                    found = true;
                    break;
                }
                temp = temp.next;
            }

            if(!found){
                System.out.println("Product not found...");
                return;
            }
            else{
                System.out.println("Press 1 to update Quantity.\npress 2 to update price.\npress 3 to update both.");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                if(choice == 1){
                    System.out.print("Enter new Quantity: ");
                    int quantity = sc.nextInt();
                    temp.ProductQty = quantity;
                    System.out.println("Product update Successfully...");
                }
                else if(choice == 2){
                    System.out.print("Enter new price: ");
                    double p_price = sc.nextDouble();
                    temp.price = p_price;
                    System.out.println("Product update Successfully...");
                }
                else if(choice == 3){
                    System.out.print("Enter new Quantity: ");
                    int quantity = sc.nextInt();
                    System.out.print("Enter new price: ");
                    double p_price = sc.nextDouble();
                    temp.ProductQty = quantity;
                    temp.price = p_price;
                    System.out.println("Product update Successfully...");
                }
                else{
                    System.out.println("press valid number...");
                }
            }
        }
    }
     /**
     * To remove product from Linked-List by name
     * @param ProductName
     */
    public void removeProduct(){
        System.out.println("----------------------------------------------------------------");
        if(first == null){
            System.out.println("Product list is Empty...");
            return;
        } 
        else{
            System.out.print("Enter product name: ");
            String Name = sc.nextLine();
                Node temp = first;
                boolean found = false; 
                while (temp != null) {
                     if(temp.ProductName.equalsIgnoreCase(Name))
                     { 
                        found = true;
                     }
                temp = temp.next;
                }

            if(!found){
                System.out.println("Product not found...");
                return;
            }
            else{

                if((first.next == null) && (first.ProductName.equalsIgnoreCase(Name))){
                    first = null;
                    System.out.println("Product Removed Successfully...");
                    return;
                }
                else if(first.ProductName.equalsIgnoreCase(Name)){
                    Node delete = first;
                    first = first.next;
                    delete.next = null;
                    System.out.println("Product Removed Successfully...");
                    return;
                }
                else{
                    temp = first;
                    while (temp.next != null && !temp.next.ProductName.equalsIgnoreCase(Name)) {
                        temp = temp.next;
                    }
                    if (temp.next != null) {
                        temp.next = temp.next.next;
                        System.out.println("Product Removed Successfully...");
                    } else {
                        System.out.println("Product Not Found");
                    }
                }
            }
        }
    }
    /**
     * To search the Product from Linked-List by Name
     * @param ProductName
     */
    public void searchProduct(String Name){
        if(first == null){
             System.out.println("Product list is Empty...");
             return;
        }
        else{
            Node temp = first;
            boolean found = false;
            while(temp != null){
                if(temp.ProductName.equalsIgnoreCase(Name))
                { 
                    found = true;
                    break;
                }
                temp = temp.next;
            }

            if(!found){
                System.out.println("Product not found...");
                return;
            }
            else
            {
                System.out.println(temp.toString());
            }
        }
    }
    /**
     * To display Product-List
     */
    public void displayProductDetail(){
        if(first == null){
            System.out.println("Product list is Empty...");
            return;
        }
        else{
            Node temp = first;
            System.out.println();
            System.out.println("                         PRODUCT LIST                           ");
            System.out.println("----------------------------------------------------------------");
            System.out.println("ID\tPRODUCT NAME\t\tPRODUCT QTY\t\tPRICE");
            System.out.println("----------------------------------------------------------------"); 
            while(temp != null){
                System.out.println(temp.ProductId + "\t" + temp.ProductName + "\t\t\t" + temp.ProductQty + "\t\t\t" + temp.price);
                temp = temp.next;
            }
        }
    }
     /**
     * To Print Bill in txt file
     * @throws IOException
     */
    public void createBill() throws IOException{
        if(first == null){
            throw new IOException("Printing Error !!!");
        }
        PrintWriter pw = new PrintWriter(new FileWriter(customer + ".txt"));
        billHeader(customer, pw);
        Node temp = first;
        double overAllPrice = 0.0, discount = 0.0, subtotal, sgst, cgst;
        
        while(temp != null){
            temp.total_Price = temp.ProductQty * temp.price; 
            overAllPrice += temp.total_Price; // Accumulating the total price
            temp = temp.next;
        }
    
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|PRODUCT ID \t\t\tPRODUCT\t\t\t\t\t\tQUANTITY\t\t\tRATE \t\t\tTOTAL PRICE\t|");
        pw.println("|---------------------------------------------------------------------------------------------------|");
    
        temp = first;
        while(temp != null) {
            pw.println("|" + temp.ProductId + "\t\t\t\t\t\t" + temp.ProductName + "\t\t\t\t\t\t" + temp.ProductQty + "\t\t\t\t\t" + temp.price + "\t\t\t" + String.format("%.2f", temp.total_Price) + "\t\t|");    
            temp = temp.next;
        }
    
        pw.println("|---------------------------------------------------------------------------------------------------|");
        
        // Calculate discount (5% of total price)
        discount = overAllPrice * 5 / 100;
        subtotal = overAllPrice - discount;
    
        // Add discount, subtotal, taxes, and total amount
        sgst = subtotal * 6 / 100;
        cgst = subtotal * 6 / 100;
        double netPayable = subtotal + sgst + cgst;
    
        // Printing the summarized bill
        pw.println("|TOTAL AMOUNT  :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", overAllPrice) + "\t\t|");
        pw.println("|DISCOUNT      :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t5 %\t\t\t|");
        pw.println("|YOU SAVE      :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", discount) + "\t\t|");
        pw.println("|SUBTOTAL      :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", subtotal) + "\t\t|");
        pw.println("|SGST          :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", sgst) + "\t\t|");
        pw.println("|CGST          :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", cgst) + "\t\t|");
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|INVOICE TOTAL :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.format("%.2f", netPayable) + "\t\t|");
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|                                   Thank You for Shopping!!                                        |");
        pw.println("|                                         Visit Again.                                              |");
        pw.println("|---------------------------------------------------------------------------------------------------|");
        
        pw.flush();
    
        // Just for fun: simulate the printing process
        Printing p = new Printing();
        p.start();
        try {
            p.join();
            System.out.println("\nPrinting Successful...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pw.close();
    
        // Clear the product list after bill is generated
        first = null;
    }
    
    /**
     * @param customerName
     * @param pw to Print Header of Bill in txt file
     */
    static void billHeader(String customerName, PrintWriter pw) {
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|                                          INVOICE                                                  |");
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|                                        KRISHNA MALL                                               |");
        pw.println("|                                     Memanagar, Ahmedabad                                          |");
        pw.println("|---------------------------------------------------------------------------------------------------|");
        pw.println("|GSTID: 24ABC224GH453                                                       Contact: +91 7300545499 |");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        pw.println("|Customer:"+customerName + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDATE:" + dtf.format(now) + "|");
        pw.flush();
    }

/* 
*Just for FUN
*/ 
class Printing extends Thread {
    public void run() {
        int i = (int) (Math.random() * 10);
        System.out.print("Printing Under Process.");
        while (i > 0) {
            System.out.print(".");
            i--;
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
}