using Core.Entities.OrderAggregate;
using System;
using System.Collections.Generic;

namespace API.DTOs
{
    public class OrderDto
    {
        public int Id { get; set; }
        public DateTime OrderDate { get; set; }
        public required string BuyerEmail { get; set; }
        public required ShippingAddressDto ShippingAddress { get; set; }
        public required string DeliveryMethod { get; set; }
        public decimal ShippingPrice { get; set; }
        public required PaymentSummaryDto PaymentSummary { get; set; }
        public required List<OrderItemDto> OrderItems { get; set; }
        public decimal Subtotal { get; set; }
        public required string PaymentIntentId { get; set; }
        public required string Status { get; set; }
        public decimal Total { get; set; }
    }
}
