package com.lmx.scaffold.api.service;

import com.lmx.scaffold.dao.mapper.DwsPrsnInfDiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lmx
 * @create: 2021/1/13
 **/
@Service
public class SystemService {

    @Autowired
    private DwsPrsnInfDiMapper dwsPrsnInfDiMapper;

    public void test() {
        Integer integer = dwsPrsnInfDiMapper.selectCount(null);
        System.out.println(integer);
    }
}
