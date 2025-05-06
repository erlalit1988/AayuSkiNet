using API.DTOs;
using Core.Entities.OrderAggregate;

namespace API.Extensions
{
    public static class OrderMappingExtensions
    {
        public static OrderDto ToDto(this Order order)
        {
            if (order == null) throw new ArgumentNullException(nameof(order));

            return new OrderDto
            {
                Id = order.Id,
                BuyerEmail = order.BuyerEmail,
                OrderDate = order.OrderDate,
                ShippingAddress = order.ShippingAddress.ToDto(),
                DeliveryMethod = order.DeliveryMethod.Description,
                ShippingPrice = order.DeliveryMethod.Price,
                OrderItems = order.OrderItems.Select(oi => oi.ToDto()).ToList(),
                Subtotal = order.Subtotal,
                Status = order.Status.ToString(),
                PaymentIntentId = order.PaymentIntentId,
                PaymentSummary = new PaymentSummaryDto
                {
                    Last4 = order.PaymentSummary.Last4,
                    Brand = order.PaymentSummary.Brand,
                    ExpMonth = order.PaymentSummary.ExpMonth,
                    ExpYear = order.PaymentSummary.ExpYear
                },
                Total = order.GetTotal()
            };
        }

        public static OrderItemDto ToDto(this OrderItem orderItem)
        {
            if (orderItem == null) throw new ArgumentNullException(nameof(orderItem));

            return new OrderItemDto
            {
                ProductId = orderItem.ItemOrdered.ProductId,
                ProductName = orderItem.ItemOrdered.ProductName,
                PictureUrl = orderItem.ItemOrdered.PictureUrl,
                Price = orderItem.Price,
                Quantity = orderItem.Quantity
            };
        }

        public static ShippingAddressDto ToDto(this ShippingAddress shippingAddress)
        {
            if (shippingAddress == null) throw new ArgumentNullException(nameof(shippingAddress));

            return new ShippingAddressDto
            {
                Name = shippingAddress.Name,
                Line1 = shippingAddress.Line1,
                Line2 = shippingAddress.Line2,
                City = shippingAddress.City,
                State = shippingAddress.State,
                Country = shippingAddress.Country,
                PostalCode = shippingAddress.PostalCode
            };
        }
    }
}
