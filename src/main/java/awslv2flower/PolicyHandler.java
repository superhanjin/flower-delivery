package awslv2flower;

import awslv2flower.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentConfirmed_OrderConfirm(@Payload PaymentConfirmed paymentConfirmed){

        if(paymentConfirmed.isMe()){
            System.out.println("##### listener OrderConfirm : " + paymentConfirmed.toJson());

            Delivery delivery = new Delivery();
            delivery.setOrderId(paymentConfirmed.getOrderId());
            delivery.setStatus("OrderConfirmed");

            deliveryRepository.save(delivery);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancelled_OrderCancellation(@Payload PaymentCancelled paymentCancelled){

        if(paymentCancelled.isMe()){
            System.out.println("##### listener OrderCancellation : " + paymentCancelled.toJson());

            Delivery delivery = new Delivery();
            delivery.setOrderId(paymentCancelled.getOrderId());
            delivery.setStatus("DeliveryCancelled");

            deliveryRepository.save(delivery);
        }
    }

}
