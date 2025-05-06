using API.DTOs;
using API.Extensions;
using Core.Entities;
using Core.Entities.OrderAggregate;
using Core.Interfaces;
using Core.Specifications;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
//using StackExchange.Redis;

namespace API.Controllers
{
    [Authorize]
    public class OrdersController(ICartService cartService, IUnitOfWork unitOfWork) : BaseApiController
    {

        [HttpPost]
        public async Task<ActionResult<Order>> CreateOrder(CreateOrderDto orderDto)
        {
            var email = User.GetEmail();

            var cart = await cartService.GetCartAsync(orderDto.CartId);

            if (cart == null) return BadRequest("Cart not found");

            if (cart.PaymentIntentId == null) return BadRequest("No payment intent for this order");

            var items = new List<OrderItem>();
            foreach (var item in cart.Items)
            {
                var productItem = await unitOfWork.Repository<Product>().GetByIdAsync(item.ProductId);
                if (productItem == null) return BadRequest("Product not found");

                var itemOrdered = new ProductItemOrdered
                {
                    ProductId = item.ProductId,
                    ProductName = item.ProductName,
                    PictureUrl = item.PictureUrl
                };
                var orderItem = new OrderItem
                {
                    ItemOrdered = itemOrdered,
                    Price = item.Price,
                    Quantity = item.Quantity
                };
                items.Add(orderItem);
            }
            var deliveryMethod = await unitOfWork.Repository<DeliveryMethod>().GetByIdAsync(orderDto.DeliveryMethodId);
            if (deliveryMethod == null) return BadRequest("No delivery method selected");

            var shippingAddress = new ShippingAddress
            {
                Name = orderDto.ShippingAddress.Name,
                Line1 = orderDto.ShippingAddress.Line1,
                Line2 = orderDto.ShippingAddress.Line2,
                City = orderDto.ShippingAddress.City,
                State = orderDto.ShippingAddress.State,
                PostalCode = orderDto.ShippingAddress.PostalCode,
                Country = orderDto.ShippingAddress.Country
            };

            var paymentSummary = new PaymentSummary
            {
                Last4 = orderDto.PaymentSummary.Last4,
                Brand = orderDto.PaymentSummary.Brand,
                ExpMonth = orderDto.PaymentSummary.ExpMonth,
                ExpYear = orderDto.PaymentSummary.ExpYear
            };

            var order = new Order
            {
                OrderItems = items,
                ShippingAddress = shippingAddress,
                DeliveryMethod = deliveryMethod,
                PaymentIntentId = cart.PaymentIntentId,
                Subtotal = items.Sum(x => x.Price * x.Quantity),
                PaymentSummary = paymentSummary,
                BuyerEmail = email
            };
            unitOfWork.Repository<Order>().Add(order);
            if (await unitOfWork.CompleteAsync())
            {
                return order;
            }

            return BadRequest("Problem creating order");
        }

        [HttpGet]
        public async Task<ActionResult<IReadOnlyList<OrderDto>>> GetOrdersForUser()
        {
            var email = User.GetEmail();
            if (string.IsNullOrEmpty(email)) return Unauthorized();

            var spec = new OrderSpecification(email);
            var orders = await unitOfWork.Repository<Order>().ListAsync(spec);
            
            if (orders == null || !orders.Any()) return NotFound("No orders found for the user.");

            var orderToReturn = orders.Select(o => o.ToDto()).ToList();

            return Ok(orderToReturn);
        }

        [HttpGet("{id:int}")]
        public async Task<ActionResult<OrderDto>> GetOrderById(int id)
        {
            var email = User.GetEmail();

            if (string.IsNullOrEmpty(email)) return Unauthorized();

            var spec = new OrderSpecification(email, id);
            var order = await unitOfWork.Repository<Order>().GetEntityWithSpec(spec);

            if (order == null) return NotFound("Order not found");

            return Ok(order.ToDto());
        }

        [HttpGet("delivery-methods")]
        public async Task<ActionResult<IReadOnlyList<DeliveryMethod>>> GetDeliveryMethods()
        {
            var deliveryMethods = await unitOfWork.Repository<DeliveryMethod>().ListAllAsync();

            return Ok(deliveryMethods);
        }
    }
}
