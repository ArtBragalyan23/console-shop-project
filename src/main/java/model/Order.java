package model;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {
    private int id;
    private int userId;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order (int id, int userId, OrderStatus status, LocalDateTime createdAt){
        this.id=id;
        this.userId=userId;
        this.status=status;
        this.createdAt=createdAt;
    }

    public int getId(){
        return id;
    }
    public int getUserId(){
        return userId;
    }
   public OrderStatus getStatus(){
        return status;
   }
   public LocalDateTime getCreatedAt(){
        return createdAt;
   }
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
   @Override
    public String toString(){
        return "id: "+ id+"\n"+"userId: "+userId+"\n"+"Status: "+status+"\n"+"Created at: "+createdAt.format(formatter);
   }
}
