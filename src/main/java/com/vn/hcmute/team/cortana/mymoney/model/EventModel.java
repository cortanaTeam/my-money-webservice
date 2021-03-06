package com.vn.hcmute.team.cortana.mymoney.model;

import com.vn.hcmute.team.cortana.mymoney.base.CallBack;
import com.vn.hcmute.team.cortana.mymoney.bean.Event;
import com.vn.hcmute.team.cortana.mymoney.data.DataRepository;
import com.vn.hcmute.team.cortana.mymoney.exception.UserException;
import com.vn.hcmute.team.cortana.mymoney.utils.SecurityUtil;
import com.vn.hcmute.team.cortana.mymoney.utils.TextUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventModel {
    
    DataRepository dataRepository;
    
    @Autowired
    SecurityUtil securityUtil;
    
    @Autowired
    public EventModel(DataRepository dataRepository) {
        // TODO Auto-generated constructor stub
        this.dataRepository = dataRepository;
    }
    
    public void getEvent(String userid, String token, CallBack<List<Event>> callBack) {
        
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get Currencies!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            List<Event> list = dataRepository.getEvent(userid);
            callBack.onSuccess(list);
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail get Currencies!"));
        }
    }
    
    public void createEvent(Event event, String userid, String token, CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get Currencies!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            // event.setId(SecurityUtil.);
            dataRepository.createEvent(event);
            callBack.onSuccess("Success create event");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail get Currencies!"));
        }
    }
    
    public void updateEvent(Event event, String userid, String token, CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get Currencies!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.updateEvent(event);
            callBack.onSuccess("Success update event");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail get Currencies!"));
        }
    }
    
    public void deleteEvent(String userid, String token, String idEvent,
              CallBack<String> callBack) {
        try {
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail get event!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.deleteEvent(userid, idEvent);
            callBack.onSuccess("Success update event");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail get event!"));
        }
    }
    
    public void syncEvent(List<Event> list, String userid, String token,
              CallBack<String> callBack) {
        try {
            
            if (TextUtil.isEmpty(userid) || TextUtil.isEmpty(token)) {
                callBack.onFailure(new Throwable("Fail sync Event!"));
                return;
            }
            if (!dataRepository.isApiKey(userid, token)) {
                callBack.onFailure(new UserException("Wrong api key!"));
                return;
            }
            dataRepository.syncEvent(list);
            callBack.onSuccess("Success update event");
        } catch (Exception e) {
            callBack.onFailure(new Throwable("Fail ync Event!"));
        }
    }
}
