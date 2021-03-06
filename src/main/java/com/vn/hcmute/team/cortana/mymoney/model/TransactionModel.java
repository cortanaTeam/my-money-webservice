package com.vn.hcmute.team.cortana.mymoney.model;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Transaction;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionModel {
    
    @Autowired
    private DataRepository mDataRepository;
    
    public TransactionModel(DataRepository dataRepository) {
        this.mDataRepository = dataRepository;
    }
    
    public void getAllTransaction(String userid, String token,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository.getAllTransaction(userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionById(String userid, String token, String transactionId,
              CallBack<Transaction> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            Transaction trans = mDataRepository.getTransactionById(transactionId, userid);
            callBack.onSuccess(trans);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByType(String userid, String token, int type,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository.getTransactionByType(type, userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByTime(String userid, String token, String startDate, String endDate,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository
                      .getTransactionByTime(startDate, endDate, userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByCategory(String userid, String token, String categoryId,
              String startDate,
              String endDate, CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository
                      .getTransactionByCategory(categoryId, startDate, endDate, userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByType(String userid, String token, int type, String walletId,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository.getTransactionByType(type, userid, walletId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByTime(String userid, String token, String startDate, String endDate,
              String walletId,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository
                      .getTransactionByTime(startDate, endDate, userid, walletId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByCategory(String userid, String token, String categoryId,
              String startDate,
              String endDate, String walletId, CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository
                      .getTransactionByCategory(categoryId, userid, startDate, endDate,
                                walletId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByEvent(String userId, String token, String eventId,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userId) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userId, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository.getTransactionByEvent(userId, eventId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionByBudget(String userId, String token, String startDate,
              String endDate, String categoryId,
              String walletId, CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userId) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userId, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            if (Double.parseDouble(startDate) > Double.parseDouble(endDate)) {
                callBack.onFailure(new UserException("StartDate > EndDate"));
                return;
            }
            List<Transaction> list = mDataRepository
                      .getTransactionByBudget(userId, startDate, endDate, categoryId,
                                walletId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void getTransactionBySaving(String userId, String token, String savingId,
              CallBack<List<Transaction>> callBack) {
        try {
            if (TextUtil.isEmpty(userId) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userId, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Transaction> list = mDataRepository.getTransactionBySaving(userId, savingId);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void addTransaction(String userid, String token, Transaction transaction,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            mDataRepository.addTransaction(transaction);
            callBack.onSuccess("Add transaction successful");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void updateTransaction(String userid, String token, Transaction transaction,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            
            mDataRepository.updateTransaction(transaction);
            callBack.onSuccess("Update transaction successful");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void removeTransaction(String userid, String token, String transactionId,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            mDataRepository.removeTransaction(transactionId, userid);
            callBack.onSuccess("Remove transaction successful");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
    public void syncTransaction(String userid, String token, List<Transaction> transactions,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new UserException("User id, token is empty"));
                return;
            }
            if (!mDataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            mDataRepository.syncTransaction(transactions);
            callBack.onSuccess("Sync transaction successful");
        } catch (Exception e) {
            callBack.onFailure(e);
        }
    }
    
}
