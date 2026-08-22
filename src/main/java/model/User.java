package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String city;

public User(int id, String name, String email, String city ){
this.id=id;
this.name=name;
this.email=email;
this.city=city;
}

    public  int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail(){
     return email;
    }
    public String getCity(){
    return city;
    }
    @Override
    public String toString(){
        return "id:"+id+"\n"+"name:"+name+"\n"+"email:"+email+ "\n" + "city:"+city+"\n";
    }
}
