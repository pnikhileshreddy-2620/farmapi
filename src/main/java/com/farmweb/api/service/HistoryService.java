package com.farmweb.api.service;

import com.farmweb.api.dto.HistoryDTO;
import com.farmweb.api.model.History;
import org.springframework.transaction.annotation.Transactional;

public interface HistoryService {

    public History createHistoryEntry(HistoryDTO historyDTO);
}
