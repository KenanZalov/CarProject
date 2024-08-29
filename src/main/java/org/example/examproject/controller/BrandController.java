package org.example.examproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.examproject.dto.BrandDto;
import org.example.examproject.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("/getAllBrands")
    public List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public BrandDto getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @PostMapping("/addBrand")
    public BrandDto addBrand(@RequestBody BrandDto brandDTO) {
        return brandService.addBrand(brandDTO);
    }

    @PutMapping("/{id}")
    public BrandDto updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }


}
