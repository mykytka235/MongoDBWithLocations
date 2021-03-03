package com.lootfood.api.controller;

import com.lootfood.api.dto.CompanyDto;
import com.lootfood.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.lootfood.api.transformer.CompanyTransformer.transform;

@RestController
@RequestMapping("/api/lootfood/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public CompanyDto create(@RequestBody CompanyDto dto) {
        return transform(companyService.add(transform(dto)));
    }

    @PutMapping("/{id}")
    public CompanyDto update(@PathVariable("id") String id, @RequestBody CompanyDto dto)  {
        return transform(id, companyService.update(transform(id, dto)));
    }

    @GetMapping("/{id}")
    public CompanyDto getById(@PathVariable("id") String id) {
        return transform(companyService.getById(id));
    }

    @GetMapping(value = "/all")
    public Page<CompanyDto> getAll(Integer page, Integer size) {
        return companyService.getAll(PageRequest.of(page, size)).map(e -> transform(e));
    }
}
