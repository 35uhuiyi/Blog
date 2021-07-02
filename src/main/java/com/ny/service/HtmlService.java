package com.ny.service;

import com.ny.annotation.AccessLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/12 16:31
 */

/**
 * 静态化处理
 */
@Service
public class HtmlService {
    @Autowired
    private TemplateEngine engine;

}
