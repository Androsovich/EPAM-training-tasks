package com.epam.winter.java.lab.service.card;

import com.epam.winter.java.lab.dao.card.CardDao;
import com.epam.winter.java.lab.model.SubscriptionCard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardDao dao;

    @Resource
    public void setDao(CardDao dao) {
        this.dao = dao;
    }

    @Override
    public SubscriptionCard getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int save(SubscriptionCard instance) {
        return dao.save(instance);
    }

    @Override
    public void update(SubscriptionCard instance) {
        dao.update(instance);
    }

    @Override
    public void delete(SubscriptionCard instance) {
        dao.delete(instance);
    }

    @Override
    public List<SubscriptionCard> getAll() {
        return dao.getAll();
    }
}
