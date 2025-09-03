package com.aayuskinet.api.controllers;

import com.aayuskinet.api.helpers.Pagination;
import com.aayuskinet.core.entities.BaseEntity;
import com.aayuskinet.core.interfaces.IGenericRepository;
import com.aayuskinet.core.interfaces.ISpecification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Base path for all controllers
public class BaseApiController {

    protected <T extends BaseEntity> ResponseEntity<Pagination<T>> createPageResult(
            IGenericRepository<T> repo, ISpecification<T> spec, int pageIndex, int pageSize) {
        List<T> items = repo.list(spec);
        long count = repo.count(spec);
        Pagination<T> pagination = new Pagination<>(pageIndex, pageSize, count, items);
        return ResponseEntity.ok(pagination);
    }
}
