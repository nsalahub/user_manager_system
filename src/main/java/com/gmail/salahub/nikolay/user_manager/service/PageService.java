package com.gmail.salahub.nikolay.user_manager.service;

public interface PageService {
    Integer getValueOfPages(Integer valueOfModels, Integer limit);

    Integer getLimitValue(Integer limit, Integer page);
}
