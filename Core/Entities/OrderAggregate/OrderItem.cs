namespace Core.Entities.OrderAggregate
{
    public class OrderItem :BaseEntity 
    {
        public required ProductItemOrdered ItemOrdered { get; set; } // Product details
        public decimal Price { get; set; } // Price of the product at the time of order
        public int Quantity { get; set; } // Quantity of the product ordered
    }
}

