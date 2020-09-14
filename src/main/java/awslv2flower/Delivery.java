package awslv2flower;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    @PostUpdate
    public void onPostUpdate(){

        if(this.getStatus().equals("DeliveryStart")) {

            Shipped shipped = new Shipped();
            BeanUtils.copyProperties(this, shipped);
            shipped.publishAfterCommit();

        }else if (this.getStatus().equals("DeliveryCancelled")){

            ShipCancelled shipCancelled = new ShipCancelled();
            BeanUtils.copyProperties(this, shipCancelled);
            shipCancelled.publishAfterCommit();

        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
