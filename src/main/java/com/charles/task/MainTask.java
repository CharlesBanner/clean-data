package com.charles.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MainTask {

    @Autowired
    private MaybeEnterPeopleService maybeEnterPeopleService;

    @PostConstruct
    public void taskStarter(){
       maybeEnterPeopleService.export();

    }

}
