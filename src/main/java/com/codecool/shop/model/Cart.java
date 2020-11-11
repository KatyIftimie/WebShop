package com.codecool.shop.model;
//import java.util.Currency;


import java.util.*;


public class Cart
{
    private int counter;
    private int cartId;
    private Map<Product, Integer> cartItems;
    private int idCounter = 0; // aici trebuie facuta o metoda de actualizat

    private String total;
    private boolean isFinished = false;

    public Cart()
    {
        counter++;
        cartId = counter;
        this.cartItems = new HashMap<Product, Integer>();
    }

//    public Cart(List<Product> items)
//    {
//        counter++;
//        cartId = counter;
//        
//        idCounter++;
//        this.id = idCounter;
//        this.items = items;
//        this.total = getTotal();
//    }

    public void addProduct(Product product)
    {
        Product key = product;

        if (cartItems.containsKey(key)) {
            int productQuantity = cartItems.get(key);
            cartItems.replace(key, productQuantity + 1);
        } else {
            cartItems.putIfAbsent(key, 1);
        }
        System.out.println("test metoda add" + cartItems.toString());
    }

    public Collection<Integer> getQuantity() {
        return cartItems.values();
    }

    public int getId()
    {
        return cartId;
    }

    public Map<Product, Integer> getItems()
    {
//        return cartItems.keySet();
        return cartItems;
    }


    public int getTotal()
    {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getDefaultPrice() * entry.getValue();
        }

        return total;
    }
    public void changeQuantity(String id, String quantity){
        int itemId = Integer.parseInt(id);
        int newQuantity = Integer.parseInt(quantity);
        for (Map.Entry<Product,Integer> item : cartItems.entrySet())
                if(item.getKey().getId() == itemId){
                    cartItems.replace(item.getKey(),newQuantity);
    }
    }
    public int getNumOfItems(){
        int total = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    public void removeFromCart(Product product){
        Product key = product;
        System.out.println("Before " +key);

        if (cartItems.containsKey(key)) {
            cartItems.remove(key);
            System.out.println("After" + key);
        }
    }






    @Override
    public String toString()
    {
        return "Cart{" +
                "id=" + cartId +
                ", items=" + cartItems +
                ", idCounter=" + idCounter +
                ", total='" + total + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
