import Product from '@/domain/Product'
import {CustomerInfo} from '@/domain/CustomerInfo'
import {PaymentInfo} from '@/domain/PaymentInfo'

/**
 * Contains all information for a checkout
 */
export class CheckoutInfo {

    public customerInfo: CustomerInfo = new CustomerInfo()

    public paymentInfo: PaymentInfo = new PaymentInfo()

    public shippingOption = 'free'

    public cartItems: {product: Product, quantity: number, total: number}[] = []

}
