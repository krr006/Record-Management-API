package com.krr006.records.controller;

import com.krr006.records.entity.Record;
import com.krr006.records.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record record) {
        return new ResponseEntity<>(recordService.createRecord(record), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return new ResponseEntity<>(recordService.getRecordById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record newRecord) {
        return new ResponseEntity<>(recordService.updateRecord(id, newRecord), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
