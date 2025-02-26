﻿using Core.Entities;
using Core.Interfaces;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics.Eventing.Reader;

namespace API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductsController(IProductRepository productRepo) : ControllerBase
    {
        [HttpGet]
        public async Task<ActionResult<IReadOnlyList<Product>>> GetProducts(string? brand, string? type, 
            string? sort)
        {
            return Ok(await productRepo.GetProductsAsync(brand, type, sort));
        }

        [HttpGet("{id:int}")] // api/products/2
        public async Task<ActionResult<Product>> GetProduct(int id)
        {
            var product = await productRepo.GetProductByIdAsync(id);

            if (product == null) return NotFound();

            return Ok(product);
        }

        [HttpPost]
        public async Task<ActionResult<Product>> CreateProduct(Product product)
        {
            productRepo.AddProduct(product);

            if (await productRepo.SaveChangesAsync())
            {
                return CreatedAtAction("GetProduct", new { id = product.Id }, product);
            }
            return BadRequest("Problem creating product");
        }

        [HttpPut("{id:int}")]
        public async Task<ActionResult> UpdateProduct(int id, Product product)
        {
            if (product.Id != id || !ProductExists(id)) 
                  return BadRequest("Cannot update this product");

            productRepo.UpdateProduct(product);
            if (await productRepo.SaveChangesAsync())
            { 
               return NoContent();
            };

            return BadRequest("Problem occurs druing updating this product");
        }

        [HttpDelete("{id:int}")]
        public async Task<ActionResult> DeleteProduct(int id)
        {
            var product = await productRepo.GetProductByIdAsync(id);
            if (product == null) return NotFound();

            productRepo.DeleteProduct(product);

            if (await productRepo.SaveChangesAsync())
            {
                return NoContent();
            };
            return BadRequest("Problem deleting the product.");
        }
        [HttpGet("brands")]
        public async Task<ActionResult<IReadOnlyList<string>>> GetBrands()
        {
            return Ok(await productRepo.GetBrandsAsync());
        }

        [HttpGet("types")]
        public async Task<ActionResult<IReadOnlyList<string>>> GetTypes()
        {
            return Ok(await productRepo.GetTypesAsync());
        }
        private bool ProductExists(int id)
        {
            return productRepo.ProductExits(id);
        }
    }
}
