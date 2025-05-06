using Core.Entities;
using Core.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    public class PaymentsController(IPaymentService paymentService, 
        IUnitOfWork unitOfWork) :  BaseApiController
    {
        [Authorize]
        [HttpPost("{cartId}")]
        public async Task<ActionResult<ShoppingCart>> CreateorUpdatePaymentIntent(string cartId)
        {
            var cart = await paymentService.CreateOrUpadatePaymentIntent(cartId);

            if (cart == null) return BadRequest("Problem with your cart");

            return Ok(cart);

        }
        [Authorize]
        [HttpGet("delivery-methods")]
        public async Task<ActionResult<IReadOnlyList<DeliveryMethod>>> GetDeliveryMethods()
        {
            return Ok(await unitOfWork.Repository<DeliveryMethod>().ListAllAsync());

        }
    }
}
