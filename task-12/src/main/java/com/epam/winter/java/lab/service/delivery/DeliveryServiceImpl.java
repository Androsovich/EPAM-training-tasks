package com.epam.winter.java.lab.service.delivery;

import com.epam.winter.java.lab.dao.delivery.DeliveryDao;
import com.epam.winter.java.lab.model.Delivery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    private DeliveryDao dao;

    @Resource
    public void setDao(DeliveryDao dao) {
        this.dao = dao;
    }

    @Override
    public Delivery getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int save(Delivery instance) {
        return dao.save(instance);
    }

    @Override
    public void update(Delivery instance) {
        dao.update(instance);
    }

    @Override
    public void delete(Delivery instance) {
        dao.delete(instance);
    }

    @Override
    public List<Delivery> getDeliveryByCard(Integer cardId) {
        return dao.getDeliveryByCard(cardId);
    }

    @Override
    public List<Delivery> getAll() {
        return dao.getAll();
    }

    @Override
    public void deliveryBook(Integer deliveryId) {
        Delivery delivery = getById(deliveryId);
        delivery.setDateDelivery(LocalDate.now());
        update(delivery);
    }
}
