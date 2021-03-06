package com.vn.hcmute.team.cortana.mymoney.data.service.saving;

import com.mongodb.MongoException;
import com.vn.hcmute.team.cortana.mymoney.bean.Saving;
import com.vn.hcmute.team.cortana.mymoney.bean.Wallet;
import com.vn.hcmute.team.cortana.mymoney.data.DbConstraint;
import com.vn.hcmute.team.cortana.mymoney.exception.DatabaseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class SavingServiceImp implements SavingService {
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public List<Saving> getSaving(String userid) {
        try {
            Query searchQuery = new Query(Criteria.where("user_id").is(userid));
            List<Saving> list = mongoTemplate
                      .find(searchQuery, Saving.class, DbConstraint.TABLE_SAVING);
            return list;
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
    @Override
    public void createSaving(Saving saving) {
        try {
            mongoTemplate.save(saving, DbConstraint.TABLE_SAVING);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void updateSaving(Saving saving) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("saving_id").is(saving.getSaving_id()).and("user_id")
                                .is(saving.getUser_id()));
            
            Saving saving1 = mongoTemplate.findOne(query, Saving.class, DbConstraint.TABLE_SAVING);
            if (saving1 == null) {
                throw new RuntimeException("Null Saving!");
            }
            
            Update update = new Update();
            update.set("name", saving.getName());
            update.set("goal_money", saving.getGoal_money());
            update.set("start_money", saving.getStart_money());
            update.set("current_money", saving.getCurrent_money());
            update.set("date", saving.getDate());
            update.set("wallet_id", saving.getWallet_id());
            update.set("status", saving.getStatus());
            update.set("currencies", saving.getCurrencies());
            update.set("icon", saving.getIcon());
            
            mongoTemplate.updateFirst(query, update, Saving.class, DbConstraint.TABLE_SAVING);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void deleteSaving(String idSaving) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("saving_id").is(idSaving));
            Saving saving = mongoTemplate.findOne(query, Saving.class, DbConstraint.TABLE_SAVING);
            if (saving == null) {
                throw new RuntimeException("Null Saving!");
            }
            mongoTemplate.remove(query, Saving.class, DbConstraint.TABLE_SAVING);
            
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    @Override
    public void takeIn(String idWallet, String idSaving, String moneyUpdateWallet,
              String moneyUpdateSaving) {
        try {
            exchangeSaving(idWallet, idSaving, moneyUpdateWallet, moneyUpdateSaving);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
        
    }
    
    @Override
    public void takeOut(String idWallet, String idSaving, String moneyUpdateWallet,
              String moneyUpdateSaving) {
        try {
            exchangeSaving(idWallet, idSaving, moneyUpdateWallet, moneyUpdateSaving);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
    public void exchangeSaving(String idWallet, String idSaving, String moneyUpdateWallet,
              String moneyUpdateSaving) {
        Query queryWallet = new Query();
        queryWallet.addCriteria(Criteria.where("wallet_id").is(idWallet));
        Wallet wallet = mongoTemplate.findOne(queryWallet, Wallet.class, DbConstraint.TABLE_WALLET);
        if (wallet == null) {
            throw new RuntimeException("Null Wallet!");
        }
        Update updateMoneyWallet = new Update();
        updateMoneyWallet.set("money", moneyUpdateWallet);
        mongoTemplate.updateFirst(queryWallet, updateMoneyWallet, Wallet.class,
                  DbConstraint.TABLE_WALLET);
        
        Query querySaving = new Query();
        querySaving.addCriteria(Criteria.where("saving_id").is(idSaving));
        Saving saving = mongoTemplate.findOne(querySaving, Saving.class, DbConstraint.TABLE_SAVING);
        if (saving == null) {
            throw new RuntimeException("Null Saving!");
        }
        Update updateMoneySaving = new Update();
        updateMoneySaving.set("current_money", moneyUpdateSaving);
        mongoTemplate.updateFirst(querySaving, updateMoneySaving, Saving.class,
                  DbConstraint.TABLE_SAVING);
    }
    
    @Override
    public void syncSaving(List<Saving> list) {
        if (list == null) {
            throw new RuntimeException("null list");
        }
        
        List<Saving> listSaving = getSaving(list.get(0).getUser_id());
        
        for (int i = 0; i < listSaving.size(); i++) {
            if (!list.contains(listSaving.get(i))) {
                deleteSaving(listSaving.get(i).getSaving_id());
            }
        }
        
        for (Saving saving : list) {
            sync(saving);
        }
    }
    
    public void sync(Saving saving) {
        try {
            Query query = new Query();
            query.addCriteria(
                      Criteria.where("saving_id").is(saving.getSaving_id()).and("user_id")
                                .is(saving.getUser_id()));
            
            Saving saving1 = mongoTemplate.findOne(query, Saving.class, DbConstraint.TABLE_SAVING);
            if (saving1 == null) {
                createSaving(saving);
                return;
            }
            
            Update update = new Update();
            update.set("name", saving.getName());
            update.set("goal_money", saving.getGoal_money());
            update.set("start_money", saving.getStart_money());
            update.set("current_money", saving.getCurrent_money());
            update.set("date", saving.getDate());
            update.set("wallet_id", saving.getWallet_id());
            update.set("status", saving.getStatus());
            update.set("currencies", saving.getCurrencies());
            update.set("icon", saving.getIcon());
            mongoTemplate.updateFirst(query, update, Saving.class, DbConstraint.TABLE_SAVING);
        } catch (MongoException e) {
            throw new DatabaseException("Something wrong! Please try later");
        }
    }
    
}
