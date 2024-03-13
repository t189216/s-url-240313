package com.ll.surl240313.domain.surl.surl.service;

import com.ll.surl240313.domain.surl.surl.repository.SurlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SurlService {
    private final SurlRepository surlRepository;

    public long count() {
        return surlRepository.count();
    }
}