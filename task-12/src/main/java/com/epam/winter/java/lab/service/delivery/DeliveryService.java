package com.epam.winter.java.lab.service.delivery;

import com.epam.winter.java.lab.model.Delivery;
import com.epam.winter.java.lab.service.IService;

import java.util.List;

public interface DeliveryService extends IService<Delivery> {

    List<Delivery> getDeliveryByCard(Integer cardId);

    void deliveryBook(Integer deliveryId);
}
