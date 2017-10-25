package com.crow.service;

import com.crow.domain.TitleWord;
import com.crow.domain.TitleWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CrowHawk on 17/10/21.
 */
@Service
public class TitleWordService {
    @Autowired
    TitleWordMapper titleWordMapper;

    public List<TitleWord> getAllWords(Integer selectLimitNum) {
        return titleWordMapper.selectWordsSorted(selectLimitNum);
    }
}
