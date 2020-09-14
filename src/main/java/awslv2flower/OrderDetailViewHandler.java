package awslv2flower;

import awslv2flower.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailViewHandler {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {
            if (ordered.isMe()) {
                // view 객체 생성
                OrderDetail orderDetail = new OrderDetail();
                // view 객체에 이벤트의 Value 를 set 함
                orderDetail.setOrderId(ordered.getId());
                orderDetail.setFlowerName(ordered.getFlowerName());
                orderDetail.setQty(ordered.getQty());
                orderDetail.setPhoneNumber(ordered.getPhoneNumber());
                orderDetail.setAddress(ordered.getAddress());
                orderDetail.setCustomerName(ordered.getCustomerName());
                orderDetail.setStatus(ordered.getStatus());
                // view 레파지 토리에 save
                orderDetailRepository.save(orderDetail);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_1(@Payload OrderCanceled orderCanceled) {
        try {
            if (orderCanceled.isMe()) {
                // view 객체 조회
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderCanceled.getId());
                for(OrderDetail orderDetail : orderDetailList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderDetail.setStatus(orderCanceled.getStatus());
                    // view 레파지 토리에 save
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_2(@Payload Shipped shipped) {
        try {
            if (shipped.isMe()) {
                // view 객체 조회
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(shipped.getOrderId());
                for(OrderDetail orderDetail : orderDetailList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderDetail.setStatus(shipped.getStatus());
                    // view 레파지 토리에 save
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentConfirmed_then_UPDATE_3(@Payload PaymentConfirmed paymentConfirmed) {
        try {
            if (paymentConfirmed.isMe()) {
                // view 객체 조회
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(paymentConfirmed.getOrderId());
                for(OrderDetail orderDetail : orderDetailList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderDetail.setStatus(paymentConfirmed.getStatus());
                    orderDetail.setPrice(paymentConfirmed.getPrice());
                    // view 레파지 토리에 save
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCancelled_then_UPDATE_4(@Payload PaymentCancelled paymentCancelled) {
        try {
            if (paymentCancelled.isMe()) {
                // view 객체 조회
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(paymentCancelled.getOrderId());
                for(OrderDetail orderDetail : orderDetailList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderDetail.setStatus(paymentCancelled.getStatus());
                    // view 레파지 토리에 save
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipCancelled_then_UPDATE_5(@Payload ShipCancelled shipCancelled) {
        try {
            if (shipCancelled.isMe()) {
                // view 객체 조회
                List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(shipCancelled.getOrderId());
                for(OrderDetail orderDetail : orderDetailList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderDetail.setStatus(shipCancelled.getStatus());
                    // view 레파지 토리에 save
                    orderDetailRepository.save(orderDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}