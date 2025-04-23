import { Pipe, PipeTransform } from '@angular/core';
import { ConfirmationToken } from '@stripe/stripe-js';

@Pipe({
  name: 'address',
  standalone: true
})
export class AddressPipe implements PipeTransform {

  transform(value?: ConfirmationToken['shipping'], ...args: unknown[]): unknown {
    if(value?.address && value.name) {
     const {line1, line2, city, state, postal_code, country} = value.address;
     const address = [line1, line2, city, state, postal_code, country].filter(Boolean).join(', ');
     return `${value.name}, ${address}`; 
    } else{
      return 'No address provided';
    }
    
  }

}
