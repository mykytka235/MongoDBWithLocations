package com.lootfood.api.transformer;

import com.lootfood.api.dto.CompanyDto;
import com.lootfood.db.entity.Company;

public class CompanyTransformer {
    public static Company transform(CompanyDto dto) {
        return transform(dto.getId(), dto);
    }

    public static Company transform(String id, CompanyDto dto) {
        return Company.builder().id(id)
                .name(dto.getName())
                .imageUrl(dto.getImageUrl())
                .services(dto.getServices())
                .build();
    }

    public static CompanyDto transform(Company company) {
        return transform(company.getId(), company);
    }

    public static CompanyDto transform(String id, Company company) {
        return CompanyDto.builder().id(id)
                .name(company.getName())
                .imageUrl(company.getImageUrl())
                .services(company.getServices())
                .build();
    }
}