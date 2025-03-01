package com.farmweb.api.controller;


import com.farmweb.api.dto.HistoryDTO;
import com.farmweb.api.model.History;
import com.farmweb.api.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @PostMapping("/save")
    public ResponseEntity<History> createHistory(@RequestBody HistoryDTO historyDTO) {
        try {
            History createdHistory = historyService.createHistoryEntry(historyDTO);
            return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle the error (e.g., vendor or customer not found)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Or a more specific error code
        } catch (Exception e) {
            // Handle other exceptions (e.g., database errors)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

