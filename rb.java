import java.util.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



//for creating items
class Item
{
    String itemName="";
    String Description="";
    double price=0;
    int id=0;
    int noOfOrders=0;
    Item(String itemName,String Description,double price,int id)
    {
        this.itemName=itemName;
        this.Description=Description;
        this.price=price;
        this.id=id;
        this.noOfOrders=0;
    }
}

//for creating table
class Table
{
    int tableNo=0;
    String CustomerName="";
    Order obj=null;
    boolean billPaid=false;
    public Table(int tableNo,Order obj,String CustomerName,boolean billPaid)
    {
        this.tableNo=tableNo;
        this.obj=obj;
        this.CustomerName=CustomerName;
        this.billPaid=billPaid;
    }
}

class Restaurant
{
    //for storing the items
    public HashMap<Integer,Item> items=new HashMap<>();

    //for storing the table
    public HashMap<Integer,Table> orderedItems=new HashMap<>();

    //for storing users






    //showitem
    public void showItem()
    {
        System.out.println("|------------------------------------------------------------------------------------------------------|\n");
        System.out.println("id                          food                            description                         price");
        System.out.println("|------------------------------------------------------------------------------------------------------|\n");
        for(Integer m:items.keySet())
        {
            Item x = items.get(m);

            System.out.println(((x.id)<10?"0"+x.id:x.id )+"                           "+x.itemName+"                       "+x.Description+"                        "+x.price);
        }
        System.out.println("\n|----------------------------------------------------------------------------------------------------|");
    }

    //adding item to menulist
    public void addItem(Item obj)
    {
        items.put(obj.id,obj);
    }

    //removing item from list
    public void removeItem(int id)
    {
        items.remove(id);

    }

    //adding orders to table
    public void addTable(int tableNo,Table obj)
    {
        orderedItems.put(tableNo,obj);
    }

    //showing table details
    public void showTabledetails(int tableNo)
    {
        //showing the details of the table

        Table object=orderedItems.get(tableNo);
        Order obj1=object.obj;
        obj1.displayOrder();

    }

}

//class order for creating order
class Order
{
    ArrayList<Item> orderList=new ArrayList<>();
    public void addItemToOrder(Item m,int n)
    {
        //adding the item to the order

        for(int i=0;i<n;i++)
        {
            orderList.add(m);
        }

        //displaying the items in the order

        displayOrder();

    }

    //displayordders function
    public void displayOrder()
    {
        System.out.println("    Items Ordered:\n");
        System.out.println("|-----------------------------------------------------------------------------------------|\n");
        System.out.println("id                        food               description                   price");
        System.out.println("|-----------------------------------------------------------------------------------------|\n");

        for(Item x:orderList)
        {
            System.out.println(((x.id)<10?"0"+x.id:x.id )+"                   "+x.itemName+"                "+x.Description+"               "+x.price);
        }

        System.out.println("\n|---------------------------------------------------------------------------------------|");
    }

    public void removeOrder(int id,int quantity)
    {
        //iterating the items in orderlist

        Iterator<Item> iterator = orderList.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.id == id)
            {
                iterator.remove();
                quantity--;

                //looping for no of items to be removed

                if (quantity == 0)
                {
                    break;
                }
            }
        }

    }


    public void CalculateBill(String name)
    {
        double bill=0;

        //adding the prices of all items

        for(Item x:orderList)
        {
            bill+=x.price;
        }

        //printing the bill

        System.out.println("!-------------------------------------!");
        System.out.println("id            food              price");
        System.out.println("!-------------------------------------!");

        //formatting date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd                    HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //printing date and time
        System.out.println(dtf.format(now));

        System.out.println("!-------------------------------------!");
        for(Item x:orderList)
        {
            System.out.println(((x.id)<10?"0"+x.id:x.id )+"           "+x.itemName+"        "+x.price);
        }

        System.out.println("!-------------------------------------!");
        System.out.println("     the bill of "+name+" is : "+bill);
        System.out.println("!-------------------------------------!");


    }
}

