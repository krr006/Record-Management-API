package com.krr006.records.service;

import com.krr006.records.exception.RecordNotFoundException;
import com.krr006.records.entity.Record;
import com.krr006.records.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    public Record getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Record updateRecord(Long id, Record newRecord) {
        return recordRepository.findById(id)
                .map(record -> {
                    record.setData(newRecord.getData());
                    return recordRepository.save(record);
                })
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new RecordNotFoundException(id);
        }
        recordRepository.deleteById(id);
    }
}
