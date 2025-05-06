using Core.Entities.OrderAggregate;
using Microsoft.EntityFrameworkCore.ChangeTracking;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Core.Specifications
{
    public class OrderSpecification : BaseSpecification<Order>
    {
        public OrderSpecification(string email): base(x => x.BuyerEmail == email)
        {
            AddInclude(x => x.DeliveryMethod);
            AddInclude(x => x.OrderItems);
            AddOrderByDescending(x => x.OrderDate);
        }
        public OrderSpecification(string email,int id) : base(x => x.BuyerEmail == email && x.Id == id)
        {
            AddInclude("DeliveryMethod");
            AddInclude("OrderItems");
            AddOrderByDescending(x => x.OrderDate);
        }
    }
}
