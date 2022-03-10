/**
 * Holds all data for the shopping cart
 */
import {CartItem} from '@/domain/CartItem'
import Product from '@/domain/Product'

export class Cart {

    private itemMap: Map<number, CartItem> = new Map()

    private _total = 0.0

    public itemCount(){
        return this.itemMap.size
    }

    public items(): IterableIterator<CartItem> {
        return this.itemMap.values()
    }

    public addProduct(product: Product, quantity: number){
        let cartItem = this.itemMap.get(product.id)
        if(!cartItem){
            cartItem = new CartItem(product, quantity)
            this.itemMap.set(product.id, cartItem)
        }
        cartItem.addQuantity(quantity)
        this._total += product.price * quantity
    }

    public get total(): number {
        return this._total
    }
}