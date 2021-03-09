package com.lootfood.service;

import com.lootfood.db.entity.Company;
import com.lootfood.db.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company add(Company service) {
        return companyRepository.save(service);
    }

    public Company getById(String id) {
        return companyRepository.findById(id).get();
    }

    public Page<Company> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Company update(Company company) {
        return companyRepository.save(company);
    }
}