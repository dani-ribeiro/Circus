package com.company;

public class Employee {
    private String firstName;
    private String lastName;
    private String IDNum;
    private String category;
    private String nickname;

    public Employee(String lastName, String firstName, String midInitial, String IDNum, String category, String nickname){
        this.firstName = firstName;
        this.lastName = lastName;
        this.IDNum = IDNum;
        this.category = category;
        this.nickname = nickname;
    }

    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getIDNum(){ return IDNum; }
    public String getCategory() { return category; }
    public String getNickname() { return nickname; }

    public String toString(){
        return lastName + ", " + IDNum + ", " + category + ", " + nickname;
    }
}
