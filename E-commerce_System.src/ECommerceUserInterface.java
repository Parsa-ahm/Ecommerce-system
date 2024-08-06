import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try{
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR"))	// ship an order to a customer
			{
				String author = "";

				System.out.print("Author: ");
				if (scanner.hasNextLine())
					author = scanner.nextLine();

				ArrayList<Book> books = amazon.booksByAuthor(author);
				Collections.sort(books);
				for (Book book: books)
					book.print();
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";

				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();

				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();

				boolean success = amazon.createCustomer(name, address);
				if (!success)
					System.out.println(amazon.getErrorMessage());
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();

				ProductOrder order = amazon.shipOrder(orderNumber);
				if (order != null)
				{
					order.print();
				}
				else 
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				// Prints all current orders and all shipped orders for this customer
				boolean validCustomer = amazon.printOrderHistory(customerId);
				if (!validCustomer)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				String orderNumber = amazon.orderProduct(productId, customerId, "");
				if (orderNumber != null)
				{
					System.out.println("Order #" + orderNumber);
				}
				else
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String format = "";

				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				if (scanner.hasNextLine())
					format = scanner.nextLine();

				String orderNumber = amazon.orderProduct(productId, customerId, format);
				if (orderNumber != null)
				{
					System.out.println("Order #" + orderNumber);
				}
				else
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String sizeColor = "";

				System.out.print("Product Id: ");
				if (scanner.hasNextLine())
					productId = scanner.nextLine();

				System.out.print("\nCustomer Id: ");
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				System.out.print("\nSize (6, 7, 8, 9, 10) and Color (Black or Brown): ");
				if (scanner.hasNextLine())
					sizeColor = scanner.nextLine();

				String orderNumber = amazon.orderProduct(productId, customerId, sizeColor);
				if (orderNumber != null)
				{
					System.out.println("Order #" + orderNumber);
				}
				else
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();

				boolean success = amazon.cancelOrder(orderNumber);
				if (!success)
				{
					System.out.println(amazon.getErrorMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}	
			else if (action.equalsIgnoreCase("ADDTOCART")) // adds an item to the customers cart
			{
				Scanner info = new Scanner(System.in);
				System.out.println("Customer Id: ");
				System.out.println("Item Id: ");
				System.out.println("Item option: ");
				String custId = info.nextLine();
				String itemId = info.nextLine();
				String itemOption = info.nextLine();
				amazon.addToCart(custId, itemId, itemOption);
				
				info.close();
			}
			else if(action.equalsIgnoreCase("REMCARTITEM")) // removes an item from customers cart
			{
				Scanner info = new Scanner(System.in);
				System.out.println("Customer Id: ");
				System.out.println("Item Id: ");
				System.out.println("Item option: ");
				String custId = info.nextLine();
				String itemId = info.nextLine();
				String itemOption = info.nextLine();
				amazon.remFromCart(custId, itemId, itemOption);
				info.close();
			}
			else if(action.equalsIgnoreCase("PRINTCART")) // prints the customers cart
			{
				Scanner info = new Scanner(System.in);
				System.out.println("Customer Id:");
				amazon.printCart(info.nextLine());
				info.close();
			}
			else if(action.equalsIgnoreCase("ORDERITEMS")) // orders items that are in the cart
			{
				Scanner info = new Scanner(System.in);
				System.out.println("Customer Id:");
				amazon.OrderFromCart(info.next());
				info.close();
			}
			System.out.print("\n>");


		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			continue;
		}
		catch (IllegalAccessError e) {
			System.out.println(e.getMessage());
			continue;
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			continue;
		}
		catch (IllegalCallerException e) {
			System.out.println(e.getMessage());
			continue;
		}
	}
	}
}