public class rb
{
    //printingLine function

    public static void printLine()
    {
        System.out.println("______________________________________________________________________________________________________\n");
    }

    //checking item is available or not

    public static boolean checkingItemInFoods(int idno,HashMap<Integer,Item> items)
    {
        for(Integer m:items.keySet())
        {
            if(items.get(m).id==idno)
            {
                return true;
            }
        }
        return false;
    }

    //checking if the table is available or not

    public static boolean checkingTableAvailability(int tableno,HashMap<Integer,Table> tables)
    {
        for(Integer m:tables.keySet())
        {
            if(tables.get(m).tableNo==tableno)
            {
                return true;
            }
        }
        return false;
    }

    //checking order availability

    public static boolean checkingOrderAvailability(int orderid,HashMap<Integer,Table> tables,int tableNo)
    {
        for(Item m:tables.get(tableNo).obj.orderList)
        {
            if(m.id==orderid)
            {
                return true;
            }
        }
        return false;
    }

    //checking quantity

    public static int checkQuantity(HashMap<Integer,Table> tables,int tableNo,int id)
    {
        int count=0;
        for(Item m:tables.get(tableNo).obj.orderList)
        {
            if(m.id==id)
            {
                count+=1;
            }
        }
        return count;
    }

    public static void main(String[] args)
    {
        Restaurant obj=new Restaurant();
        Scanner sc=new Scanner(System.in);
        int userId=0;
        String[][] foods =
                {
                        {"Pizza      ", "Cheesy    ", "1099.0", "01"},
                        {"Burger     ", "Juicy     ", "0899.0", "02"},
                        {"Salad      ", "Fresh     ", "0699.0", "03"},
                        {"Pasta      ", "Creamy    ", "1299.0", "04"},
                        {"Steak      ", "Sizzling  ", "1999.0", "05"},
                        {"Soup       ", "Warm      ", "0499.0", "06"},
                        {"Sandwich   ", "Classic   ", "0799.0", "07"},
                        {"Sushi      ", "Delicate  ", "1499.0", "08"},
                        {"Tacos      ", "Spicy     ", "0999.0", "09"},
                        {"Ramen      ", "Savory    ", "1199.0", "10"},
                        {"Fish       ", "Grilled   ", "1699.0", "11"},
                        {"Chicken    ", "Crispy    ", "1399.0", "12"},
                        {"Shrimp     ", "Garlicky  ", "1799.0", "13"},
                        {"Lasagna    ", "Rich      ", "1599.0", "14"},
                        {"Omelette   ", "Fluffy    ", "0899.0", "15"},
                        {"Curry      ", "Aromatic  ", "1299.0", "16"},
                        {"Bacon      ", "Crisp     ", "0599.0", "17"},
                        {"Wings      ", "Tangy     ", "0999.0", "18"},
                        {"Fries      ", "Golden    ", "0399.0", "19"},
                        {"Nachos     ", "Cheesy    ", "1099.0", "20"},
                        {"Pancakes   ", "Fluffy    ", "0699.0", "21"},
                        {"Sausage    ", "Grilled   ", "0499.0", "22"},
                        {"Pho        ", "Brothy    ", "1199.0", "23"},
                        {"Gyro       ", "Savory    ", "0899.0", "24"},
                        {"Casserole  ", "Hearty    ", "1299.0", "25"},
                        {"Stew       ", "Comforting", "0999.0", "26"},
                        {"Calamari   ", "Crispy    ", "1499.0", "27"},
                        {"Quiche     ", "Savory    ", "0799.0", "28"},
                        {"Lobster    ", "Buttery   ", "2499.0", "29"},
                        {"Macaroni   ", "Creamy    ", "1099.0", "30"},
                        {"Tiramisu   ", "Decadent  ", "0599.0", "31"},
                        {"Sushi Roll ", "Fresh     ", "1199.0", "32"},
                        {"Burrito    ", "Filling   ", "0899.0", "33"},
                        {"Enchiladas ", "Spicy     ", "1299.0", "34"},
                        {"Crepes     ", "Delicate  ", "0699.0", "35"},
                        {"Bagel      ", "Toasted   ", "0299.0", "36"},
                        {"Risotto    ", "Creamy    ", "1399.0", "37"},
                        {"Meatballs  ", "Juicy     ", "0999.0", "38"},
                        {"Croissant  ", "Buttery   ", "0399.0", "39"},
                        {"Crab       ", "Sweet     ", "1899.0", "40"},
                        {"Eggplant   ", "Grilled   ", "0799.0", "41"},
                        {"Pulled Pork", "Smoky     ", "1499.0", "42"},
                        {"Biscuits   ", "Fluffy    ", "0499.0", "43"},
                        {"Ceviche    ", "Refreshing", "1699.0", "44"},
                        {"Mussels    ", "Savory    ", "1299.0", "45"},
                        {"Bruschetta ", "Crisp     ", "0599.0", "46"},
                        {"Paella     ", "Colorful  ", "1999.0", "47"},
                        {"Cabbage    ", "Stuffed   ", "0899.0", "48"},
                        {"Gazpacho   ", "Chilled   ", "0699.0", "49"},
                        {"Croquettes ", "Crispy    ", "1099.0", "50"}

                };

        //adding all items of the array to the menulist

        for (String[] food : foods)
        {
            obj.addItem(new Item(food[0],food[1],Double.parseDouble(food[2]),Integer.parseInt(food[3])));
        }

        boolean condition=true;



                        while(condition)
                        {
                            printLine();

                            //printing the available features
                            System.out.println("                                         WILDBEANS RESTAURANT              ");
                            printLine();
                            System.out.println("1. Add item  \n2. remove items \n3. Show items \n4. Create order \n5. Remove Order \n6. Update order \n7. Replace order \n8. Calculate Bill \n9. ShowOrder Details \n10. Table Remove \n11. Exit");

                            printLine();
                            int n=sc.nextInt();

                            switch (n)
                            {
                                case 1:
                                    obj.showItem();
                                    //Addding items to menuList

                                    System.out.println("Enter itemName:");
                                    String itemString=sc.next();

                                    System.out.println("Enter Description:");
                                    String Description=sc.next();

                                    System.out.println("Enter price:");
                                    double price=sc.nextDouble();

                                    while(true)
                                    {
                                        System.out.println("Enter id:");
                                        int id=sc.nextInt();

                                        //checking if the id is available or not

                                        if(checkingItemInFoods(id,obj.items))
                                        {
                                            System.out.println("Item id already exists!!");
                                        }
                                        else
                                        {
                                            obj.addItem(new Item(itemString, Description, price, id));
                                            break;
                                        }
                                    }
                                    break;

                                case 2:

                                    //removeItems from menu
                                    int id=0;
                                    while(true)
                                    {
                                        System.out.println("Enter id:");
                                        id=sc.nextInt();
                                        if(checkingItemInFoods(id,obj.items))
                                        {
                                            obj.removeItem(id);
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("no id exists");
                                        }
                                    }
                                    break;


                                case 3:

                                    //Showing items in MenuList

                                    obj.showItem();
                                    break;

                                case 4:

                                    //add items to the order

                                    System.out.println("\nEnter cutomerName:");

                                    String customerName=sc.next();

                                    int tableNo=0;

                                    //chceking if the table is occupied or not

                                    while(true)

                                    {

                                        System.out.println("Enter tableNo:");
                                        tableNo=sc.nextInt();

                                        //checking if the table is booked or not

                                        if(checkingTableAvailability(tableNo, obj.orderedItems))
                                            System.out.println("Table is already booked!!");
                                        else
                                            break;

                                    }

                                    System.out.println("Enter noOfItems:");
                                    int noOfItems=sc.nextInt();

                                    Order obj1=new Order();
                                    int orderid=0;

                                    for(int i=0;i<noOfItems;i++)
                                    {

                                        while(true)
                                        {
                                            System.out.println("Enter id:");
                                            orderid=sc.nextInt();

                                            if(checkingItemInFoods(orderid, obj.items))
                                                break;
                                            else
                                                System.out.println("there is no id available");
                                        }

                                        System.out.println("Enter quantity:");
                                        int quantity=sc.nextInt();

                                        //showing items in order
                                        obj.showItem();

                                        //adding item to order
                                        obj1.addItemToOrder(obj.items.get(orderid),quantity);

                                    }

                                    //add order to the table

                                    obj.addTable(tableNo,new Table(tableNo,obj1,customerName,false));



                                    break;

                                case 5:

                                    //removing items from the order

                                    int tableno=0;
                                    int removeId=0;

                                    //chceking if the table is occupied or not

                                    while(true)
                                    {

                                        System.out.println("Enter tableNo:");
                                        tableno=sc.nextInt();


                                        if(checkingTableAvailability(tableno, obj.orderedItems))
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("the table is empty");
                                        }

                                    }


                                    //checking if removeid is available in orderd list or not

                                    while(true)
                                    {
                                        System.out.println("Enter remove id:");
                                        removeId=sc.nextInt();

                                        if(checkingOrderAvailability(removeId,obj.orderedItems,tableno))
                                            break;
                                        else
                                            System.out.println("there is no id ordered!");
                                    }

                                    int removeQuantity=0;

                                    // checking if the removeQuantity is greater than quantity available or not

                                    while(true)
                                    {
                                        System.out.println("Enter remove quantity:");
                                        removeQuantity=sc.nextInt();

                                        if(checkQuantity(obj.orderedItems,tableno,removeId)<removeQuantity)
                                        {
                                            System.out.println("Only "+checkQuantity(obj.orderedItems,tableno,removeId)+" is available");
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }

                                    //getting customerName
                                    System.out.println(obj.orderedItems.get(tableno).CustomerName);

                                    //remove items from order
                                    obj.orderedItems.get(tableno).obj.removeOrder(removeId,removeQuantity);
                                    break;

                                case 6:
                                    int updatetablenos=0;
                                    int updateId=0;

                                    //updating order

                                    //checking table availability

                                    while(true)
                                    {

                                        System.out.println("Enter tableNo:");
                                        updatetablenos=sc.nextInt();


                                        if(checkingTableAvailability(updatetablenos, obj.orderedItems))
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("the table is empty");
                                        }

                                    }

                                    //checking item in foods

                                    while(true)
                                    {
                                        System.out.println("Enter update id:");
                                        updateId=sc.nextInt();

                                        if(checkingItemInFoods(updateId, obj.items))
                                            break;
                                        else
                                            System.out.println("there is no id available");
                                    }


                                    System.out.println("Enter update quantity:");
                                    int updateQuantitys=sc.nextInt();

                                    //getting customerName
                                    System.out.println(obj.orderedItems.get(updatetablenos).CustomerName);


                                    //adding items
                                    obj.orderedItems.get(updatetablenos).obj.addItemToOrder(obj.items.get(updateId),updateQuantitys);

                                    break;

                                case 7:

                                    //replacing items in the order

                                    int updatetableno=0;
                                    int updateremoveId=0;


                                    //chceking if the table is occupied or not

                                    while(true)
                                    {

                                        System.out.println("Enter tableNo:");
                                        updatetableno=sc.nextInt();


                                        if(checkingTableAvailability(updatetableno, obj.orderedItems))
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("the table is empty");
                                        }

                                    }

                                    //checking if the entered id is oreder or not

                                    while(true)
                                    {
                                        System.out.println("Enter remove id:");
                                        updateremoveId=sc.nextInt();

                                        if(checkingOrderAvailability(updateremoveId,obj.orderedItems,updatetableno))
                                            break;
                                        else
                                            System.out.println("there is no id ordered!");
                                    }

                                    int updateremoveQuantity=0;


                                    // checking if the removeQuantity is greater than quantity available or not

                                    while(true)
                                    {
                                        System.out.println("Enter remove quantity:");
                                        updateremoveQuantity=sc.nextInt();

                                        if(checkQuantity(obj.orderedItems,updatetableno,updateremoveId)<updateremoveQuantity)
                                        {
                                            System.out.println("Only "+checkQuantity(obj.orderedItems,updatetableno,updateremoveId)+" is available");
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }

                                    //checking if the id is available or not in the menuList

                                    while(true)
                                    {
                                        System.out.println("Enter update id:");
                                        updateId=sc.nextInt();

                                        if(checkingItemInFoods(updateId, obj.items))
                                            break;
                                        else
                                            System.out.println("there is no id available");
                                    }


                                    System.out.println("Enter update quantity:");
                                    int updateQuantity=sc.nextInt();

                                    //getting customerName
                                    System.out.println(obj.orderedItems.get(updatetableno).CustomerName);

                                    //removing items
                                    obj.orderedItems.get(updatetableno).obj.removeOrder(updateremoveId,updateremoveQuantity);

                                    //adding items
                                    obj.orderedItems.get(updatetableno).obj.addItemToOrder(obj.items.get(updateId),updateQuantity);

                                    break;

                                case 8:

                                    //calculating bill

                                    while(true)
                                    {
                                        System.out.println("Enter tableNo:");
                                        int calculatetableno=sc.nextInt();

                                        if(checkingTableAvailability(calculatetableno, obj.orderedItems))
                                        {
                                            obj.orderedItems.get(calculatetableno).obj.CalculateBill(obj.orderedItems.get(calculatetableno).CustomerName);

                                            //setting the billPaid as true
                                            obj.orderedItems.get(calculatetableno).billPaid=true;
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("the table is empty");
                                        }

                                    }


                                    break;

                                case 9:

                                    //Showing details of the table
                                    int showOrdertableno=0;

                                    //checking table availability
                                    while(true)
                                    {

                                        System.out.println("Enter tableNo:");
                                        showOrdertableno=sc.nextInt();


                                        if(checkingTableAvailability(showOrdertableno, obj.orderedItems))
                                        {
                                            break;
                                        }
                                        else
                                        {
                                            System.out.println("the table is empty");
                                        }

                                    }


                                    //getting customerName
                                    System.out.println(obj.orderedItems.get(showOrdertableno).CustomerName);

                                    //displaying the order of the table
                                    obj.orderedItems.get(showOrdertableno).obj.displayOrder();
                                    break;

                                case 10:

                                    //removing the table

                                    int removeTableNo=0;

                                    //checking table availability

                                    while(true)
                                    {
                                        System.out.println("Enter tableNo:");
                                        removeTableNo=sc.nextInt();
                                        if(checkingTableAvailability(removeTableNo, obj.orderedItems))
                                            break;
                                        else
                                            System.out.println("There is no table exist");
                                    }
                                    sc.nextLine();

                                    //checking if the bill is paid or Not

                                    if(obj.orderedItems.get(removeTableNo).billPaid)
                                    {
                                        obj.orderedItems.remove(removeTableNo);
                                        System.out.println("The table "+removeTableNo+" is successfully removed!!");
                                    }
                                    else
                                    {
                                        //alert the admin

                                        System.out.println("the bill of the table is not paid!!");
                                        System.out.println("are you sure u want to remove?(y/n)");

                                        String yOrN=sc.next();
                                        if(yOrN.equals("y"))
                                        {
                                            obj.orderedItems.remove(removeTableNo);
                                            System.out.println("The table "+removeTableNo+" is successfully removed!!");
                                        }
                                    }
                                    break;


                                case 11:

                                    //ending the loop
                                    condition=false;

                                    break;

                            }
                        }



    }
}
