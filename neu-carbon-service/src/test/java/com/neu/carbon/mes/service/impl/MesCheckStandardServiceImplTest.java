package com.neu.carbon.mes.service.impl;

import com.neu.carbon.mes.service.IMesCheckStandardService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class MesCheckStandardServiceImplTest {

    @Autowired
    private IMesCheckStandardService mesCheckStandardService;

    @Test
    public void selectMesCheckStandardList() {
        mesCheckStandardService.selectMesCheckStandardList(null).forEach(System.out::println);
    }
}