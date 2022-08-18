package application;
//-----Author: Hoo Ern Ping
//-----ID: B200152B

import java.util.Arrays;

public class Customer {
    //-----instance variable

    private int id;
    private String name;
    private String floor;
    private String[] roomID;
    private int stay;
    private int adultQty;
    private int kidQty;

    //-----default constructor
    public Customer() {
        id = 0;
        name = "unknown";
        floor = "unknown";
        roomID = new String[adultQty + kidQty];
        stay = 0;
    }

    //-----constructor with parameter
    public Customer(int id, String name, String floor,
            String[] roomID, int stay) {

        this.id = id;
        this.name = name;
        this.floor = floor;
        this.roomID = roomID;
        this.stay = stay;
    }

    //-----get method
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFloor() {
        return floor;
    }

    public String[] getRoomID() {
        return roomID;
    }

    public int getStay() {
        return stay;
    }

    //-----set method
    public void setID(int theID) {
        id = theID;
    }

    public void setName(String theName) {
        name = theName;
    }

    public void setFloor(String theFloor) {
        floor = theFloor;
    }

    public void setRoomID(String[] theRoomID) {
        roomID = theRoomID;
    }

    public void setStay(int theStay) {
        stay = theStay;
    }

    //-----task method
    public double calcTotalPrice() {
        double totalPrice = 0;
        if ("1st Floor (Single Room)".equals(getFloor())) {
            totalPrice = 150 * getStay();
        } else if ("2nd Floor (Twin Room)".equals(getFloor()) || "3rd Floor (Twin Room)".equals(getFloor())) {
            totalPrice = 230 * getStay();
        } else if ("4th Floor (President Suite)".equals(getFloor())) {
            totalPrice = 300 * getStay();
        }
        return totalPrice;
    }

    //-----print price method
    public String printPrice() {
        return String.format("%.2f", calcTotalPrice());
    }

    //-----print method
    public String toString() {
        return "Customer ID: " + getID()
                + "\nCustomer Name: " + getName()
                + "\nFloor: " + getFloor()
                + "\nCustomer Room: " + Arrays.toString(getRoomID())
                + "\nTotal Price: " + calcTotalPrice();
    }
}
