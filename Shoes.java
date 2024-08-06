import java.util.Scanner;

public class Shoes extends Product
{
	public static enum Color {BLACK, BROWN};
	public static enum Size  {SIX, SEVEN, EIGHT, NINE, TEN};
	
	public static int sizes = 5;
	public static int colors = 2;
	private static final int SIZEOFFSET = 6; 
 
	private int[][] stockCounts = new int[sizes][colors];
  
	
	private class SizeColor
	{
		public int size;
		public int color;
		
		public SizeColor(int size, int color)
		{
			this.size = size;
			this.color = color;
		}
	}
	
  public Shoes(String name, String id, double price, int[][] stockCounts)
  {
  	 super(name, id, price, 100000, Product.Category.SHOES);
  	 this.stockCounts = stockCounts;
  }
    
  public boolean validOptions(String productOptions)
  {
  	if (productOptions == null || productOptions.equals(""))
  		return false;
  	Scanner options = new Scanner(productOptions);
  	if (!options.hasNext()) return false;
  	String size  = options.next();
  	if (!options.hasNext()) return false;
  	String color = options.next();
  	if (color == null) return false;
  	return (size.equalsIgnoreCase("6") || size.equals("7") || size.equals("8") || size.equals("9") || size.equals("10")) &&
  			   (color.equalsIgnoreCase("Black") || color.equalsIgnoreCase("Brown"));
  }
  
  private SizeColor getSizeColor(String productOptions)
  {
  	Scanner options = new Scanner(productOptions);
  	String sizeString  = options.next();
  	String colorString = options.next();
  	int size = Integer.parseInt(sizeString) - SIZEOFFSET;
  	int color;
  	if (colorString.equals("Black")) 
  		color = 0;
  	else
  		color = 1;
  	return new SizeColor(size,color);
  }
  public int getStockCount(String productOptions)
	{
  	SizeColor sc = getSizeColor(productOptions);
  	
  	return stockCounts[sc.size][sc.color];
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
  	SizeColor sc = getSizeColor(productOptions);
  	
  	stockCounts[sc.size][sc.color] = stockCount;
	}

  public void reduceStockCount(String productOptions)
	{
  	SizeColor sc = getSizeColor(productOptions);
  	
  	stockCounts[sc.size][sc.color]--;
	}

  public void print()
  {
  	super.print(); 
  }
}


