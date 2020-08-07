package com.epam.winter.java.lab.dao.delivery;

import com.epam.winter.java.lab.dao.IDao;
import com.epam.winter.java.lab.model.Delivery;

import java.util.List;

public interface DeliveryDao extends IDao<Delivery> {

    List<Delivery> getDeliveryByCard(Integer cardId);
}
