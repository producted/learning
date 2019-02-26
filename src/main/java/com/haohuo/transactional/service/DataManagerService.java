package com.haohuo.transactional.service;

public interface DataManagerService {
    void pay();

    void updateState(Integer orId);

    void compose(Integer orId);
}
