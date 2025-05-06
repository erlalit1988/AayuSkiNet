namespace Core.Entities.OrderAggregate
{
    public enum OrderStatus
    {
        Pending,        // Order has been created but not yet processed
        PaymentReceived, // Payment has been successfully received
        PaymentFailed,   // Payment failed during processing
        Shipped,         // Order has been shipped
    }
}
